(ns app.home
  (:require [app.icons :as icon]
            [app.model :as model]
            [re-frame.core :as rf]
            [reagent-mui.components :as m]
            [app.layout :as l]
            [app.components :as c]
            [app.theme :as theme]
            [reagent.core :as r]
            [app.contact :refer [contact]]
            [breaking-point.core :as bp]))

(defn hero []
  [:img {:src "/images/yellow-chairs.jpg"
         :style {:height "50vh"
                 :width "100%"
                 :object-fit :cover}}])

(defn offer []
  [m/grid {:id "home"
           :container true
           :row-spacing 2
           :column-spacing 3
           :my 2
           :sx {:scroll-margin-top "60px"}}
   [m/grid {:item true
            :xs 12}
    [c/title :left "Welcome"]]

   [m/grid {:item true
            :xs 12
            :md 8
            :align-items :center
            :sx {:height "fit-content"}}

    [m/typography {:variant :body1
                   :paragraph true}
     "Hi, I’m Adrian, an integrative therapist providing a warm and supportive space to explore a range of challenges, including anxiety,
     depression, goals and low self-esteem. I have a particular interest in men’s mental health and use a range of therapeutic models and
     frameworks to help you recognise patterns, regulate emotions, and move toward meaningful, lasting change. I see therapy as a collaborative
     process - one where you’re truly heard, not judged or directed. Together, we’ll work to deepen self-awareness, build resilience, and support
     you in creating a more fulfilling life."]

    [m/typography {:variant :body1
                   :paragraph true}
     "For me, therapy isn’t about giving advice or telling you what to do - it’s about working together to explore your
     thoughts, emotions, and patterns so that you can better understand yourself and move towards the life you want.
     My role is to facilitate that process by providing a safe, supportive, and non-judgmental space where you feel truly heard."]
    [m/typography {:variant :body1
                   :paragraph true}
     "One of the most important aspects of therapy is the relationship between therapist and client - change doesn’t happen in isolation, it happens in connection.
     I strive to create a space where you feel comfortable being yourself, knowing that you’re not being judged but rather supported with empathy and understanding.
     Therapy is not a one-size-fits-all process, which is why I tailor my approach to each person I work with, drawing on different therapeutic models to best suit your needs and goals."]

    [m/stack {:direction {:xs :column
                          :lg :row}
              :justify-content :space-between}
     [m/typography {:variant :h5
                    :paragraph true
                    :sx {:mt 5
                         :font-style :italic
                         :color theme/subtitle}}
      "Reliable, confidential, goal-focused support."]
     [:img {:src "/images/BACP Logo.png"
            :style {:max-width
                    (if (or @(rf/subscribe [::bp/sm?]) @(rf/subscribe [::bp/mobile?]))
                      150
                      200)
                    :object-fit :contain}}]]]


   [m/grid {:item true
            :container true
            :justify-content :center
            :xs 12
            :md 4}
    [:img {:src "/images/adrian-wall.jpeg"
           :style {:object-fit :cover
                   :max-width "80%"
                   :max-height "420px"}}]]])

(defn li [text]
  [m/list-item
   [m/list-item-icon
    (r/as-element [icon/circle {:font-size "10px"}])]
   [m/list-item-text {:sx {:ml -2}}
    text]])

(defn about []
  [m/grid {:id "about"
           :container true
           :spacing 2
           :my 2
           :sx {:scroll-margin-top "60px"}}
   [m/grid {:item true
            :xs 12}
    [c/title :right (if @(rf/subscribe [::bp/mobile?])
                      "About"
                      "About My Practice")]]

   [m/grid {:item true
            :xs 12
            :md 3
            :display :flex
            :align-items :center
            :justify-content :center
            :mb 2}
    [:img {:src "/images/aj-room.jpeg"
           :style {:width "80%"
                   :max-width "50vw"}}]]

   [m/grid {:item true
            :xs 12
            :md 9}
    [m/typography {:variant :body1
                   :paragraph true}
     "As an integrative therapist, I am trained in Cognitive Behavioural Therapy (CBT), Psychodynamic Theory, and Person-Centred Therapy,
     and I am able to draw on any or all of these theoretical models based on what I feel would most benefit you as a client."]
    [m/typography {:variant :body1
                   :paragraph true}
     "Although I work with both men and women, I have a particular passion for supporting men in exploring and expressing their emotions.
     In my experience, many men have grown up in environments where vulnerability is discouraged,
     often leading to feelings of being disconnected from their emotions or finding their emotions expressed in negative ways.
     Over time, this can lead to struggles with relationships and self-esteem.
     I believe that therapy can help men break down these barriers, develop emotional awareness, and find new ways to express themselves that feel authentic and empowering."]]

   [m/grid {:item true
            :xs 12}
    [m/typography {:variant :h5}
     "I can help with..."]]
   [m/grid {:item true
            :xs 6
            :sm 3}
    [m/list {:dense true}
     [li "Abuse"]
     [li "Addictions"]
     [li "Anger management"]
     [li "Anxiety"]]]
   [m/grid {:item true
            :xs 6
            :sm 3}
    [m/list {:dense true}
     [li "Bereavement"]
     [li "Depression"]
     [li "Health related issues"]
     [li "Identity issues"]]]
   [m/grid {:item true
            :xs 6
            :sm 3}
    [m/list {:dense true}
     [li "Men's issues"]
     [li "Phobias"]
     [li "Post-traumatic stress"]
     [li "Redundancy"]]]
   [m/grid {:item true
            :xs 6
            :sm 3}
    [m/list {:dense true}
     [li "Relationships"]
     [li "Self-esteem"]
     [li "Sexuality"]
     [li "Stress"]]]])

(defn prices []
  [m/grid {:id "prices"
           :container true
           :spacing 2
           :column-spacing 3
           :my 2
           :sx {:scroll-margin-top "60px"}}
   [m/grid {:item true
            :xs 12}
    [c/title :left "Prices"]]

   [m/grid {:item true
            :xs 12
            :sm 6}
    [m/typography {:variant :h4
                   :color theme/text}
     "In-Person"
     [m/typography {:component :span
                    :variant :h3
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
                    :variant :h3
                    :color theme/subtitle
                    :pl 2}
      "£60"]]
    [m/typography {:variant :body1
                   :paragraph true}
     "I conduct online sessions using Google Meet, and always recommend that clients join the call
     from a quiet environment where we won't be disturbed."]]

   [m/grid {:item true
            :xs 12
            :sx {:mt 1}}
    [m/typography {:variant :body2}
     "Price is for a 50 minute individual counselling session. I accept payment by credit/debit card, contactless, or bank-transfer."]]])


(defn site []
  (r/create-class

    {:component-did-mount
     (fn [_]
       (rf/dispatch-sync [:set-boundaries])
       (.addEventListener js/document "scroll"
         #(reset! model/current-pos (.-scrollY js/window))))

     :render
     (fn [_]
       [m/box {:sx {:min-height "100vh"
                    :background-color theme/paper}}
        (if (< @(rf/subscribe [::bp/screen-width]) 600)
          [l/mobile-navbar]
          [l/navbar])
        [hero]
        [m/container {:max-width :xl}
         [offer]
         [about]
         [prices]
         [contact]]
        [l/footer]])}))
