(ns guestbook.pages.guest-list
  (:require [reagent.session :as session]
            [clojure.string :as s]
            [reagent.core :as reagent :refer [atom]]
            [secretary.core :refer [dispatch!]]))

(defn guest-list-page []
  [:div
   [:div.page-header [:h2 "Guests"]]
   (for [{:keys [first-name last-name]}
         (session/get :guests)]
     [:div.row
      [:p first-name " " last-name]])
   [:button {:type "submit"
             :class "btn btn-default"
             :on-click #(dispatch! "/sign-in")}
    "sign in"]])
