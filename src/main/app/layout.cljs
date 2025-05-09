(ns app.layout
  (:require [app.model :as model]
            [clojure.string :as s]
            [reagent-mui.components :as m]
            [app.theme :as theme]
            [app.icons :as icon]
            [reagent.core :as r]
            [re-frame.core :as rf]))

(defn nav-button [id text highlight]
  [m/stack {:align-items :center}
   [m/button {:variant :text
              :size :large
              :sx {:pb 0}
              :on-click #(rf/dispatch [:scroll-to id])}

    text]
   [m/box {:sx {:background-color
                (if (= highlight id)
                  theme/accent
                  theme/secondary)
                :height 2
                :width 0.5
                :border-radius 10}}]])

(defn navbar []
  (let [{:keys [about prices contact]} @(rf/subscribe [:boundaries])
        highlight (cond
                    (< @model/current-pos about) :home
                    (and (>= @model/current-pos about) (< @model/current-pos prices)) :about
                    (and (>= @model/current-pos prices) (< @model/current-pos contact)) :prices
                    (> @model/current-pos contact) :contact)]
    [m/paper {:sx {:position :sticky
                   :top 0
                   :background-color theme/secondary
                   :z-index 10
                   :pt 0.5}}
     [m/container {:max-width :xl}
      [m/stack {:direction :row
                :justify-content :space-between
                :align-items :center}
       [m/stack {:direction :row
                 :align-items :center
                 :spacing 2}
        [m/typography {:variant :h1
                       :sx {:pr 2}}
         "AJ Therapy"]
        [nav-button :home "home" highlight]
        [nav-button :about "about" highlight]
        [nav-button :prices "prices" highlight]
        [nav-button :contact "contact" highlight]
        #_[m/button {:variant :text
                     :size :large
                     :sx {:pb 0}
                     :end-icon (r/as-element [icon/open-in-new {:sx {:pb 2}}])}
           "hospitality counselling"]]]]]))

(defn mobile-item [id]
  [m/menu-item {:on-click #(rf/dispatch [:scroll-to id])}
   (s/capitalize id)])

(defn mobile-menu []
  [m/menu {:open (or @(rf/subscribe [:mobile-menu]) false)
           :on-close #(rf/dispatch [:set-mobile-menu false])
           :anchor-el (.getElementById js/document "menu-btn")}
   [mobile-item "home"]
   [mobile-item "about"]
   [mobile-item "prices"]
   [mobile-item "contact"]
   #_[m/menu-item "Hospitality Counselling"
      [icon/open-in-new {:sx {:pb 2}}]]])

(defn mobile-navbar []
  [m/app-bar {:position :sticky
              :sx {:background-color theme/secondary
                   :p 1}}
   [m/stack {:direction :row
             :justify-content :space-between
             :align-items :center}
    [m/icon-button {:on-click #(rf/dispatch [:set-mobile-menu true])
                    :id "menu-btn"
                    :sx {:p 0}}
     [icon/menu]]
    [mobile-menu]
    [m/typography {:variant :h1
                   :font-size :medium}
     "AJ Therapy"]]])



(defn footer []
  [m/box {:id "footer"
          :sx {:background-color theme/title}}
   [m/container {:max-width :xl
                 :sx {:py 1
                      :display :flex
                      :align-items :center
                      :justify-content :space-between}}
    [m/typography {:variant :h4
                   :color theme/secondary} "AJ Therapy"]
    [m/stack {:spacing 1
              :direction :row}
     [icon/copyright {:color :secondary
                      :font-size :small}]
     [m/typography {:color :secondary}
      "2025 "
      [m/link {:color :secondary
               :sx {:cursor :pointer}}
       "FJB"]]]]])

