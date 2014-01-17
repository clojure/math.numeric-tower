clojure.math.numeric-tower
========================================

Formerly clojure.contrib.math

Math functions that deal intelligently with the various
types in Clojure's numeric tower, as well as math functions
commonly found in Scheme implementations.

Functions included:

* (expt x y) - x to the yth power

* (abs n) - absolute value of n

* (gcd m n) - greatest common divisor of m and n

* (lcm m n) - least common multiple of m and n

* (floor x) - round down

* (ceil x) - round up

* (round x) - round to nearest

* (sqrt x) - square root, exact if possible

* (exact-integer-sqrt k) returns floor of square root and the "remainder"

More documentation in docstrings.

Releases and Dependency Information
========================================

Latest stable release: 0.0.4

* [All Released Versions](http://search.maven.org/#search%7Cgav%7C1%7Cg%3A%22org.clojure%22%20AND%20a%3A%22math.numeric-tower%22)

* [Development Snapshot Versions](https://oss.sonatype.org/index.html#nexus-search;gav~org.clojure~math.numeric-tower~~~)

[Leiningen](https://github.com/technomancy/leiningen) dependency information:

```clojure
[org.clojure/math.numeric-tower "0.0.4"]
```

[Maven](http://maven.apache.org/) dependency information:

```xml
<dependency>
  <groupId>org.clojure</groupId>
  <artifactId>math.numeric-tower</artifactId>
  <version>0.0.4</version>
</dependency>
```

Example Usage
========================================

```clojure
(ns example.core
  (:require [clojure.math.numeric-tower :as math]))

(defn- sqr
  "Uses the numeric tower expt to square a number"
  [x]
  (math/expt x 2))

(defn euclidean-squared-distance
  "Computes the Euclidean squared distance between two sequences"
  [a b]
  (reduce + (map (comp sqr -) a b)))

(defn euclidean-distance
  "Computes the Euclidean distance between two sequences"
  [a b]
  (math/sqrt (euclidean-squared-distance a b)))

(let [a [1 2 3 5 8 13 21]
      b [0 2 4 6 8 10 12]]
  (euclidean-distance a b))

;;=> 9.643650760992955
```

Refer to docstrings in the `clojure.math.numeric-tower` namespace for
additional documentation.

[API Documentation](http://clojure.github.com/math.numeric-tower/)

Developer Information
========================================

* [GitHub project](https://github.com/clojure/math.numeric-tower)

* [Bug Tracker](http://dev.clojure.org/jira/browse/MTOWER)

* [Continuous Integration](http://build.clojure.org/job/math.numeric-tower/)

* [Compatibility Test Matrix](http://build.clojure.org/job/math.numeric-tower-test-matrix/)


Changelog
========================================
* Release 0.0.4 on 2014-01-16
  * Adjust return type of expt to match base when power is 0.
	  * (expt 3M 0) -> 1M
	  * (expt 3N 0) -> 1N

* Release 0.0.3 on 2013-12-29
  * Minor improvement to sqrt of ratio.

* Release 0.0.2 on 2012-11-23
  * Added type hints to remove some reflective calls.

* Release 0.0.1 on 2011-10-15
  * Initial release.
  * Source-compatible with clojure.contrib.math, except for the name change.

License
========================================

Distributed under the Eclipse Public License, the same as Clojure.
