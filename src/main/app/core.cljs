(ns app.core
  (:require
    [app.model :as model]
    [app.theme :as theme]
    [re-frame.core :as rf]
    [reagent.dom :refer [render]]
    [reagent-mui.styles :as mui-styles]
    [app.home :as home]
    [breaking-point.core :as bp]))

(defn ^:dev/after-load app
  "Render the toplevel component for this app."
  []
  (rf/dispatch-sync
    [::bp/set-breakpoints
     {:breakpoints theme/breakpoints}])
  (render
    [mui-styles/theme-provider theme/app-theme
     [home/site]]
    (.getElementById js/document "app")))

(defn ^:export main
  "Run application startup logic."
  []
  (app))