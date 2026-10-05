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
(def title "#4A372C")
(def text "#261D17")
(def subtitle "#BE7451")
(def accent "#E6B600")
(def secondary "#F8D4A8")
(def paper "#FEF7E5")

(def forest "#143109")
(def sage "#AAAE7F")
(def beige "#D0D6B3")
(def pale "#E7EBCC")
(def ly "#FEFFE5")
(def olive "#5F7044")
(def onyx "#071103")
(def pumpkin "#ED7D3A")

(def theme-options
  {:palette {:primary {:main title}
             :secondary {:main secondary}
             :accent {:main pumpkin}}
   :typography {:fontFamily "'Figtree', sans-serif"
                :h1 {:color onyx
                     :fontWeight 500
                     :fontSize "3rem"
                     :fontFamily "'Josefin Sans', sans-serif"}
                :h2 {:color forest
                     :fontFamily "'Josefin Sans', sans-serif"}
                :h3 {:fontFamily "'Josefin Sans', sans-serif"}
                :h4 {:fontFamily "'Josefin Sans', sans-serif"}
                :body1 {:color onyx
                        :textAlign :justify}
                :quote {:color onyx
                        :fontSize "2rem"
                        :fontFamily "'IBM Plex Serif', 'Figtree', sans-serif"
                        :fontStyle :italic}}
   :components {:MuiButton
                {:variants
                 [{:props {}
                   :style {:borderRadius 10}}]}}})


(def app-theme
  (-> (original-mui-styles/createTheme (clj->js theme-options))
    (original-mui-styles/responsiveFontSizes)))