(ns app.hospitality
  (:require [app.components :as c]
            [app.hosp-contact :refer [contact]]
            [app.icons :as icon]
            [app.layout :as l]
            [app.theme :as theme]
            [re-frame.core :as rf]
            [reagent-mui.components :as m]
            [breaking-point.core :as bp]
            [reagent.core :as r]))

(defn tick [text]
  [c/li text (r/as-element [icon/check])])

(defn intro []
  [m/grid {:container true
           :row-spacing 2
           :column-spacing 3
           :my 2}
   [m/grid {:item true
            :xs 12}
    [c/title :left "Supporting Mental Health in Hospitality"]]
   [m/grid {:item true
            :xs 12}
    [m/typography {:variant :body1
                   :paragraph true}
     "Hi, I’m Adrian, a BACP-registered therapist with a background in hospitality and a passion for supporting mental
     well-being in the industry. Before becoming a therapist, I spent 15 years working in hospitality, so I know firsthand
     how tough it can be. The long hours, high-pressure environment, customer demands, and lack of downtime can take a
     serious toll on mental health. Unfortunately, the industry often normalises stress, burnout, and even unhealthy
     coping mechanisms, leaving many hospitality workers struggling in silence."]]
   [m/grid {:item true
            :xs 12
            :md 8}
    [m/typography {:variant :body1
                   :paragraph true}
     "The reality is, hospitality has one of the highest rates of work-related stress, anxiety, and depression. Studies show that:"]
    [c/li "Over 80% of hospitality workers report feeling stressed at work."]
    [c/li "One in five hospitality workers struggle with severe mental health issues, including depression and anxiety."]
    [c/li "More than half (57%) have considered leaving the industry due to the impact on their mental health."]
    [c/li "Hospitality has a higher rate of substance misuse than most other industries, often as a way to cope with workplace pressures."]]
   ;https://www.hospitalityaction.org.uk/blog-and-news/2024/july/taking-the-temperature-the-results-are-in/
   [m/grid {:container true
            :item true
            :xs 12
            :md 4
            :justify-content :center}
    [:img {:src "/images/rose-pour.jpg"
           :title "Photo by Mika Baumeister on Unsplash"
           :style {:object-fit :contain
                   :max-width "80%"
                   :max-height "420px"}}]]
   [m/grid {:item true
            :xs 12}
    [m/typography {:variant :body1
                   :paragraph true}
     "These statistics highlight a serious issue, but the good news is that with the right support, things can change.
     That’s why I bring on-site mental health support directly to your team through Employee Assistance Programme (EAP) sessions
     and practical resources designed specifically for hospitality staff."]]])

(defn why-it-matters []
  [m/grid {:id "matters"
           :container true
           :spacing 2
           :my 2}
   [m/grid {:item true
            :xs 12}
    [c/title :right "Why it Matters for You"]]
   [m/grid {:container true
            :item true
            :xs 12
            :md 3
            :justify-content :center}
    [:img {:src "/images/bar-smile.jpg"
           :title "Photo by Kike Salazar N on Unsplash"
           :style {:object-fit :contain
                   :max-width "100%"
                   :max-height "420px"}}]]
   [m/grid {:item true
            :xs 12
            :md 9}
    [m/typography {:variant :body1
                   :paragraph true}
     "Supporting staff mental health isn’t just the right thing to do—it also makes good business sense. When employees
     feel valued and supported, they’re more engaged, more productive, and less likely to leave."]
    [m/typography {:variant :body1
                   :paragraph true}
     "Here’s how investing in mental health can benefit your business:"]
    [tick "Reduced staff turnover - A happy, supported team is more likely to stay, saving you time and money on recruitment."]
    [tick "Lower absenteeism - When mental health is prioritized, staff are less likely to need time off due to stress or burnout."]
    [tick "Better team morale - Creating a supportive work culture leads to better communication, teamwork, and job satisfaction."]
    [tick "Improved customer service - A team that feels good delivers better service, leading to happier customers and better reviews."]
    [m/typography {:variant :body1
                   :paragraph true
                   :sx {:mt 2}}
     "I know that in hospitality, time is precious, and it’s easy for mental health to take a backseat. But investing
     in staff well-being isn’t just a ‘nice-to-have’ - it’s essential for a thriving, successful business. By giving
     your team the tools to manage stress and prioritize their wellbeing, you’re creating a workplace where people feel
     supported, valued, and motivated."]]])

(defn what-i-offer []
  [m/grid {:id "hosp-offer"
           :container true
           :spacing 2
           :my 2}
   [m/grid {:item true
            :xs 12}
    [c/title :left "What I Offer"]]
   [m/grid {:item true
            :xs 12
            :md 8}
    [m/typography {:variant :body1
                   :paragraph true}
     "I visit restaurants and hospitality venues to deliver engaging, practical 60-minute mental health talks that focus on:"]
    [c/li "Understanding stress and burnout in hospitality."]
    [c/li "Recognizing early signs of mental health struggles."]
    [c/li "Practical techniques to build resilience and manage workplace stress."]
    [c/li "Encouraging healthy coping mechanisms instead of relying on unhealthy habits."]
    [c/li "Creating a more supportive team culture."]]
   [m/grid {:container true
            :item true
            :xs 12
            :md 4
            :justify-content :center}
    [:img {:src "/images/sitting-talking.jpg"
           :title "Photo by Derek Coleman on Unsplash"
           :style {:object-fit :contain
                   :max-width "100%"
                   :max-height "420px"}}]]
   [m/grid {:item true
            :xs 12}
    [m/typography {:variant :body1
                   :paragraph true}
     "At the end of the session, I open the floor for questions, giving staff a chance to raise any concerns in a safe,
     judgment-free environment. Each team member will also receive a "
     [:b "take home booklet"]
     " filled with useful resources, coping
     strategies, and information on where to seek further support if needed."]]])

(defn page []
  [m/box {:sx {:min-height "100vh"
               :background-color theme/secondary}}
   (if (< @(rf/subscribe [::bp/screen-width]) 600)
     [l/mobile-navbar]
     [l/navbar])
   [m/container {:max-width :xl}
    [intro]
    [why-it-matters]
    [what-i-offer]
    [contact]]
   [l/footer]])