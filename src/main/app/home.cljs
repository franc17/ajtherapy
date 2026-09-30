(ns app.home
  (:require [app.model :as model]
            [app.about :as about]
            [app.cost :as cost]
            [re-frame.core :as rf]
            [reagent-mui.components :as m]
            [app.layout :as l]
            [app.components :as c]
            [app.theme :as theme]
            [reagent.core :as r]
            [app.contact :refer [contact]]
            [breaking-point.core :as bp]))

(defn hero []
  [m/stack {:direction {:xs :column :md :row}
            :justify-content :space-evenly
            :spacing {:xs 4 :md 10}
            :py {:xs 5 :md 10}}
   [:img {:src "/images/aj-shoulder.jpg"
          :style {:max-height "75vh"
                  :object-fit :contain
                  :border-radius "25px"}}]
   [m/stack {:spacing 4
             :justify-content :space-evenly
             :align-items :center}
    [m/typography {:variant :h2
                   :sx {:font-style :italic
                        :color theme/forest}}
     "Reliable, confidential, goal-focused support."]
    [m/typography {:variant :h4
                   :sx {:color theme/olive}}
     "BACP registered counsellor working online and in South West London."]

    [m/button {:variant :outlined
               :sx {:text-transform :none
                    :p 2}
               :on-click #(rf/dispatch [:scroll-to "contact"])}
     [m/typography {:variant :h5}
      "Get in Touch"]]]])

(def main-content
  [m/typography {:variant :body1
                 :paragraph true}
   "Hi, I’m Adrian, an integrative therapist providing a warm and supportive space to help you explore a range of challenges.
    Whether you might be experiencing anxiety, depression, trauma or low self-esteem, I'm here to support your journey.
    I use theoretical models to help you recognise patterns, regulate emotions, and move toward meaningful, lasting change.
    I see therapy as a collaborative process - one where you’re truly heard, not judged or directed. Together, we’ll work to
    deepen self-awareness, build resilience, and support you in creating a more fulfilling life."])

(def additional-content
  [:<>
   [m/typography {:variant :body1
                  :paragraph true}
    "For me, therapy isn’t about giving advice or telling you what to do - it’s about working together to explore your
    thoughts, emotions, and patterns so that you can better understand yourself and move towards the life you want.
    My role is to facilitate that process by providing a safe, supportive, and non-judgmental space where you feel truly heard."]
   [m/typography {:variant :body1
                  :paragraph true}
    "One of the most important aspects of therapy is the relationship between therapist and client - change doesn’t happen in isolation, it happens in connection.
    I strive to create a space where you feel comfortable being yourself, knowing that you’re not being judged but rather supported with empathy and understanding.
    Therapy is not a one-size-fits-all process, which is why I tailor my approach to each person I work with, drawing on different therapeutic models to best suit your needs and goals."]])


(defn approach []
  (let [mobile (model/handheld?)]
    [m/grid {:id "home"
             :container true
             :row-spacing 2
             :column-spacing 2
             :sx {:scroll-margin-top "60px"}}

     [m/grid {:item true
              :xs 12}
      [c/title :left "My Approach"]]

     (if mobile
       [c/read-more-block
        {:id :approach
         :visible
         main-content
         :read-more
         additional-content}]

       [:<>
        [m/grid {:item true
                 :xs 12
                 :md 8}
         main-content
         additional-content]

        [m/grid {:item true
                 :container true
                 :justify-content :center
                 :xs 12
                 :md 4}
         [:img {:src "/images/adrian-wall.jpeg"
                :style {:object-fit :cover
                        :max-width "80%"
                        :max-height "420px"}}]]])]))


(defn above-footer []
  [m/stack {:direction :column
            :spacing 2
            :justify-content :flex-start
            :align-items :flex-end
            :sx {:pt 1
                 :pb 2}}
   [:img {:src "/images/BACP Logo.png"
          :style {:max-width
                  (if (or @(rf/subscribe [::bp/sm?]) @(rf/subscribe [::bp/mobile?]))
                    150
                    200)
                  :object-fit :contain}}]
   [:img {:src "/images/pt-sample.png"
          :style {:max-width
                  (if (or @(rf/subscribe [::bp/sm?]) @(rf/subscribe [::bp/mobile?]))
                    150
                    200)
                  :object-fit :contain}}]])

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
                    :background-color theme/ly}}
        (if (model/handheld?)
          [l/mobile-navbar]
          [l/navbar])

        [c/section {:bgcolor theme/pale}
         [hero]]

        [c/section {:bgcolor theme/sage}
         [approach]]

        [c/section {:bgcolor theme/beige}
         [about/about]]

        [c/section {:bgcolor theme/pale}
         [about/help-with]]

        [c/section {:bgcolor theme/beige}
         [cost/prices]]

        [c/section {:bgcolor theme/olive}
         [contact]]

        [l/footer]])}))
