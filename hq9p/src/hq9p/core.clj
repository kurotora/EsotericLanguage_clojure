(ns hq9p.core
  (:require [clojure.java.io :as io]
            [clojure.string :as string]))


(defrecord HQ9p [src counter])

(defn make-HQ9p []
  (HQ9p. "" 0))

(defn hello [obj]
  (println "Hello, world!")
  obj)

(defn print-src [obj]
  (println (:src obj))
  obj)

(defn print-99-bottles-of-beer [obj]
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
  obj)


(defn increment [obj]
  (assoc obj :counter (+ 1 (:counter obj))))

(defn run [obj src]
  (let [newobj (assoc obj :src src)]
    (reduce #(cond
              (= %2 \H) (hello %1)
              (= %2 \Q) (print-src %1)
              (= %2 \9) (print-99-bottles-of-beer %1)
              (= %2 \P) (increment %1))
         newobj
         src)))


;(defn -main [& args]
;  (with-open [r (io/reader (first args))]
;    (let [ip (atom (make-HQ9p))
;          lseq (io/line-seq r)]
;      (reset! ip (run @ip (first lseq))))))
