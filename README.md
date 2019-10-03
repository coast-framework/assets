# assets

Easy clojure asset bundling

## Quickstart

## Install

Add this to your [deps.edn](https://clojure.org/guides/deps_and_cli)

```clojure
coast-framework/db {:mvn/version "1.0.0"}
```

## Usage
Create an `assets.edn` file in the `resources` directory that looks something like this:

```clojure
{"bundle.css" ["tachyons.min.css" "app.css"]
 "bundle.js"  ["app.js"]})
```

You can have as many "bundles" as you want and you can name them anything. Ensure that your css files go into the `resources/public/css` directory and the js files go into the `resources/public/js` directory.

You can reference your bundles with some clojure code like below, but it's entirely up to you

```clojure
(ns your-proj
  (:require [assets.core :as assets]))

(defn css
  ([req bundle opts]
   (let [files (assets/bundle "dev" bundle)]
     (for [href files]
       [:link (merge {:href href :type "text/css" :rel "stylesheet"} opts)])))
  ([req bundle]
   (css nil bundle {}))
  ([bundle]
   (css nil bundle)))


(defn js
  ([req bundle opts]
   (let [files (assets/bundle "dev" bundle)]
     (for [src files]
      [:script (merge {:src src :type "application/javascript"} opts)])))
  ([req bundle]
   (js nil bundle {}))
  ([bundle]
   (js nil bundle)))
```

When the `bundle` fn is called, the `assets.edn` file is read or if you pass `"prod"` as the first argument to `bundle`, the `assets.minified.edn` file is read.

You can generate the `assets.minified.edn` file by calling something like the following code:

```clojure
(let [m (-> (io/resource "assets.edn")
              (slurp)
              (edn/read-string))]
    (pprint-write "resources/assets.minified.edn" (build m)))
```

If this seems kind of weird and half-baked, it kind of is, this library was built to be used with coast and it hasn't been fully separated out yet. PRs welcome!

## Running the tests

```bash
make test
```
