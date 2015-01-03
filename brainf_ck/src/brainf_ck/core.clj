(ns brainf-ck.core
  (:require [clojure.java.io :as io]))

(defrecord Brainf_ck [src jumps])

(defprotocol Brainf_ckProtocol
  (run [this size])
  (analizeJumps [this]))

(extend-type Brainf_ck
  Brainf_ckProtocol
  (run [this size]
    (defn _evelToken [tape cur pc]
      (case (nth (:src this) pc)
        \p [(assoc tape cur (+ 1 (nth tape cur))) cur (+ pc 1)]
        \m [(assoc tape cur (- 1 (nth tape cur))) cur (+ pc 1)]
        \l [tape (+ cur 1) (+ pc 1)]
        \b [tape (- cur 1) (+ pc 1)]
        \d (do
             (print (nth tape cur))
             [tape cur (+ pc 1)])
;        \c (do
;             (print (nth tape cur))
;             [tape cur (+ pc 1)])
        \s (if (= (nth tape cur) 0)
             [tape cur (nth (:jumps this) pc)]
             [tape cur (+ pc 1)])
        \e (if (not= (nth tape cur) 0)
             [tape cur (nth (:jumps this) pc)]
             [tape cur (+ pc 1)])))
    (loop [tape (vec (repeat size 0))
           cur 0
           pc 0]
      (let [[nt nc np] (_evelToken tape cur pc)]
        (if (<= (count (:src this)) np)
          this
          (recur nt nc np)))))
  (analizeJumps [this]
    (let [col (:tokens this)]
      (defn _an [[jumps starts] [idx token]]
        (case token
          \s [jumps (cons idx starts)]
          \e (if (= 0 (count starts))
               (throw Exception)
               [(doto jumps
                  (assoc (first starts) idx)
                  (assoc idx (first starts)))
                (rest starts)])))
      (assoc this :jumps (reduce _an
                                 [{} []]
                                 (zipmap (iterate inc 0) col))))))


(defn make-Brainf_ck [src]
  (doto (Brainf_ck. src {})
    (analizeJumps)))


;(defn -main [& args]
;  (with-open [rdr (clojure.java.io/reader (first args))
;              f (file-seq rdr)
;              lines (for [l f] l)
;              text (concat (apply lines))]
;    (.run (make-Brainf_ck text))))
;
;(-main "test.txt")
