

# Clojure Radix Math

This library provides utilities to convert integers to arbitrary radix representations with custom "letter alphabets".

Likewise, strings can be easily decoded back into a integer representation.

While Java has a limit of base (or "radix") 36, this library is generic and can handle arbitrary bases. [See the usage section for examples](#usage).


## Releases and Dependency Information

radix-math is released via [Clojars](https://clojars.org/radix-math). The Latest stable release is `1.0.0`.

[Leiningen](https://github.com/technomancy/leiningen) dependency information:

```clojure
[radix-math "1.0.0"]
```


## Usage

```clojure
(require '[radix-math :as radix])

;; Lets start with base 10:
(radix/to-radix 1 (range 10))
; => (1)

;; 95 has two digits
(radix/to-radix 95 (range 10))
; => (9 5)

;; To convert to hex, lets first define all available digits:
(def hex (map #(.charAt (format "%x" %) 0) (range 16)))

(apply str (radix/to-radix 255 hex))
; => "ff"

;; Using all printable ASCII characters as digits:
(apply str (radix/to-radix 480203 radix/printable-ascii))
; => "WAT"

;; Converting back from sequences:
(radix/from-radix [1 2 3] (range 10))
; => 123

(radix/from-radix "ff" hex)
; => 255

(radix/from-radix "\"!" radix/printable-ascii)
; => 94
```


## License

Copyright © 2014 Torsten Becker.

Distributed under the [Eclipse Public License](http://www.eclipse.org/legal/epl-v10.html), the same as Clojure.
