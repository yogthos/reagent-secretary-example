(ns reagent-secretary-example.core
  (:require
    [reagent-secretary-example.about :refer [about-page]]
    [reagent-secretary-example.home :refer [home-page]]
    [reagent.core :as r]
    [secretary.core :as secretary :include-macros true]
    [accountant.core :as accountant]
    [goog.events :as events]
   [goog.history.EventType :as HistoryEventType])
  (:import goog.History))

(def selected-page (r/atom home-page))

(defn page []
  [@selected-page])
;; -------------------------
;; Routes

(secretary/defroute "/" []
  (reset! selected-page home-page))

(secretary/defroute "/about" []
  (reset! selected-page about-page))

  ;; -------------------------
  ;; History
  ;; must be called after routes have been defined

(defn hook-browser-navigation! []
  (doto (History.)
        (events/listen
         HistoryEventType/NAVIGATE
         (fn [event]
           (secretary/dispatch! (.-token event))))
        (.setEnabled true)))

;; -------------------------
;; Initialize app

(defn mount-root []
  (r/render [page] (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!
   {:nav-handler
    (fn [path]
      (secretary/dispatch! path))
    :path-exists?
    (fn [path]
      (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))
