;; Copyright (c) Torsten Becker, 2014. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://www.eclipse.org/legal/epl-v10.html)
;; which can be found in the file epl.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.
;; You must not remove this notice, or any other, from this software.

(ns radix-math)

(def printable-ascii
  (map char (range (int \!) 127)))

(defn logarithm [num base]
  (/ (Math/log num) (Math/log base)))

(defn to-radix [number alphabet]
  (let [base (count alphabet)
        num-digits (if (= number 0)
                     1
                     (-> number (logarithm base) Math/floor int inc))]
    (reverse
      (for [i (range num-digits)
            :let [n (/ number (Math/pow base i))
                  digit (mod n base)]]
        (nth alphabet digit)))))

(defn from-radix [encoded alphabet]
  (let [base (count alphabet)
        digit-to-pos (into {} (map-indexed #(vector %2 %1) alphabet))
        lookup #(or (digit-to-pos %)
                    (throw (ex-info "Digit not found in alphabet" {:digit %})))]
    (->> encoded
      reverse
      (map-indexed #(vector %1 %2))
      (reduce
        (fn [accu [i val]]
          (+ accu
             (* (int (Math/pow base i))
                (lookup val))))
        0))))
