(ns app.not-found
  (:require [app.icons :as icon]
            [app.theme :as theme]
            [reagent-mui.components :as m]
            [reagent.core :as r]
            [reitit.frontend.easy :as rfe]))

(defn page []
  [m/box {:sx {:height "100vh"
               :width "100vw"
               :background-color theme/paper
               :py 5}}
   [m/container
    [m/typography {:variant :h3
                   :paragraph true}
     "Oops! Page not found."]
    [m/button {:variant :outlined
               :start-icon (r/as-element [icon/backspace])
               :href (rfe/href :home)}
     "Back to the homepage"]]])
