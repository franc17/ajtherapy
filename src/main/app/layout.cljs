(ns app.layout
  (:require [app.model :as model]
            [clojure.string :as s]
            [reagent-mui.components :as m]
            [app.theme :as theme]
            [app.icons :as icon]
            [re-frame.core :as rf]
            [reitit.frontend.easy :as rfe]))

(defn nav-button [id text highlight]
  [m/stack {:align-items :center}
   [m/button {:variant :text
              :size :large
              :sx {:pb 0}
              :on-click #(rf/dispatch [:scroll-to id])}

    text]
   [m/box {:sx {:background-color
                (if (= highlight id)
                  theme/pumpkin
                  theme/ly)
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
    [m/box {:sx {:position :sticky
                 :top 0
                 :background-color theme/ly
                 :z-index 10
                 :pt 4
                 :pb 2}}
     [m/container {:max-width :xl}
      [m/stack {:direction :row
                :justify-content :space-between
                :align-items :center}

       [m/stack {:direction :row
                 :align-items :center
                 :spacing 2}
        [m/typography {:on-click #(rf/dispatch [:scroll-to :home])
                       :variant :h3
                       :color theme/forest
                       :sx {:pr 2
                            :cursor :pointer}}
         "Adrian Jackson Therapy"]]
       (when (= :home @(rf/subscribe [:current-page]))
         [m/stack {:direction :row}
          [nav-button :home "home" highlight]
          [nav-button :about "about" highlight]
          [nav-button :prices "fees" highlight]
          [nav-button :contact "contact" highlight]])]]]))

(defn mobile-item [id]
  [m/menu-item {:on-click #(rf/dispatch [:scroll-to id])}
   [m/typography
    (s/capitalize id)]])

(defn mobile-menu []
  [m/menu {:open (or @(rf/subscribe [:mobile-menu]) false)
           :on-close #(rf/dispatch [:set-mobile-menu false])
           :anchor-el (.getElementById js/document "menu-btn")
           :MenuListProps {:sx {:background-color theme/ly}}}
   [mobile-item "home"]
   [mobile-item "about"]
   [mobile-item "fees"]
   [mobile-item "contact"]])

(defn mobile-navbar []
  [m/app-bar {:position :sticky
              :sx {:background-color theme/ly
                   :p 1}}
   [m/stack {:direction :row
             :justify-content :space-between
             :align-items :center}
    [m/stack {:direction :row
              :spacing 0.5
              :align-items :center}
     [m/typography {:on-click #(rf/dispatch [:scroll-to :home])
                    :variant :h1
                    :font-size :medium}
      "Adrian Jackson Therapy"]]
    [:<>
     [m/icon-button {:on-click #(rf/dispatch [:set-mobile-menu true])
                     :id "menu-btn"
                     :sx {:p 0}}
      [icon/menu {:color :primary}]]
     [mobile-menu]]]])

(defn footer []
  [m/box {:id "footer"
          :sx {:background-color theme/ly}}
   [m/container {:max-width :xl
                 :sx {:py 1
                      :display :flex
                      :align-items :center
                      :justify-content :space-between}}
    [m/stack {:spacing 1
              :direction :row}
     [icon/copyright {:color :primary
                      :font-size :small}]
     [m/typography {:color :primary}
      "2026 FJB"]]

    [:img {:src "/images/BACP Logo.png"
           :style {:max-width 100
                   :object-fit :contain}}]]])


