(ns radix)

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

;; To test it in Clojure, run `lein repl`, then `(use 'radix)`, then try the examples:

(to-radix 1 (range 10))
; => (1)

(to-radix 99 (range 10))
; => (9 9)

(apply str (to-radix 36657220 printable-ascii))
; => "M-[I"

(apply str (to-radix 0 printable-ascii))
; => "!"

(from-radix [1 2 3] (range 10))
; => 123

(from-radix "\"!" printable-ascii)
; => 94

(map #(from-radix % printable-ascii) ["!", "\"", "\"!", "\"!!", "M-[I"])
; => (0 1 94 8836 36657220)

(* 94 94)
; => 8836
