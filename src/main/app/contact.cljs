(ns app.contact
  (:require [app.icons :as icon]
            [re-frame.core :as rf]
            [reagent-mui.components :as m]
            [app.components :as c]
            [app.theme :as theme]
            [reagent.core :as r]))

(defn contact []
  [m/grid {:id "contact"
           :container true
           :row-spacing 2
           :column-spacing 2
           :sx {:scroll-margin-top "80px"}}
   [m/grid {:item true
            :xs 12}
    [c/title :left "Contact"
     {:border-color theme/sage}]]

   [m/grid {:item true
            :xs 12
            :md 6}
    [m/stack {:direction :row
              :spacing 2
              :align-items :baseline
              :pb 2}
     [icon/email {:sx {:color theme/pumpkin}}]
     [m/typography {:variant :h4}
      [m/link {:href "mailto:hello@ajtherapy.uk"
               :target "_blank"
               :rel "noopener noreferrer"
               :color theme/onyx}
       "hello@ajtherapy.uk"]]]]


   [m/grid {:item true
            :xs 12
            :md 6}
    [m/stack {:direction :row
              :spacing 2
              :align-items :baseline}
     [icon/place {:sx {:color theme/pumpkin}}]
     [m/typography {:variant :h4} "Balham"]]]])
