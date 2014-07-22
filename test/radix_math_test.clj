;; Copyright (c) Torsten Becker, 2014. All rights reserved.  The use
;; and distribution terms for this software are covered by the Eclipse
;; Public License 1.0 (http://www.eclipse.org/legal/epl-v10.html)
;; which can be found in the file epl.html at the root of this
;; distribution.  By using this software in any fashion, you are
;; agreeing to be bound by the terms of this license.
;; You must not remove this notice, or any other, from this software.

(ns radix-math-test
  (require [midje.sweet :refer :all]
           [radix-math :as radix]))

(def base-10 (range 10))
(def hex (map #(.charAt (format "%x" %) 0) (range 16)))

(facts "to-radix"
  (radix/to-radix 0 base-10) => [0]
  (radix/to-radix 1 base-10) => [1]
  (radix/to-radix 15 base-10) => [1, 5]
  (radix/to-radix 99 base-10) => [9, 9]
  (radix/to-radix 123 base-10) => [1, 2, 3]
  (apply str (radix/to-radix 255 hex)) => "ff"
  (apply str (radix/to-radix 0 radix/printable-ascii)) => "!"
  (apply str (radix/to-radix 1 radix/printable-ascii)) => "\""
  (apply str (radix/to-radix 480203 radix/printable-ascii)) => "WAT")

(facts "from-radix"
  (radix/from-radix [0] base-10) => 0
  (radix/from-radix [1] base-10) => 1
  (radix/from-radix [1, 5] base-10) => 15
  (radix/from-radix [9, 9] base-10) => 99
  (radix/from-radix [1, 2, 3] base-10) => 123
  (radix/from-radix "ff" hex) => 255
  (radix/from-radix "ff" hex) => 255
  (radix/from-radix "\"!" radix/printable-ascii) => 94
  (radix/from-radix "WAT" radix/printable-ascii) => 480203
  (radix/from-radix "??" hex) => (throws "Digit not found in alphabet"))
