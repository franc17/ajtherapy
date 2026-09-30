(ns app.cost
  (:require [app.components :as c]
            [app.theme :as theme]
            [reagent-mui.components :as m]))

(defn prices []
  [m/grid {:id "prices"
           :container true
           :spacing 2
           :column-spacing 3
           :sx {:scroll-margin-top "60px"}}
   [m/grid {:item true
            :xs 12}
    [c/title :right "Fees"]]

   [m/grid {:item true
            :xs 12
            :sm 6}
    [m/card {:sx {:width 0.8
                  :display :flex
                  :p 2
                  :justify-content :center
                  :justify-self :center}}
     [m/typography {:variant :h4
                    :color theme/text}
      "In-Person"
      [m/typography {:component :span
                     :variant :h4
                     :color theme/subtitle
                     :pl 2}
       "£70"]]]]

   [m/grid {:item true
            :xs 12
            :sm 6}
    [m/card {:sx {:width 0.8
                  :display :flex
                  :p 2
                  :justify-content :center
                  :justify-self :center}}
     [m/typography {:variant :h4}
      "Online"
      [m/typography {:component :span
                     :variant :h4
                     :color theme/subtitle
                     :pl 2}
       "£60"]]]]

   [m/grid {:item true
            :xs 12
            :sx {:mt 2}}
    [m/typography {:variant :body2}
     "Price is for a 50 minute individual counselling session. I accept payment by credit/debit card, contactless, or bank-transfer."]]])



[m/grid {:item true
         :xs 12
         :sm 6}
 [m/typography {:variant :h4
                :color theme/text}
  "In-Person"
  [m/typography {:component :span
                 :variant :h4
                 :color theme/subtitle
                 :pl 2}
   "£70"]]
 [m/typography {:variant :body1
                :paragraph true}
  "In-person counselling takes place in a comfortable and private therapy room at
  Balham Community Centre in Southwest London."]]


[m/grid {:item true
         :xs 12
         :sm 6}
 [m/typography {:variant :h4}
  "Online"
  [m/typography {:component :span
                 :variant :h4
                 :color theme/subtitle
                 :pl 2}
   "£60"]]
 [m/typography {:variant :body1
                :paragraph true}
  "I conduct online sessions using Google Meet, and always recommend that clients join the call
  from a quiet environment where we won't be disturbed."]]