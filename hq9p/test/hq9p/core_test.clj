(ns hq9p.core-test
  (:require [clojure.test :refer :all]
            [hq9p.core :refer :all]))

;(deftest a-test
;  (testing "FIXME, I fail."
;    (is (= 0 1))))


;; test code
(def interpretor (atom (make-HQ9p)))
(def intp (make-HQ9p))
(def program-text "HHQ9P")

(defn test1 [] (reset! interpretor (run @interpretor program-text)))
(defn test2 [] (run @interpretor program-text))
(defn test3 [] (run intp program-text))

(test1)
(test2)
(test3)
;(-main "program.txt")


(println @interpretor)
(println program-text)
(println "ss")
(increment intp)

; (defn print-bottles-of-beer [obj]
;   (defn _print99 [k]
;     (let [[before after] (cond
;                           (= k 0) ["no more bottles" "99 bottles"]
;                           (= k 1) ["1 bottle" "no more bottles"]
;                           (= k 2) ["2 bottles" "1 bottle"]
;                           :else   ["2 bottles" "1 bottle"])
;           action (if (= k 0)
;                    "Go to the store and buy some more"
;                    "Take one down and pass it around")]
;       (print (format "%s of beer on the wall, %s of beer." before before))
;       (print (format "%s, %s of beer on the wall" action after))
;       (when (= k 0) (print ""))))
;   (doall (map _print99 (range 10 -1 -1)))
;   obj))
; (print (range 10 -1 -1))
; (print-bottles-of-beer @interpretor)


;(with-open [r (reader ("program.txt"))
;  (let [ip (atom (make-HQ9p))
;        lseq (line-seq r)]
;    (reset! ip (run @ip (first lseq)))))
