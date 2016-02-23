(ns reagent-example.routes.services
  (:use compojure.core)
  (:require [reagent-example.layout :as layout]
            [clojure.pprint :refer [pprint]]))

(defn save-document [doc]
  (pprint doc)
  {:status "ok"})

(defroutes service-routes
  (POST "/save" {:keys [body-params]}
        (save-document body-params)))
