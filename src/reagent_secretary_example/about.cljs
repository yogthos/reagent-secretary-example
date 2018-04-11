(ns reagent-secretary-example.about
  (:require
   [accountant.core :as accountant]))

(defn about-page []
  [:div
   [:p "This is the about page"]
   [:button
    {:on-click #(accountant/navigate! "/")}
    "home"]])
