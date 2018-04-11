(ns reagent-secretary-example.prod
  (:require
    [reagent-secretary-example.core :as core]))

;;ignore println statements in prod
(set! *print-fn* (fn [& _]))

(core/init!)
