(ns app.routing
  (:require
    [re-frame.core :as rf]
    [reitit.core :as r]
    [reitit.frontend.easy :as rfe]))

(def router
  (r/router
    [["/" {:name ::home}]
     ["/hospitality" {:name ::hospitality}]]))

(defmulti panels identity)
(defmethod panels :default [] [:div "Oops, page not found"])

(rf/reg-event-fx :do-navigate
  [route])


(rf/reg-fx :navigate
  (fn [route]
    (navigate! route)))

(rf/reg-event-fx :navigate-to
  (fn [_ [_ route]]
    {:navigate route}))