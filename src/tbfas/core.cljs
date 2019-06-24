(ns tbfas.core
  (:require [tbfas.components.table :as table]
            [tbfas.apis.json :as json]
            [reagent.core :as reagent]
            [reagent.session :as session]
            [cljsjs.jquery]
            [cljsjs.datatables.net]))

(defn document-ready [f]
  (. (js/$ js/document)
     (ready f)))

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
                                          :rows rows}])
        component-did-update (fn []
                               (document-ready
                                #(. (js/$ "table")
                                    DataTable)))]
    (reagent/create-class
     {:component-did-mount component-did-mount
      :reagent-render component-render
      :component-did-update component-did-update})))

(def root
  (. js/document querySelector "#root"))

(reagent/render [component]
                root)
