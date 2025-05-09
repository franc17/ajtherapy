(ns app.contact
  (:require [app.icons :as icon]
            [re-frame.core :as rf]
            [reagent-mui.components :as m]
            [app.components :as c]
            [app.theme :as theme]
            [reagent.core :as r]))

(defn balham-map []
  [:iframe {:src "https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d2486.528450739866!2d-0.15236862306759896!3d51.448453315007505!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x487605d1c31f3d41%3A0xb39f6f9fcae38cfc!2sBalham%20Community%20Centre!5e0!3m2!1sen!2suk!4v1744101556497!5m2!1sen!2suk"
            :width "100%" :height "100%" :style {:border 0} :allowFullScreen "" :loading "lazy" :referrerPolicy "no-referrer-when-downgrade"}])

(defn calendar-collapse [calendar-visible]
  [m/grid {:item true
           :xs 12
           :sm 12
           :sx {:margin-bottom (if calendar-visible "100px" "400px")}}
   [m/collapse {:in calendar-visible}
    [m/box {:id "cal"
            :sx {:height 700}}
     [:div {:class "calendly-inline-widget"
            :data-url
            "https://calendly.com/ajtherapyuk/30min?text_color=002923&primary_color=00A480&hide_gdpr_banner=1"
            :data-resize "true"
            :style {:min-width "100%"
                    :height "100%"}}]]]])

[m/grid {:item true
         :container true
         :spacing 2
         :xs 12
         :md 8}
 [m/typography
  "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua.
  Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat."]
 [m/grid {:item true
          :xs 6
          :md 4}
  [m/typography {:variant :h5}
   "hello@ajtherapy.uk"]]]


(defn contact []
  (let [calendar-visible (or @(rf/subscribe [:calendar]) false)]
    [m/grid {:id "contact"
             :container true
             :row-spacing 2
             :column-spacing 2
             :my 2
             :sx {:scroll-margin-top "60px"}}
     [m/grid {:item true
              :xs 12}
      [c/title :right "Contact"]]

     #_[m/grid {:item true
                :xs 12
                :md 8
                :sx {:pr 1}}
        [m/typography {:variant :body1
                       :paragraph true}
         "I offer individual counselling sessions both online and face-to-face.
       Sessions are held weekly for 50 minutes in a secure and confidential environment,
       providing a safe space for you to explore your thoughts and emotions."]
        [m/typography {:variant :body1
                       :paragraph true}
         "In-person counselling takes place in a comfortable and private therapy room at Balham Community Centre in Southwest London."]
        [m/typography {:variant :body1
                       :paragraph true}
         "I conduct online sessions using Google Meet, and always recommend that clients join the call from a quiet environment where we won't be disturbed."]]
     [m/grid {:item true
              :xs 12
              :md 6}
      [m/stack {:direction :row
                :spacing 2
                :align-items :center}
       [icon/email {:sx {:color theme/subtitle}}]
       [m/typography {:variant :h4}
        "hello@ajtherapy.uk"]]

      [m/stack {:direction :row
                :spacing 2
                :align-items :start
                :mt 3}
       [icon/place {:sx {:color theme/subtitle}}]
       [m/typography {:variant :h6
                      :sx {:white-space :pre}}
        "Balham Community Centre\n"
        "Alderbrook Primary School\n"
        "Oldridge Rd\n"
        "SW12 8PP"]]]
     [m/grid {:item true
              :xs 12
              :md 6}
      [balham-map]]


     [m/grid {:item true
              :xs 12
              :mt 2}
      [m/typography {:variant :h5}
       "Book a free 20 minute consultation"]]
     [m/grid {:item true
              :xs 12}
      [m/typography {:variant :body1
                     :paragraph true}
       "I offer a free no-obligation chat via phone or video call where we can discuss your reasons for considering therapy
       and go over any questions you might have."]
      [m/typography {:variant :body1
                     :paragraph true}
       "You can contact me by email to arrange, or book a slot directly in my calendar below."]

      (if calendar-visible
        [m/button {:variant :outlined
                   :end-icon (r/as-element [icon/arrow-up])
                   :on-click #(rf/dispatch [:set-calendar false])}
         "Hide Calendar"]
        [m/button {:variant :contained
                   :end-icon (r/as-element [icon/arrow-down])
                   :on-click #(rf/dispatch [:set-calendar true])}
         "Show My Calendar"])]

     [calendar-collapse calendar-visible]]))

