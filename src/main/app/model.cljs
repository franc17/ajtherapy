(ns app.model
  (:require
    [re-frame.core :as rf]
    [reagent.core :as r]))

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

(rf/reg-event-db :set-hosp-submitted
  (fn [db [_ bool]]
    (assoc db :hosp-submitted bool)))

(rf/reg-sub :hosp-submitted
  (fn [db _]
    (get db :hosp-submitted)))

(rf/reg-event-fx :hosp-submit
  (fn [_ [_ {:keys [values dirty path]}]]
    (print
      values)
    {:dispatch [:set-hosp-submitted true]}))