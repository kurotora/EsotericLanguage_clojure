(ns hq9pobj.core
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))

(defrecord HQ9p [src counter])

(defn make-HQ9p []
  (HQ9p. "" 0))

(defprotocol HQ9pProtocol
  (hello [this])
  (print-src [this])
  (print-99-bottles-of-beer [this])
  (increment [this])
  (run [this src]))

(extend-type HQ9p
  HQ9pProtocol
  (hello [this]
     (println "Hello, world!")
     this)
  (print-src [this]
     (println (:src this))
     this)
  (print-99-bottles-of-beer [this]
     (defn _print99 [k]
       (let [[before after] (cond
                             (= k 0) ["no more bottles" "99 bottles"]
                             (= k 1) ["1 bottle" "no more bottles"]
                             (= k 2) ["2 bottles" "1 bottle"]
                             :else   [(format "%s bottles" k) (format "%s bottle" k)])
             action (if (= k 0)
                      "Go to the store and buy some more"
                      "Take one down and pass it around")]
         (println (format "%s of beer on the wall, %s of beer." (string/capitalize before) before))
         (println (format "%s, %s of beer on the wall" action after))
         (when (= k 0) (println ""))))
     (doall (map _print99 (range 99 -1 -1)))
     this)
  (increment [this]
     (assoc this :counter (+ 1 (:counter this))))
  (run [this src]
     (let [newobj (assoc this :src src)]
       (reduce #(cond
                 (= %2 \H) (hello %1)
                 (= %2 \Q) (print-src %1)
                 (= %2 \9) (print-99-bottles-of-beer %1)
                 (= %2 \P) (increment %1))
               newobj
               src))))

;(defn -main [& args]
;  (with-open [r (io/reader (first args))]
;    (let [ip (atom (make-HQ9p))
;          lseq (io/line-seq r)]
;      (reset! ip (run @ip (first lseq))))))
