(ns ^:figwheel-no-load reagent-secretary-example.dev
  (:require
    [reagent-secretary-example.core :as core]
    [devtools.core :as devtools]))

(enable-console-print!)

(devtools/install!)

(core/init!)
