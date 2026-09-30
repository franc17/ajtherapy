(ns app.cost
  (:require [app.components :as c]
            [app.theme :as theme]
            [reagent-mui.components :as m]))

(defn prices []
  [m/grid {:id "prices"
           :container true
           :spacing 2
           :column-spacing 3
           :justify-content :space-evenly
           :align-items :stretch
           :sx {:scroll-margin-top "60px"}}
   [m/grid {:item true
            :xs 12}
    [c/title :right "Fees"]]

   [m/grid {:item true
            :xs 12
            :md 4}
    [m/card {:sx {:p 4
                  :border-radius 10
                  :background-color theme/pale}}
     [m/card-content
      [m/typography {:variant :h4
                     :color theme/text
                     :text-align :center}
       "In-Person"
       [m/typography {:component :span
                      :variant :h4
                      :color theme/olive
                      :pl 2}
        "£70"]]
      [m/typography {:variant :body1
                     :pt 3
                     :sx {:line-height 2
                          :text-align :center}}
       "In-person counselling takes place in a comfortable and private therapy room in
     Balham, South West London."]]]]

   [m/grid {:item true
            :xs 12
            :md 4}
    [m/card {:sx {:p 4
                  :border-radius 10
                  :background-color theme/pale}}
     [m/card-content
      [m/typography {:variant :h4
                     :color theme/text
                     :text-align :center}
       "Online"
       [m/typography {:component :span
                      :variant :h4
                      :color theme/olive
                      :pl 2}
        "£60"]]
      [m/typography {:variant :body1
                     :pt 3
                     :sx {:line-height 2
                          :text-align :center}}
       "I conduct online sessions using Google Meet, and always recommend that clients join the call
     from a quiet and private environment."]]]]


   [m/grid {:item true
            :xs 12
            :sx {:mt 2}}
    [m/typography {:variant :body2}
     "Price is for a 50 minute individual counselling session. I accept payment by credit/debit card, contactless, or bank-transfer."]]])
