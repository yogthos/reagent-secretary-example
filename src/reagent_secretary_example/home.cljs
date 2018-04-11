(ns reagent-secretary-example.home
  (:require
   [accountant.core :as accountant]))

(defn home-page []
  [:div
   [:h2 "Welcome to Reagent"]
   [:p "This is the home page"]
   [:button
    {:on-click #(accountant/navigate! "/about")}
    "about"]])
