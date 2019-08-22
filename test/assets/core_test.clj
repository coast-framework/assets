(ns assets.core-test
  (:require [assets.core :as assets]
            [clojure.test :refer [deftest testing is]]))

(def assets-edn
  {"bundle.css" ["tachyons.min.css" "app.css"]
   "bundle.js"  ["app.js"]})

(deftest test-paths
  (testing "test basic case"
    (is (assets/paths "css" ["a.css" "b.css"])
        ["resources/public/css/a.css" "resources/public/css/b.css"])))
