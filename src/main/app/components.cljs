(ns app.components
  (:require [app.theme :as theme]
            [reagent-mui.components :as m]
            [app.icons :as icon]))

(defn title [position text]
  [m/divider {:component :div
              :text-align position
              :sx {:mb 1
                   :mt 3
                   :width {:xs 1 :sm 0.8}
                   :float position
                   "&::before, &::after"
                   {:border-color theme/accent
                    :border-width :medium}}}
   [m/typography {:variant :h1}
    text]])

(defn li [text]
  [m/list-item
   [m/list-item-icon
    [icon/circle {:sx {:color theme/accent}}]]
   [m/list-item-text
    text]])
