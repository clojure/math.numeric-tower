{:namespaces
 ({:doc
   "Math functions that deal intelligently with the various\ntypes in Clojure's numeric tower, as well as math functions\ncommonly found in Scheme implementations.\n\nexpt - (expt x y) is x to the yth power, returns an exact number\n  if the base is an exact number, and the power is an integer,\n  otherwise returns a double.\nabs - (abs n) is the absolute value of n\ngcd - (gcd m n) returns the greatest common divisor of m and n\nlcm - (lcm m n) returns the least common multiple of m and n\n\nWhen floor, ceil, and round are passed doubles, we just defer to\nthe corresponding functions in Java's Math library.  Java's\nbehavior is somewhat strange (floor and ceil return doubles rather\nthan integers, and round on large doubles yields spurious results)\nbut it seems best to match Java's semantics.  On exact numbers\n(ratios and decimals), we can have cleaner semantics.\n\nfloor - (floor n) returns the greatest integer less than or equal to n.\n  If n is an exact number, floor returns an integer,\n  otherwise a double.\nceil - (ceil n) returns the least integer greater than or equal to n.\n  If n is an exact number, ceil returns an integer,\n  otherwise a double.\nround - (round n) rounds to the nearest integer.\n  round always returns an integer.  round rounds up for values\n  exactly in between two integers.\n\n\nsqrt - Implements the sqrt behavior I'm accustomed to from PLT Scheme,\n  specifically, if the input is an exact number, and is a square\n  of an exact number, the output will be exact.  The downside\n  is that for the common case (inexact square root), some extra\n  computation is done to look for an exact square root first.\n  So if you need blazingly fast square root performance, and you\n  know you're just going to need a double result, you're better\n  off calling java's Math/sqrt, or alternatively, you could just\n  convert your input to a double before calling this sqrt function.\n  If Clojure ever gets complex numbers, then this function will\n  need to be updated (so negative inputs yield complex outputs).\nexact-integer-sqrt - Implements a math function from the R6RS Scheme\n  standard.  (exact-integer-sqrt k) where k is a non-negative integer,\n  returns [s r] where k = s^2+r and k < (s+1)^2.  In other words, it\n  returns the floor of the square root and the \"remainder\".",
   :author "Mark Engelberg",
   :name "clojure.math.numeric-tower",
   :wiki-url
   "http://clojure.github.com/math.numeric-tower/clojure.math.numeric-tower-api.html",
   :source-url
   "https://github.com/clojure/math.numeric-tower/blob/46a91e50a1b8992300106f925d7474b9366e77f5/src/main/clojure/clojure/math/numeric_tower.clj"}),
 :vars
 ({:raw-source-url
   "https://github.com/clojure/math.numeric-tower/raw/46a91e50a1b8992300106f925d7474b9366e77f5/src/main/clojure/clojure/math/numeric_tower.clj",
   :name "abs",
   :file "src/main/clojure/clojure/math/numeric_tower.clj",
   :source-url
   "https://github.com/clojure/math.numeric-tower/blob/46a91e50a1b8992300106f925d7474b9366e77f5/src/main/clojure/clojure/math/numeric_tower.clj#L96",
   :line 96,
   :var-type "function",
   :arglists ([n]),
   :doc "(abs n) is the absolute value of n",
   :namespace "clojure.math.numeric-tower",
   :wiki-url
   "http://clojure.github.com/math.numeric-tower//clojure.math.numeric-tower-api.html#clojure.math.numeric-tower/abs"}
  {:raw-source-url
   "https://github.com/clojure/math.numeric-tower/raw/46a91e50a1b8992300106f925d7474b9366e77f5/src/main/clojure/clojure/math/numeric_tower.clj",
   :name "exact-integer-sqrt",
   :file "src/main/clojure/clojure/math/numeric_tower.clj",
   :source-url
   "https://github.com/clojure/math.numeric-tower/blob/46a91e50a1b8992300106f925d7474b9366e77f5/src/main/clojure/clojure/math/numeric_tower.clj#L218",
   :line 218,
   :var-type "function",
   :arglists ([n]),
   :doc
   "(exact-integer-sqrt n) expects a non-negative integer n, and returns [s r] where n = s^2+r and n < (s+1)^2.  In other words, it returns the floor of the square root and the 'remainder'.\nFor example, (exact-integer-sqrt 15) is [3 6] because 15 = 3^2+6.",
   :namespace "clojure.math.numeric-tower",
   :wiki-url
   "http://clojure.github.com/math.numeric-tower//clojure.math.numeric-tower-api.html#clojure.math.numeric-tower/exact-integer-sqrt"}
  {:raw-source-url
   "https://github.com/clojure/math.numeric-tower/raw/46a91e50a1b8992300106f925d7474b9366e77f5/src/main/clojure/clojure/math/numeric_tower.clj",
   :name "expt",
   :file "src/main/clojure/clojure/math/numeric_tower.clj",
   :source-url
   "https://github.com/clojure/math.numeric-tower/blob/46a91e50a1b8992300106f925d7474b9366e77f5/src/main/clojure/clojure/math/numeric_tower.clj#L80",
   :line 80,
   :var-type "function",
   :arglists ([base pow]),
   :doc
   "(expt base pow) is base to the pow power.\nReturns an exact number if the base is an exact number and the power is an integer, otherwise returns a double.",
   :namespace "clojure.math.numeric-tower",
   :wiki-url
   "http://clojure.github.com/math.numeric-tower//clojure.math.numeric-tower-api.html#clojure.math.numeric-tower/expt"}
  {:raw-source-url
   "https://github.com/clojure/math.numeric-tower/raw/46a91e50a1b8992300106f925d7474b9366e77f5/src/main/clojure/clojure/math/numeric_tower.clj",
   :name "gcd",
   :file "src/main/clojure/clojure/math/numeric_tower.clj",
   :source-url
   "https://github.com/clojure/math.numeric-tower/blob/46a91e50a1b8992300106f925d7474b9366e77f5/src/main/clojure/clojure/math/numeric_tower.clj#L183",
   :line 183,
   :var-type "function",
   :arglists ([a b]),
   :doc "(gcd a b) returns the greatest common divisor of a and b",
   :namespace "clojure.math.numeric-tower",
   :wiki-url
   "http://clojure.github.com/math.numeric-tower//clojure.math.numeric-tower-api.html#clojure.math.numeric-tower/gcd"}
  {:raw-source-url
   "https://github.com/clojure/math.numeric-tower/raw/46a91e50a1b8992300106f925d7474b9366e77f5/src/main/clojure/clojure/math/numeric_tower.clj",
   :name "lcm",
   :file "src/main/clojure/clojure/math/numeric_tower.clj",
   :source-url
   "https://github.com/clojure/math.numeric-tower/blob/46a91e50a1b8992300106f925d7474b9366e77f5/src/main/clojure/clojure/math/numeric_tower.clj#L190",
   :line 190,
   :var-type "function",
   :arglists ([a b]),
   :doc "(lcm a b) returns the least common multiple of a and b",
   :namespace "clojure.math.numeric-tower",
   :wiki-url
   "http://clojure.github.com/math.numeric-tower//clojure.math.numeric-tower-api.html#clojure.math.numeric-tower/lcm"}
  {:name "MathFunctions",
   :doc nil,
   :var-type "protocol",
   :line 103,
   :namespace "clojure.math.numeric-tower",
   :wiki-url
   "http://clojure.github.com/math.numeric-tower//clojure.math.numeric-tower-api.html#clojure.math.numeric-tower/MathFunctions",
   :source-url
   "https://github.com/clojure/math.numeric-tower/blob/46a91e50a1b8992300106f925d7474b9366e77f5/src/main/clojure/clojure/math/numeric_tower.clj#L103",
   :raw-source-url
   "https://github.com/clojure/math.numeric-tower/raw/46a91e50a1b8992300106f925d7474b9366e77f5/src/main/clojure/clojure/math/numeric_tower.clj",
   :file "src/main/clojure/clojure/math/numeric_tower.clj"}
  {:name "ceil",
   :doc
   "(ceil n) returns the least integer greater than or equal to n.\nIf n is an exact number, ceil returns an integer, otherwise a double.",
   :arglists ([n]),
   :var-type "function",
   :namespace "clojure.math.numeric-tower",
   :wiki-url
   "http://clojure.github.com/math.numeric-tower//clojure.math.numeric-tower-api.html#clojure.math.numeric-tower/ceil",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "floor",
   :doc
   "(floor n) returns the greatest integer less than or equal to n.\nIf n is an exact number, floor returns an integer, otherwise a double.",
   :arglists ([n]),
   :var-type "function",
   :namespace "clojure.math.numeric-tower",
   :wiki-url
   "http://clojure.github.com/math.numeric-tower//clojure.math.numeric-tower-api.html#clojure.math.numeric-tower/floor",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "integer-length",
   :doc "Length of integer in binary",
   :arglists ([n]),
   :var-type "function",
   :namespace "clojure.math.numeric-tower",
   :wiki-url
   "http://clojure.github.com/math.numeric-tower//clojure.math.numeric-tower-api.html#clojure.math.numeric-tower/integer-length",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "round",
   :doc
   "(round n) rounds to the nearest integer.\nround always returns an integer.  Rounds up for values exactly in between two integers.",
   :arglists ([n]),
   :var-type "function",
   :namespace "clojure.math.numeric-tower",
   :wiki-url
   "http://clojure.github.com/math.numeric-tower//clojure.math.numeric-tower-api.html#clojure.math.numeric-tower/round",
   :source-url nil,
   :raw-source-url nil,
   :file nil}
  {:name "sqrt",
   :doc "Square root, but returns exact number if possible.",
   :arglists ([n]),
   :var-type "function",
   :namespace "clojure.math.numeric-tower",
   :wiki-url
   "http://clojure.github.com/math.numeric-tower//clojure.math.numeric-tower-api.html#clojure.math.numeric-tower/sqrt",
   :source-url nil,
   :raw-source-url nil,
   :file nil})}
