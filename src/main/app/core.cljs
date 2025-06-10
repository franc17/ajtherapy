(ns app.core
  (:require
    [app.routing :as router]
    [app.theme :as theme]
    [re-frame.core :as rf]
    [reagent.dom :refer [render]]
    [reagent-mui.styles :as mui-styles]
    [app.home :as home]
    [breaking-point.core :as bp]
    [app.hospitality :as hospitality]
    [app.not-found :as not-found]))

(defn visible-page []
  (let [page @(rf/subscribe [:current-page])]
    (when page
      (case page
        :home [home/site]
        :hospitality [hospitality/page]
        [not-found/page]))))

(defn ^:dev/after-load app
  "Render the toplevel component for this app."
  []
  (rf/dispatch-sync
    [::bp/set-breakpoints
     {:breakpoints theme/breakpoints}])
  (render
    [mui-styles/theme-provider theme/app-theme
     [visible-page]]
    (.getElementById js/document "app")))

(defn ^:export main
  "Run application startup logic."
  []
  (rf/dispatch-sync [:initialize-db])
  (router/init-router!)
  (app))