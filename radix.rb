# Copyright (c) Torsten Becker, 2014. All rights reserved.  The use
# and distribution terms for this software are covered by the Eclipse
# Public License 1.0 (http://www.eclipse.org/legal/epl-v10.html)
# which can be found in the file epl.html at the root of this
# distribution.  By using this software in any fashion, you are
# agreeing to be bound by the terms of this license.
# You must not remove this notice, or any other, from this software.

# Bonus: The same radix math in Ruby

printable_ascii = ("!"...(127.chr)).to_a

def logarithm(num, base)
  Math.log(num) / Math.log(base)
end

def to_radix(number, alphabet)
  base = alphabet.size
  num_digits = if number == 0
    1
  else
    logarithm(number, base).floor + 1
  end

  (0...num_digits).map do |i|
    n = number / (base ** i)
    digit = n % base
    alphabet[digit]
  end.reverse
end

def from_radix(encoded, alphabet)
  base = alphabet.size
  lookup = proc {|d| alphabet.index(d) or raise "Digit not found in alphabet"}

  encoded.reverse.each_with_index.inject(0) do |accu, tuple|
      val, i = tuple
      accu + ((base ** i) * lookup.call(val))
  end
end

p to_radix(1, (0...10).to_a)
# [1]

p to_radix(99, (0...10).to_a)
# [9, 9]

p to_radix(36657220, printable_ascii).join
# "M-[I"

p to_radix(0, printable_ascii).join
# "!"

p from_radix([1, 2, 3], (0...10).to_a)
# 123

p from_radix("\"!".split(""), printable_ascii)
# 94

p ["!", "\"", "\"!", "\"!!", "M-[I"].map {|x| from_radix(x.split(""), printable_ascii)}
# [0, 1, 94, 8836, 36657220]

p (94 * 94)
# 8836
