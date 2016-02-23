(ns reagent-example.handler
  (:require [compojure.core :refer [defroutes routes wrap-routes]]
            [reagent-example.layout :refer [error-page]]
            [reagent-example.routes.home :refer [home-routes]]
            [reagent-example.routes.services :refer [service-routes]]
            [reagent-example.middleware :as middleware]
            [clojure.tools.logging :as log]
            [compojure.route :as route]
            [config.core :refer [env]]
            [reagent-example.config :refer [defaults]]
            [mount.core :as mount]
            [luminus.logger :as logger]))

(defn init
  "init will be called once when
   app is deployed as a servlet on
   an app server such as Tomcat
   put any initialization code here"
  []
  (logger/init env)
  (doseq [component (:started (mount/start))]
    (log/info component "started"))
  ((:init defaults)))

(defn destroy
  "destroy will be called when your application
   shuts down, put any clean up code here"
  []
  (log/info "reagent-example is shutting down...")
  (doseq [component (:stopped (mount/stop))]
    (log/info component "stopped"))
  (log/info "shutdown complete!"))

(def app-routes
  (routes
    (wrap-routes #'service-routes middleware/wrap-csrf)
    (wrap-routes #'home-routes middleware/wrap-csrf)
    (route/not-found
      (:body
        (error-page {:status 404
                     :title "page not found"})))))

(def app (middleware/wrap-base #'app-routes))
