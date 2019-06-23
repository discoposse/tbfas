(ns tbfas.components.table
  "Components for generating a table.")

(defn table
  "Render a table with m as table structure."
  [m]
  (let [{:keys [name
                columns
                rows]} m]
    [:table
     [table-caption name]
     [table-thead columns]
     [table-tbody rows]
     [table-tfoot (count columns)]]))

(defn table-caption
  "Render a table caption with s as its text."
  [s]
  [:caption s])

(defn table-thead
  "Render a table thead with coll as its table columns."
  [coll]
  [:thead
   [:tr
    (for [s coll]
      [:th {:key (random-uuid)} s])]])

(defn table-tbody
  "Render a table tbody with coll as its table rows."
  [coll]
  [:tbody
   (for [m coll]
     [:tr {:key (random-uuid)}
      (for [v (vals m)]
        [:td {:key (random-uuid)}
         (cond
           (vector? v) [:ol
                        (for [vv v]
                          [:li {:key (random-uuid)} vv])]
           :else v)])])])

(defn table-tfoot
  "Render an empty table tfoot with n as colspan of its td."
  [n]
  [:tfoot
   [:tr
    [:td {:colSpan n}]]])
