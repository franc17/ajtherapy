(ns app.hospitality
  (:require [app.theme :as theme]
            [reagent-mui.components :as m]))


(defn page []
  [m/box {:sx {:min-height "100vh"
               :background-color theme/paper}}])