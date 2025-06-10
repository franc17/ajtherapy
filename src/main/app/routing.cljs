(ns app.routing
  (:require [re-frame.core :as rf]
            [reitit.frontend :as rfr]
            [reitit.frontend.easy :as rfe]))

(def routes
  [""
   ["/"      {:name :home}]
   ["/hospitality" {:name :hospitality}]])

(def router
  (rfr/router routes))

(rf/reg-event-db
  :navigated
  (fn [db [_ match]]
    (let [page (get-in match [:data :name])
          params (:path-params match)]
      (assoc db :current-route {:page page :params params}))))

(rf/reg-sub
  :current-page
  (fn [db _]
    (get-in db [:current-route :page])))

(defn init-router! []
  (rfe/start!
    router
    (fn [match]
      (when match
        (rf/dispatch [:navigated match])))
    {:use-fragment true}))