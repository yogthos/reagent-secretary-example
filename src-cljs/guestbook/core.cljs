(ns guestbook.core
 (:require [guestbook.session :as session]
           [guestbook.pages.guest-list
            :refer [guest-list-page]]
           [guestbook.pages.guest :refer [guest-page]]
           [reagent.core :as reagent :refer [atom]]
           [secretary.core :as secretary
             :include-macros true :refer [defroute]]
           [goog.events :as events]
           [goog.history.EventType :as EventType]))

(defn hook-browser-navigation! []
  (doto (History.)
    (events/listen
      EventType/NAVIGATE
      (fn [event]
        (secretary/dispatch! (.-token event))))
    (.setEnabled true)))

(defroute "/" []
  (session/put! :current-page guest-list-page))
(defroute "/sign-in" []
  (session/put! :current-page guest-page))

(def current-page (atom nil))

(defn page []
  [(session/get :current-page)])

(defn init! []
  (hook-browser-navigation!)
  (secretary/set-config! :prefix "#")
  (session/put! :current-page guest-list-page)
  (reagent/render-component
   [page]
   (.getElementById js/document "app")))

(init!)
