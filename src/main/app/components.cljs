(ns app.components
  (:require [app.model :as model]
            [app.theme :as theme]
            [re-frame.core :as rf]
            [reagent-mui.components :as m]
            [app.icons :as icon]
            [reagent.core :as r]
            [breaking-point.core :as bp]))

(defn title [position text & {:keys [title-color border-color]}]
  [m/divider {:component :div
              :text-align (if (model/handheld?)
                            :center
                            position)
              :sx {:mb 1
                   :mt 3
                   :width {:xs 1 :sm 0.8}
                   :float position
                   "&::before, &::after"
                   {:border-color (or border-color theme/ly)
                    :border-width :medium}}}
   [m/typography {:variant :h1
                  :color (or title-color nil)}
    text]])

(defn section [sx-map content]
  [m/box {:sx sx-map
          :pb 4}
   [m/container {:max-width :xl}
    content]])

(defn li [text & [icon]]
  [m/list-item
   [m/list-item-icon
    (if icon
      icon
      (r/as-element [icon/circle-filled {:sx {:fontSize "8px"}}]))]
   [m/list-item-text {:sx {:ml -2}}
    text]])

(defn read-more-block [{:keys [id visible read-more]}]
  (let [expanded (or @(rf/subscribe [:read-expanded id]) false)]
    [:<>
     [m/grid {:item true
              :xs 12}
      visible]

     [m/grid {:item true
              :xs 12}
      (if-not expanded
        [m/button {:on-click #(rf/dispatch [:read-more id])}
         "Read more..."]
        [m/box {:height "10px"}])
      [m/collapse {:in expanded
                   :orientation :vertical
                   :children
                   (r/as-element
                     [:<>
                      read-more
                      #_[m/button {:on-click #(rf/dispatch [:read-less id])}
                         "Read less"]])}]]]))