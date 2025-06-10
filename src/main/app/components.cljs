(ns app.components
  (:require [app.theme :as theme]
            [re-frame.core :as rf]
            [reagent-mui.components :as m]
            [app.icons :as icon]
            [reagent.core :as r]
            [breaking-point.core :as bp]))

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
   [m/typography {:variant :h1
                  :sx {:text-wrap (if (< @(rf/subscribe [::bp/screen-width]) 1000)
                                    :wrap
                                    :no-wrap)}}
    text]])

(defn li [text & [icon]]
  [m/list-item
   [m/list-item-icon
    (if icon
      icon
      (r/as-element [icon/circle {:font-size "10px"}]))]
   [m/list-item-text {:sx {:ml -2}}
    text]])

(defn pt-verification []
  [:a.sx-verified-seal
   {:href "https://www.psychologytoday.com/profile/1494012"}])
