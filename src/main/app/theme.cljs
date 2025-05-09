(ns app.theme
  (:require ["@mui/material/styles" :as original-mui-styles]))

;Breakpoints
(def breakpoints
  [:mobile 480
   :sm 760
   :m 900
   :l 1200
   :xl 1500])

;Palette
(def title "#003A32")
(def text "#002923")
(def subtitle "#00A480")
(def accent "#E6B600")
(def secondary "#C5DBE3")
(def paper "#EEE5DB")

(def theme-options
  {:palette {:primary {:main title}
             :secondary {:main secondary}
             :accent {:main accent}}
   :typography {:fontFamily "'Josefin Sans', sans-serif"
                :h1 {:color title
                     :fontWeight 500
                     :fontSize "3rem"}
                :h2 {:color subtitle
                     :font-weight :medium}}
   :components {:MuiButton
                {:variants
                 [{:props {}
                   :style {:borderRadius 10}}]}}})


(def app-theme
  (-> (original-mui-styles/createTheme (clj->js theme-options))
    (original-mui-styles/responsiveFontSizes)))