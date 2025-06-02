(ns app.model
  (:require
    [re-frame.core :as rf]
    [reagent.core :as r]
    [cljs.core.async :refer [go]]
    [cljs.core.async.interop :refer-macros [<p!]]))

;Homepage scroll behaviour -----

(defn get-element-top [id]
  (let [el (.getElementById js/document id)]
    (- (.-top
         (.getBoundingClientRect el))
      100)))

(rf/reg-event-db :set-boundaries
  (fn [db [_]]
    (assoc db :scroll-boundaries
              {:home (get-element-top "home")
               :about (get-element-top "about")
               :prices (get-element-top "prices")
               :contact (get-element-top "contact")})))

(def current-pos (r/atom 0))

(rf/reg-sub :boundaries
  (fn [db _]
    (get db :scroll-boundaries)))

(rf/reg-event-fx :scroll-to
  (fn [_ [_ id]]
    (.scrollIntoView
      (.getElementById js/document (name id))
      (clj->js {:behavior :smooth}))
    {:dispatch [:set-mobile-menu false]}))


;Mobile menu -----

(rf/reg-event-db :set-mobile-menu
  (fn [db [_ bool]]
    (assoc db :mobile-menu bool)))

(rf/reg-sub :mobile-menu
  (fn [db _]
    (get db :mobile-menu)))


;Homepage calendar -----

(rf/reg-event-fx :go-to-calendar
  (fn [{:keys [db]} _]
    {:db (assoc db :calendar true)
     :dispatch [:scroll-to :cal]}))

(rf/reg-event-db :set-calendar
  (fn [db [_ bool]]
    (assoc db :calendar bool)))

(rf/reg-sub :calendar
  (fn [db _]
    (get db :calendar)))


;Hospitality contact -----

(defn submit-form [form-data]
  (go
    (let [response (<p! (js/fetch "https://api.web3forms.com/submit"
                          #js {:method "POST"
                               :body form-data}))]
      (print response))))

(rf/reg-event-db :set-hosp-submitted
  (fn [db [_ bool]]
    (assoc db :hosp-submitted bool)))

(rf/reg-sub :hosp-submitted
  (fn [db _]
    (get db :hosp-submitted)))

(rf/reg-event-db :set-hosp-attempted
  (fn [db [_ bool]]
    (assoc db :hosp-attempted bool)))

(rf/reg-sub :hosp-attempted
  (fn [db _]
    (get db :hosp-attempted)))

(rf/reg-event-db :reset-hosp
  (fn [db _]
    (assoc db :hosp-submitted false :hosp-attempted false)))

(rf/reg-event-fx :hosp-submit
  (fn [_ [_ {:keys [values]}]]
    (let [form-data (doto
                      (js/FormData.)
                      (.append "access_key" "3e2b1f17-04f9-41af-8b82-9caa187a8401")
                      (.append "subject" "New Hospitality Enquiry")
                      (.append "name" (:name values))
                      (.append "role" (:role values))
                      (.append "company" (:company values))
                      (.append "email" (:email values))
                      (.append "phone" (:phone values))
                      (.append "info" (:info values)))]
      (submit-form form-data)
      {:dispatch [:set-hosp-submitted true]})))