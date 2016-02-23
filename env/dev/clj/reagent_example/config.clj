(ns reagent-example.config
  (:require [selmer.parser :as parser]
            [clojure.tools.logging :as log]
            [reagent-example.dev-middleware :refer [wrap-dev]]))

(def defaults
  {:init
   (fn []
     (parser/cache-off!)
     (log/info "\n-=[reagent-example started successfully using the development profile]=-"))
   :middleware wrap-dev})
