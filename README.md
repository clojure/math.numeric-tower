# clojure.math.numeric-tower

Formerly clojure.contrib.math

Math functions that deal intelligently with the various
types in Clojure's numeric tower, as well as math functions
commonly found in Scheme implementations.

## Installation

This library is available in Maven central. Add this `:dependency` to your
Leinengen `project.clj`:

```clojure
[org.clojure/math.numeric-tower "0.0.1"]
```

Or, add this to your Maven project's `pom.xml`:

```xml
<dependency>
  <groupId>org.clojure</groupId>
  <artifactId>math.numeric-tower</artifactId>
  <version>0.0.1</version>
</dependency>
```

The list of all prior releases is available [here](http://search.maven.org/#search%7Cga%7C1%7Cg%3A%22org.clojure%22%20AND%20a%3A%22math.numeric-tower%22).

## Usage

expt - (expt x y) is x to the yth power, returns an exact number
  if the base is an exact number, and the power is an integer,
  otherwise returns a double.

abs - (abs n) is the absolute value of n

gcd - (gcd m n) returns the greatest common divisor of m and n

lcm - (lcm m n) returns the least common multiple of m and n

When floor, ceil, and round are passed doubles, we just defer to
the corresponding functions in Java's Math library.  Java's
behavior is somewhat strange (floor and ceil return doubles rather
than integers, and round on large doubles yields spurious results)
but it seems best to match Java's semantics.  On exact numbers
(ratios and decimals), we can have cleaner semantics.

floor - (floor n) returns the greatest integer less than or equal to n.
  If n is an exact number, floor returns an integer,
  otherwise a double.

ceil - (ceil n) returns the least integer greater than or equal to n.
  If n is an exact number, ceil returns an integer,
  otherwise a double.

round - (round n) rounds to the nearest integer.
  round always returns an integer.  round rounds up for values
  exactly in between two integers.


sqrt - Implements the sqrt behavior I'm accustomed to from PLT Scheme,
  specifically, if the input is an exact number, and is a square
  of an exact number, the output will be exact.  The downside
  is that for the common case (inexact square root), some extra
  computation is done to look for an exact square root first.
  So if you need blazingly fast square root performance, and you
  know you're just going to need a double result, you're better
  off calling java's Math/sqrt, or alternatively, you could just
  convert your input to a double before calling this sqrt function.
  If Clojure ever gets complex numbers, then this function will
  need to be updated (so negative inputs yield complex outputs).

exact-integer-sqrt - Implements a math function from the R6RS Scheme
  standard.  (exact-integer-sqrt k) where k is a non-negative integer,
  returns [s r] where k = s^2+r and k < (s+1)^2.  In other words, it
  returns the floor of the square root and the "remainder".

## License

Distributed under the Eclipse Public License, the same as Clojure.
