{:namespaces
 ({:source-url
   "https://github.com/clojure/math.numeric-tower/blob/d40bd9e87e4568588c839cc1fd141be35081b4f7/src/main/clojure/clojure/math/numeric_tower.clj",
   :wiki-url
   "http://clojure.github.com/math.numeric-tower/clojure.math.numeric-tower-api.html",
   :name "clojure.math.numeric-tower",
   :author "Mark Engelberg",
   :doc
   "Math functions that deal intelligently with the various\ntypes in Clojure's numeric tower, as well as math functions\ncommonly found in Scheme implementations.\n\nexpt - (expt x y) is x to the yth power, returns an exact number\n  if the base is an exact number, and the power is an integer,\n  otherwise returns a double.\nabs - (abs n) is the absolute value of n\ngcd - (gcd m n) returns the greatest common divisor of m and n\nlcm - (lcm m n) returns the least common multiple of m and n\n\nWhen floor, ceil, and round are passed doubles, we just defer to\nthe corresponding functions in Java's Math library.  Java's\nbehavior is somewhat strange (floor and ceil return doubles rather\nthan integers, and round on large doubles yields spurious results)\nbut it seems best to match Java's semantics.  On exact numbers\n(ratios and decimals), we can have cleaner semantics.\n\nfloor - (floor n) returns the greatest integer less than or equal to n.\n  If n is an exact number, floor returns an integer,\n  otherwise a double.\nceil - (ceil n) returns the least integer greater than or equal to n.\n  If n is an exact number, ceil returns an integer,\n  otherwise a double.\nround - (round n) rounds to the nearest integer.\n  round always returns an integer.  round rounds up for values\n  exactly in between two integers.\n\n\nsqrt - Implements the sqrt behavior I'm accustomed to from PLT Scheme,\n  specifically, if the input is an exact number, and is a square\n  of an exact number, the output will be exact.  The downside\n  is that for the common case (inexact square root), some extra\n  computation is done to look for an exact square root first.\n  So if you need blazingly fast square root performance, and you\n  know you're just going to need a double result, you're better\n  off calling java's Math/sqrt, or alternatively, you could just\n  convert your input to a double before calling this sqrt function.\n  If Clojure ever gets complex numbers, then this function will\n  need to be updated (so negative inputs yield complex outputs).\nexact-integer-sqrt - Implements a math function from the R6RS Scheme\n  standard.  (exact-integer-sqrt k) where k is a non-negative integer,\n  returns [s r] where k = s^2+r and k < (s+1)^2.  In other words, it\n  returns the floor of the square root and the \"remainder\"."}),
 :vars
 ({:arglists ([n]),
   :name "abs",
   :namespace "clojure.math.numeric-tower",
   :source-url
   "https://github.com/clojure/math.numeric-tower/blob/d40bd9e87e4568588c839cc1fd141be35081b4f7/src/main/clojure/clojure/math/numeric_tower.clj#L83",
   :raw-source-url
   "https://github.com/clojure/math.numeric-tower/raw/d40bd9e87e4568588c839cc1fd141be35081b4f7/src/main/clojure/clojure/math/numeric_tower.clj",
   :wiki-url
   "http://clojure.github.com/math.numeric-tower//clojure.math.numeric-tower-api.html#clojure.math.numeric-tower/abs",
   :doc "(abs n) is the absolute value of n",
   :var-type "function",
   :line 83,
   :file "src/main/clojure/clojure/math/numeric_tower.clj"}
  {:arglists ([n]),
   :name "exact-integer-sqrt",
   :namespace "clojure.math.numeric-tower",
   :source-url
   "https://github.com/clojure/math.numeric-tower/blob/d40bd9e87e4568588c839cc1fd141be35081b4f7/src/main/clojure/clojure/math/numeric_tower.clj#L213",
   :raw-source-url
   "https://github.com/clojure/math.numeric-tower/raw/d40bd9e87e4568588c839cc1fd141be35081b4f7/src/main/clojure/clojure/math/numeric_tower.clj",
   :wiki-url
   "http://clojure.github.com/math.numeric-tower//clojure.math.numeric-tower-api.html#clojure.math.numeric-tower/exact-integer-sqrt",
   :doc
   "(exact-integer-sqrt n) expects a non-negative integer n, and returns [s r] where n = s^2+r and n < (s+1)^2.  In other words, it returns the floor of the square root and the 'remainder'.\nFor example, (exact-integer-sqrt 15) is [3 6] because 15 = 3^2+6.",
   :var-type "function",
   :line 213,
   :file "src/main/clojure/clojure/math/numeric_tower.clj"}
  {:arglists ([base pow]),
   :name "expt",
   :namespace "clojure.math.numeric-tower",
   :source-url
   "https://github.com/clojure/math.numeric-tower/blob/d40bd9e87e4568588c839cc1fd141be35081b4f7/src/main/clojure/clojure/math/numeric_tower.clj#L72",
   :raw-source-url
   "https://github.com/clojure/math.numeric-tower/raw/d40bd9e87e4568588c839cc1fd141be35081b4f7/src/main/clojure/clojure/math/numeric_tower.clj",
   :wiki-url
   "http://clojure.github.com/math.numeric-tower//clojure.math.numeric-tower-api.html#clojure.math.numeric-tower/expt",
   :doc
   "(expt base pow) is base to the pow power.\nReturns an exact number if the base is an exact number and the power is an integer, otherwise returns a double.",
   :var-type "function",
   :line 72,
   :file "src/main/clojure/clojure/math/numeric_tower.clj"}
  {:arglists ([a b]),
   :name "gcd",
   :namespace "clojure.math.numeric-tower",
   :source-url
   "https://github.com/clojure/math.numeric-tower/blob/d40bd9e87e4568588c839cc1fd141be35081b4f7/src/main/clojure/clojure/math/numeric_tower.clj#L178",
   :raw-source-url
   "https://github.com/clojure/math.numeric-tower/raw/d40bd9e87e4568588c839cc1fd141be35081b4f7/src/main/clojure/clojure/math/numeric_tower.clj",
   :wiki-url
   "http://clojure.github.com/math.numeric-tower//clojure.math.numeric-tower-api.html#clojure.math.numeric-tower/gcd",
   :doc "(gcd a b) returns the greatest common divisor of a and b",
   :var-type "function",
   :line 178,
   :file "src/main/clojure/clojure/math/numeric_tower.clj"}
  {:arglists ([a b]),
   :name "lcm",
   :namespace "clojure.math.numeric-tower",
   :source-url
   "https://github.com/clojure/math.numeric-tower/blob/d40bd9e87e4568588c839cc1fd141be35081b4f7/src/main/clojure/clojure/math/numeric_tower.clj#L185",
   :raw-source-url
   "https://github.com/clojure/math.numeric-tower/raw/d40bd9e87e4568588c839cc1fd141be35081b4f7/src/main/clojure/clojure/math/numeric_tower.clj",
   :wiki-url
   "http://clojure.github.com/math.numeric-tower//clojure.math.numeric-tower-api.html#clojure.math.numeric-tower/lcm",
   :doc "(lcm a b) returns the least common multiple of a and b",
   :var-type "function",
   :line 185,
   :file "src/main/clojure/clojure/math/numeric_tower.clj"}
  {:file "src/main/clojure/clojure/math/numeric_tower.clj",
   :raw-source-url
   "https://github.com/clojure/math.numeric-tower/raw/d40bd9e87e4568588c839cc1fd141be35081b4f7/src/main/clojure/clojure/math/numeric_tower.clj",
   :source-url
   "https://github.com/clojure/math.numeric-tower/blob/d40bd9e87e4568588c839cc1fd141be35081b4f7/src/main/clojure/clojure/math/numeric_tower.clj#L90",
   :wiki-url
   "http://clojure.github.com/math.numeric-tower//clojure.math.numeric-tower-api.html#clojure.math.numeric-tower/MathFunctions",
   :namespace "clojure.math.numeric-tower",
   :line 90,
   :var-type "protocol",
   :doc nil,
   :name "MathFunctions"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/math.numeric-tower//clojure.math.numeric-tower-api.html#clojure.math.numeric-tower/ceil",
   :namespace "clojure.math.numeric-tower",
   :var-type "function",
   :arglists ([n]),
   :doc
   "(ceil n) returns the least integer greater than or equal to n.\nIf n is an exact number, ceil returns an integer, otherwise a double.",
   :name "ceil"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/math.numeric-tower//clojure.math.numeric-tower-api.html#clojure.math.numeric-tower/floor",
   :namespace "clojure.math.numeric-tower",
   :var-type "function",
   :arglists ([n]),
   :doc
   "(floor n) returns the greatest integer less than or equal to n.\nIf n is an exact number, floor returns an integer, otherwise a double.",
   :name "floor"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/math.numeric-tower//clojure.math.numeric-tower-api.html#clojure.math.numeric-tower/integer-length",
   :namespace "clojure.math.numeric-tower",
   :var-type "function",
   :arglists ([n]),
   :doc "Length of integer in binary",
   :name "integer-length"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/math.numeric-tower//clojure.math.numeric-tower-api.html#clojure.math.numeric-tower/round",
   :namespace "clojure.math.numeric-tower",
   :var-type "function",
   :arglists ([n]),
   :doc
   "(round n) rounds to the nearest integer.\nround always returns an integer.  Rounds up for values exactly in between two integers.",
   :name "round"}
  {:file nil,
   :raw-source-url nil,
   :source-url nil,
   :wiki-url
   "http://clojure.github.com/math.numeric-tower//clojure.math.numeric-tower-api.html#clojure.math.numeric-tower/sqrt",
   :namespace "clojure.math.numeric-tower",
   :var-type "function",
   :arglists ([n]),
   :doc "Square root, but returns exact number if possible.",
   :name "sqrt"})}
