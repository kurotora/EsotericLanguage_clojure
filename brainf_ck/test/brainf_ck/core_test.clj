(ns brainf-ck.core-test
  (:require [clojure.test :refer :all]
            [brainf-ck.core :refer :all]))

;(deftest a-test
;  (testing "FIXME, I fail."
;    (is (= 0 1))))


(def obj (make-Brainf_ck "pd"))
(println obj)
(run obj 256)


;
;(run obj 256)


;(defn rrun [this size]
;    (defn _evelToken [tape cur pc]
;;      (println tape)
;;      (println cur)
;;      (println pc)
;;      (println (get (:src this) pc))
;      (case (get (:src this) pc)
;;        \p (do
;;             (println [(assoc tape cur (+ 1 (nth tape cur))) cur (+ pc 1)])
;;             [tape 0 0])
;        \p (do
;             (println "OK")
;             [(assoc tape cur (+ 1 (nth tape cur))) cur (+ pc 1)])
;        \m [(assoc tape cur (- 1 (nth tape cur))) cur (+ pc 1)]
;        \l [tape (+ cur 1) (+ pc 1)]
;        \b [tape (- cur 1) (+ pc 1)]
;        \d (do
;             (print (nth tape cur))
;             [tape cur (+ pc 1)])
;        \c (do
;             (print (nth tape cur))
;             [tape cur (+ pc 1)])
;        \s (if (= (nth tape cur) 0)
;             [tape cur (nth (:jumps this) pc)]
;             [tape cur (+ pc 1)])
;        \e (if (not= (nth tape cur) 0)
;             [tape cur (nth (:jumps this) pc)]
;             [tape cur (+ pc 1)])))
;    (loop [tape (vec (take size (repeat 0)))
;           cur 0
;           pc 0]
;      (let [[nt nc np] (_evelToken tape cur pc)]
;       (println nt nc np)
;        (println (count (:src this)))
;        (if (<= (count (:src this)) np)
;          this
;          (recur nt nc np)))))
;
;(def obj (make-Brainf_ck "p"))
;(println obj)
;(rrun obj 256)
