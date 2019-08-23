(ns assets.core-test
  (:require [assets.core :refer [assets-dir ext hrefs md5 paths]]
            [clojure.test :refer [deftest is testing]]))

(def assets-edn
  {"bundle.css" ["tachyons.min.css" "app.css"]
   "bundle.js"  ["app.js"]})

(deftest test-md5
  (testing "same md5 digest"
    (is (= (md5 "xxx")
           (md5 "xxx")))
    (is (= (md5 "yyy")
           (md5 "yyy"))))
  (testing "different md5 digest"
    (is (not= (md5 "xxx")
              (md5 "yyy")))))

(deftest test-ext
  (testing "file with extension"
    (is (= (ext "app.css") "css"))
    (is (= (ext "app.js") "js")))
  (testing "file without extension"
    (is (nil? (ext "app")))))

(deftest test-hrefs
  (testing "basic case"
    (is (= (hrefs "css" ["a.css" "b.css"])
           ["/css/a.css" "/css/b.css"]))))

(deftest test-paths
  (testing "basic case"
    (is (paths "css" ["a.css" "b.css"])
        ["resources/public/css/a.css" "resources/public/css/b.css"])))

(deftest test-assets-dir
  (testing "basic case"
    (is (assets-dir) "resources/public/assets")))
