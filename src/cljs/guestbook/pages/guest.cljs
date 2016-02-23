(ns guestbook.pages.guest
  (:refer-clojure :exclude [get])
  (:require [reagent.session :as session]
            [reagent.core :as reagent :refer [atom]]
            [secretary.core :refer [dispatch!]]
            [ajax.core :refer [POST]]))

(defn put! [doc id value]
  (swap! doc assoc :saved? false id value))

(defn get [doc id]
  (id @doc))

(defn row [label & body]
  [:div.row
   [:div.col-md-2 [:span label]]
   [:div.col-md-3 body]])

(defn text-input [doc id label]
  [row label
   [:input {:type "text"
            :class "form-control"
            :value (get doc id)
            :onChange #(put! doc id (-> % .-target .-value))}]])

(defn save-doc [doc]
  (POST (str js/context "/save")
        {:params (dissoc @doc :saved?)
         :handler
         (fn [_]
           (put! doc :saved? true)
           (session/update-in! [:guests] conj @doc)
           (dispatch! "/"))}))

(defn guest-page []
  (let [doc (atom {})]
    (fn []
      [:div
       [:div.page-header [:h1 "Sign In"]]

       [text-input doc :first-name "First name"]
       [text-input doc :last-name "Last name"]

       (if (get doc :saved?)
         [:p "Saved"]
         [:button {:type "submit"
                   :class "btn btn-default"
                   :on-click #(save-doc doc)}
          "Submit"])
       [:button {:type "submit"
                 :class "btn btn-default"
                 :on-click #(dispatch! "/")} "back"]])))

