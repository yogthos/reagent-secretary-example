(ns reagent-example.config
  (:require [clojure.tools.logging :as log]))

(def defaults
  {:init
   (fn []
     (log/info "\n-=[reagent-example started successfully]=-"))
   :middleware identity})
