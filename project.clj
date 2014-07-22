(defproject radix-math "1.0.0"
  :description "Clojure library to convert integers to and from arbitrary radixes."
  :url "https://github.com/torsten/radix-math"
  :min-lein-version "2.3.0"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :main radix-math
  :aliases {"test" ["midje"]
            "all" ["with-profile" "dev,1.6:dev,1.5"]}
  :profiles {:1.5 {:dependencies [[org.clojure/clojure "1.5.1"]]}
             :1.6 {:dependencies [[org.clojure/clojure "1.6.0"]]}
             :dev {:dependencies [[midje "1.6.3"]]
                   :plugins [[lein-midje "3.1.3"]]}})
