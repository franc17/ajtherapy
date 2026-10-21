(ns app.testimonials
  (:require [app.components :as c]
            [app.model :as model]
            [app.theme :as theme]
            [app.icons :as icons]
            [re-frame.core :as rf]
            [reagent-mui.components :as m]))

(def content
  [{:id 1
    :text
    "Adrian helped me process a separation with a thoughtful, professional mix of compassion, care and consideration of aspects I had not considered.
    He is an excellent therapist and a great person, I would wholeheartedly recommend his services."
    :author "Shaun"}
   {:id 2
    :text
    "Have had a lovely experience getting to work with Adrian so far. He’s kind, patient and empathetic, but also knows when to shut me up so that he can actually get a word in.
    Comfortable for him as a therapist from an LGBTQ+ perspective, which is hard to come by. Normally very hesitant with male therapist (sorry everyone else) but have had the exact opposite with Adrian
    - a really kind guy and gives good advice. Would definitely recommend!"
    :author "Michael"}
   {:id 3
    :text
    "I found Adrian really friendly and trustworthy, which made it easy to open up. He had a great sense of when to let me explore things myself and when to step in with practical guidance.
    I always left sessions feeling more stable and better able to ground myself when my thoughts spiral."
    :author "Joe"}
   {:id 4
    :text "Such an extremely welcoming, helpful and patient therapist. Would recommend 100%."
    :author "Eli"}
   {:id 5
    :text "I saw Adrian for help with my anxiety. I found his approach professional and insightful. I noticed an improvement in not only my anxiety issue but also my relationships,
     mood and sleep. I would absolutely recommend Adrian!"
    :author "Aaron"}
   {:id 6
    :text "Adrian is very kind and listens and has been very supportive and understanding."
    :author "Anon."}
   {:id 7
    :text "He's an amazing therapist who's helping through an incredibly tough time."
    :author "Anon."}
   {:id 8
    :text "Wonderful therapist. Very understanding and patient."
    :author "Anon."}
   {:id 9
    :text "Adrian is helping me to face my challenges head on and work through my problems."
    :author "Anon."}])


(defn card [{:keys [text author index]} visible-index]
  [m/paper {:sx {:display (if (= index visible-index) :block :none)
                 :bgcolor theme/forest
                 :box-shadow :none
                 :px {:xs 0 :sm 5}}
            #_#_:on-click #(rf/dispatch [:set-testimonial-visible (inc visible-index)])}
   [m/typography {:variant :quote
                  :text-align :center
                  :sx {:color theme/ly}}
    text]
   [m/typography {:color theme/ly
                  :text-align :end}
    author]])

(defn display []
  (let [visible-index (or @(rf/subscribe [:testimonial-visible]) 1)]
    [m/box {:sx {:width 0.75}
            :display :flex
            :justify-content :center}
     (map-indexed
       (fn [idx t]
         [:<> {:key (:id t)}
          [card (merge t {:index (+ 1 idx)}) visible-index]])
       content)]))

(defn testimonials []
  (let [visible-index (or @(rf/subscribe [:testimonial-visible]) 1)
        testimonial-count (count content)]
    [m/grid {:id "testimonials"
             :container true
             :spacing 0
             :column-spacing 3
             :min-height "40vh"}
     [m/grid {:item true
              :xs 12}
      [c/title :left "Testimonials"
       {:title-color theme/beige
        :border-color theme/sage}]]
     [m/grid {:item true
              :xs 12}
      [m/stack {:direction :row
                :width (if (model/handheld?) 1 0.75)
                :justify-content :center
                :justify-self :center}

       [m/icon-button {:disabled (= visible-index 1)
                       :sx {:visibility (if (= visible-index 1) :hidden :visible)}
                       :on-click #(rf/dispatch [:set-testimonial-visible (dec visible-index)])}
        [icons/arrow-left {:sx {:color theme/ly}}]]

       [display]

       [m/icon-button {:disabled (= visible-index testimonial-count)
                       :sx {:visibility (if (= visible-index testimonial-count) :hidden :visible)}
                       :on-click #(rf/dispatch [:set-testimonial-visible (inc visible-index)])}
        [icons/arrow-right {:sx {:color theme/ly}}]]]]]))

