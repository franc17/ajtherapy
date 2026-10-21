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
    "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod."}
   {:id 2
    :text
    "Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit."}
   {:id 3
    :text
    "Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur."}])

(defn card [{:keys [text index]} visible-index]
  [m/paper {:sx {:display (if (= index visible-index) :block :none)
                 :bgcolor theme/forest
                 :box-shadow :none
                 :px {:xs 0 :sm 5}}
            #_#_:on-click #(rf/dispatch [:set-testimonial-visible (inc visible-index)])}
   [m/typography {:variant :quote
                  :text-align :center
                  :sx {:color theme/ly}}
    text]])

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

