(ns tbfas.core
  (:require [tbfas.components.table :as table]
            [tbfas.apis.json :as json]
            [reagent.core :as reagent]
            [reagent.session :as session]))

(defn component []
  (let [component-did-mount #(json/update-session-from-urls [{:url "/data/site.json"
                                                              :key :site}
                                                             {:url "/data/table.json"
                                                              :key :table}])
        component-render #(let [caption (session/get-in [:site "title"])
                                columns (keys (first (session/get :table)))
                                rows (session/get :table)
                                col-span (count columns)]
                            [table/table {:name caption
                                          :columns columns
                                          :rows rows}])]
    (reagent/create-class
     {:component-did-mount component-did-mount
      :reagent-render component-render})))

(def root
  (. js/document querySelector "#root"))

(reagent/render [component]
                root)
