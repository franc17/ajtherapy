(ns app.cookies
  (:require
    [app.components :as c]
    [app.theme :as theme]
    [re-frame.core :as rf]
    [reagent-mui.components :as m]))

(defn cookie-banner []
  [m/paper {:sx {:z-index 100
                 :position :sticky
                 :bottom 0}}
   [c/section {:bgcolor theme/ly
               :p 1}
    [m/stack {:direction :row
              :justify-content :space-between
              :align-items :center}
     [m/typography {:variant :body1
                    :width 0.75}
      "This site uses essential cookies to make it work. With your consent, it may also use non-essential cookies
    to analyse website traffic."]
     [m/stack {:direction :row
               :spacing 2}
      [m/button {:variant :contained
                 :sx {:textTransform :none}
                 :on-click #(rf/dispatch [:set-cookie-consent true])}
       "Accept all"]
      [m/button {:variant :outlined
                 :sx {:textTransform :none}
                 :on-click #(rf/dispatch [:set-cookie-consent false])}
       "Reject non-essential"]]]]])
