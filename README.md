

# Radix Math

This library provides utilities to convert integers to arbitrary radix representations with custom "letter alphabets".

Likewise strings can be easily decoded back into a integer representation.

While Java has a limit of base (or "radix") 36, this library is generic and can handle arbitrary bases.


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

;; Using all printable ASCII chars:
(apply str (radix/to-radix 480203 radix/printable-ascii))
; => "WAT"

(apply str (radix/to-radix 0 radix/printable-ascii))
; => "!"

(radix/from-radix [1 2 3] (range 10))
; => 123

(radix/from-radix "\"!" radix/printable-ascii)
; => 94

(map #(radix/from-radix % radix/printable-ascii) ["!", "\"", "\"!", "\"!!", "WAT"])
; => (0 1 94 8836 480203)
```


## License

Copyright © 2014 Torsten Becker.

Distributed under the [Eclipse Public License](http://www.eclipse.org/legal/epl-v10.html), the same as Clojure.
