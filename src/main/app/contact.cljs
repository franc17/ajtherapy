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
           :sx {:scroll-margin-top "60px"}}
   [m/grid {:item true
            :xs 12}
    [c/title :left "Contact"
     {:title-color theme/beige
      :border-color theme/forest}]]

   [m/grid {:item true
            :xs 12
            :md 4}
    [m/stack {:direction :row
              :spacing 2
              :align-items :center
              :pb 2}
     [icon/email {:sx {:color theme/subtitle}}]
     [m/typography {:variant :h4}
      [m/link {:href "mailto:hello@ajtherapy.uk"
               :target "_blank"
               :rel "noopener noreferrer"}
       "hello@ajtherapy.uk"]]]

    [m/stack {:direction :row
              :spacing 2
              :align-items :center}
     [icon/place {:sx {:color theme/subtitle}}]
     [m/typography {:variant :h4} "Balham"]]]

   [m/grid {:item true
            :xs 12
            :md 8}
    [m/typography {:variant :h5
                   :paragraph true}
     "I offer a free 20 minute consultation call."]]])
