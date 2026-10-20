(ns app.about
  (:require [app.components :as c]
            [app.model :as model]
            [re-frame.core :as rf]
            [reagent-mui.components :as m]
            [breaking-point.core :as bp]))

(def main-copy
  "As an integrative therapist, I am trained in Cognitive Behavioural Therapy (CBT), Psychodynamic Theory, and Person-Centred Therapy,
   and I am able to draw on any or all of these theoretical models based on what I feel would most benefit you as a client.")

(def additional-copy
  "Although I work with both men and women, I have a particular passion for supporting men in exploring and expressing their emotions.
   In my experience, many men have grown up in environments where vulnerability is discouraged,
   often leading to feelings of being disconnected from their emotions or finding their emotions expressed in negative ways.
   Over time, this can lead to struggles with relationships and self-esteem.
   I believe that therapy can help men break down these barriers, develop emotional awareness, and find new ways to express themselves that feel authentic and empowering.")


(defn about []
  (let [mobile (model/handheld?)]
    [m/grid {:container true
             :spacing 2}
     [m/grid {:item true
              :xs 12}
      [c/title :right "About My Practice"]]

     (if mobile
       [c/read-more-block
        {:id :about
         :visible
         [m/typography {:variant :body1}
          main-copy]
         :read-more
         [m/stack {:direction :column
                   :spacing 1}
          [m/typography {:variant :body1}
           additional-copy]]}]

       [:<>
        [m/grid {:item true
                 :container true
                 :justify-content :center
                 :xs 12
                 :md 4}
         [:img {:src "/images/aj-room.jpeg"
                :style {:object-fit :cover
                        :border-radius "25px"
                        :max-width "80%"
                        :max-height "420px"}}]]
        [m/grid {:item true
                 :xs 12
                 :md 8}
         [m/typography {:variant :body1
                        :paragraph true}
          main-copy]
         [m/typography {:variant :body1
                        :paragraph true}
          additional-copy]]])]))


(defn help-with []
  (let [mobile (model/handheld?)]
    [m/grid {:container true
             :spacing 2}
     [m/grid {:item true
              :xs 12}
      [c/title :right "Focus Areas"]]

     ;TODO do we need read-more? what's in it?

     [m/grid {:item true
              :xs 6
              :sm 3}
      [m/list {:dense true}
       [c/li "Abuse"]
       [c/li "Addictions"]]]
     [m/grid {:item true
              :xs 6
              :sm 3}
      [m/list {:dense true}
       [c/li "Abuse"]
       [c/li "Addictions"]]]
     [m/grid {:item true
              :xs 6
              :sm 3}
      [m/list {:dense true}
       [c/li "Abuse"]
       [c/li "Addictions"]]]
     [m/grid {:item true
              :xs 6
              :sm 3}
      [m/list {:dense true}
       [c/li "Abuse"]
       [c/li "Addictions"]]]]))