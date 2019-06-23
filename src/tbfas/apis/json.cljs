(ns tbfas.apis.json
  "Helper functions to manage JSON APIs."
  (:require [reagent.session :as session]))

(defn update-session-from-url
  "Update reagent session from map of url and key identifier."
  [m]
  (let [{:keys [url key]} m]
    (.. (js/fetch url)
        (then #(. % json))
        (then #(session/put! key (js->clj %))))))

(defn update-session-from-urls
  "Update reagent session from xs of url and key identifier pairs."
  [xs]
  (doseq [m xs]
    (update-session-from-url m)))
