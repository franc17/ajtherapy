(ns app.hosp-contact
  (:require [app.components :as c]
            [app.icons :as icon]
            [re-frame.core :as rf]
            [reagent-mui.components :as m]
            [reagent.core :as r]
            [fork.re-frame :as fork]
            [vlad.core :as v]))

(def form-validation
  (v/join
    (v/attr [:name]
      (v/chain
        (v/present)
        (v/length-over 2)))
    (v/attr [:role]
      (v/present))
    (v/attr [:company]
      (v/chain
        (v/present)
        (v/length-over 2)))))

(defn input [{:keys [values handle-change handle-blur errors touched]} name input-props]
  [m/text-field (merge {:name name
                        :value (values name)
                        :on-change handle-change
                        :on-blur handle-blur
                        :variant :standard
                        :helper-text (when (touched name)
                                       (get errors (list name)))}
                  input-props)])

(defn contact-form [{:keys [submitting? handle-submit] :as fork-props}]
  [m/stack {:id "hospitality-contact"
            :direction :column
            :spacing 2
            :align-items :flex-start
            :sx {:p 2
                 :border-radius "10px"
                 :border "1px solid black"
                 :width 0.8}}
   [m/stack {:direction {:xs :column :sm :row}
             :spacing 2
             :width 1}
    [input fork-props :name {:required true
                             :label "Your Name"
                             :sx {:width {:xs 1 :sm 0.6}}}]
    [input fork-props :role {:required true
                             :label "Role"
                             :sx {:width {:xs 1 :sm 0.4}}}]]
   [input fork-props :company {:full-width true
                               :required true
                               :label "Company Name"}]
   [m/stack {:direction {:xs :column :sm :row}
             :spacing 2
             :width 1}
    [input fork-props :email {:full-width true
                              :required true
                              :label "Email Address"}]
    [input fork-props :phone {:full-width true
                              :label "Contact Number"}]]
   [input fork-props :info {:full-width true
                            :multiline true
                            :rows 4
                            :label "Any Additional Information"}]
   [m/button {:type :submit
              :on-click handle-submit
              :disabled submitting?
              :variant :contained
              :full-width false
              :end-icon (r/as-element [icon/send])}
    "Submit"]])

(defn form-submitted []
  [m/stack {:direction :column
            :spacing 1
            :align-items :flex-start
            :sx {:min-height 400}}
   [m/typography {:variant :h5}
    "Thank you for your interest"]
   [m/typography {:variant :body1}
    "I will be in contact within the next few days to discuss further."]
   [m/button {:variant :outlined
              :full-width false
              :start-icon (r/as-element [icon/backspace])
              :on-click #(rf/dispatch [:set-hosp-submitted false])}
    "Submit another"]])

(defn contact []
  [m/grid {:id "hosp-contact"
           :container true
           :spacing 3
           :mt 2
           :mb 6}
   [m/grid {:item true
            :xs 12}
    [c/title :right "Contact"]]
   [m/grid {:item true
            :container true
            :justify-content :center
            :xs 12
            :md 6}
    (if @(rf/subscribe [:hosp-submitted])
      [form-submitted]
      [fork/form {:path [:hosp-form]
                  :form-id "hospitality-contact"
                  :prevent-default? true
                  :clean-on-unmount? true
                  :keywordize-keys true
                  :validation #(v/field-errors form-validation %)
                  :on-submit #(rf/dispatch [:hosp-submit %])}
       (fn [fork-props]
         [contact-form fork-props])])]
   [m/grid {:item true
            :xs 12
            :md 6}
    [m/typography {:variant :body1
                   :paragraph true}
     "If you’d like to book a session for your team or find out more, I’d love to chat."]
    [m/typography {:variant :body1
                   :paragraph true}
     "Fill in the form and I'll contact you with more information, or you can email me directly at "
     [m/link {:href "mailto:hospitality@ajtherapy.uk"
              :target "_blank"}
      "hospitality@ajtherapy.uk"] "."]]])
