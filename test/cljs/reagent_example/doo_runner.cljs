(ns reagent-example.doo-runner
  (:require [doo.runner :refer-macros [doo-tests]]
            [reagent-example.core-test]))

(doo-tests 'reagent-example.core-test)

