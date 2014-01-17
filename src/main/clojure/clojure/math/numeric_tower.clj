;;; math.clj: math functions that deal intelligently with the various
;;; types in Clojure's numeric tower, as well as math functions
;;; commonly found in Scheme implementations.

;; by Mark Engelberg (mark.engelberg@gmail.com)
;; May 21, 2011

(ns 
  ^{:author "Mark Engelberg",
     :doc "Math functions that deal intelligently with the various
types in Clojure's numeric tower, as well as math functions
commonly found in Scheme implementations.

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
  returns the floor of the square root and the \"remainder\".
"}
  clojure.math.numeric-tower)

;; so this code works with both 1.2.x and 1.3.0:
(def ^{:private true} minus (first [-' -]))
(def ^{:private true} mult (first [*' *]))
(def ^{:private true} plus (first [+' +]))
(def ^{:private true} dec* (first [dec' dec]))
(def ^{:private true} inc* (first [inc' inc]))

;; feature testing macro, based on suggestion from Chas Emerick:
(defmacro when-available
  [sym & body]
  (try
    (when (resolve sym)
      (list* 'do body))
    (catch ClassNotFoundException _#)))

(defn- expt-int [base pow]
  (loop [n pow, y (num 1), z base]
    (let [t (even? n), n (quot n 2)]
      (cond
       t (recur n y (mult z z))
       (zero? n) (mult z y)
       :else (recur n (mult z y) (mult z z))))))

(defn expt
  "(expt base pow) is base to the pow power.
Returns an exact number if the base is an exact number and the power is an integer, otherwise returns a double."
  [base pow]
  (if (and (not (float? base)) (integer? pow))
    (cond
     (pos? pow) (expt-int base pow)
     (zero? pow) (cond
                   (= (type base) BigDecimal) 1M
                   (= (type base) java.math.BigInteger) (java.math.BigInteger. "1")
                   (when-available clojure.lang.BigInt (= (type base) clojure.lang.BigInt))
                   (when-available clojure.lang.BigInt (bigint 1))
                   :else 1)
     :else (/ 1 (expt-int base (minus pow))))
    (Math/pow base pow)))
  
(defn abs "(abs n) is the absolute value of n" [n]
  (cond
   (not (number? n)) (throw (IllegalArgumentException.
			     "abs requires a number"))
   (neg? n) (minus n)
   :else n))

(defprotocol MathFunctions
  (floor [n] "(floor n) returns the greatest integer less than or equal to n.
If n is an exact number, floor returns an integer, otherwise a double.")
  (ceil [n] "(ceil n) returns the least integer greater than or equal to n.
If n is an exact number, ceil returns an integer, otherwise a double.")
  (round [n] "(round n) rounds to the nearest integer.
round always returns an integer.  Rounds up for values exactly in between two integers.")
  (integer-length [n] "Length of integer in binary")
  (sqrt [n] "Square root, but returns exact number if possible."))

(declare sqrt-integer)
(declare sqrt-ratio)
(declare sqrt-decimal)

(extend-type
 Integer MathFunctions
 (floor [n] n)
 (ceil [n] n)
 (round [n] n)
 (integer-length [n] (- 32 (Integer/numberOfLeadingZeros n)))
 (sqrt [n] (sqrt-integer n)))

(extend-type
 Long MathFunctions
 (floor [n] n)
 (ceil [n] n)
 (round [n] n)
 (integer-length [n] (- 64 (Long/numberOfLeadingZeros n)))
 (sqrt [n] (sqrt-integer n)))

(extend-type
 java.math.BigInteger MathFunctions
 (floor [n] n)
 (ceil [n] n)
 (round [n] n)
 (integer-length [n] (.bitLength n))
 (sqrt [n] (sqrt-integer n)))

(when-available
  clojure.lang.BigInt
  (extend-type
    clojure.lang.BigInt MathFunctions
    (floor [n] n)
    (ceil [n] n)
    (round [n] n)
    (integer-length [n] (.bitLength n))
    (sqrt [n] (sqrt-integer n))))

(extend-type
 java.math.BigDecimal MathFunctions
 (floor [n] (bigint (.setScale n 0 BigDecimal/ROUND_FLOOR)))
 (ceil [n] (bigint (.setScale n 0 BigDecimal/ROUND_CEILING)))
 (round [n] (floor (+ n 0.5M)))
 (sqrt [n] (sqrt-decimal n)))

(extend-type
 clojure.lang.Ratio MathFunctions
 (floor [n]
	(if (pos? n) (quot (. n numerator) (. n denominator))
	    (dec* (quot (. n numerator) (. n denominator)))))
 (ceil [n]
       (if (pos? n) (inc* (quot (. n numerator) (. n denominator)))
	   (quot (. n numerator) (. n denominator))))
 (round [n] (floor (+ n 1/2)))
 (sqrt [n] (sqrt-ratio n)))

(extend-type
 Double MathFunctions
 (floor [n] (Math/floor n))
 (ceil [n] (Math/ceil n))
 (round [n] (Math/round n)) ;(round (bigdec n)))
 (sqrt [n] (Math/sqrt n)))

(extend-type
 Float MathFunctions
 (floor [n] (Math/floor n))
 (ceil [n] (Math/ceil n))
 (round [n] (Math/round n)) ;(round (bigdec n)))
 (sqrt [n] (Math/sqrt n)))

(defn gcd "(gcd a b) returns the greatest common divisor of a and b" [a b]
  (if (or (not (integer? a)) (not (integer? b)))
    (throw (IllegalArgumentException. "gcd requires two integers"))  
    (loop [a (abs a) b (abs b)]
      (if (zero? b) a,
	  (recur b (mod a b))))))

(defn lcm
  "(lcm a b) returns the least common multiple of a and b"
  [a b]
  (when (or (not (integer? a)) (not (integer? b)))
    (throw (IllegalArgumentException. "lcm requires two integers")))
  (cond (zero? a) 0
        (zero? b) 0
        :else (abs (mult b (quot a (gcd a b))))))

;; Produces the largest integer less than or equal to the square root of n
;; Input n must be a non-negative integer
(defn- integer-sqrt [n]
  (cond
   (> n 24)
   (let [n-len (integer-length n)]
     (loop [init-value (if (even? n-len)
			 (expt 2 (quot n-len 2))
			 (expt 2 (inc* (quot n-len 2))))]
       (let [iterated-value (quot (plus init-value (quot n init-value)) 2)]
	 (if (>= iterated-value init-value)
	   init-value
	   (recur iterated-value)))))
   (> n 15) 4
   (> n  8) 3
   (> n  3) 2
   (> n  0) 1
   (> n -1) 0))

(defn exact-integer-sqrt "(exact-integer-sqrt n) expects a non-negative integer n, and returns [s r] where n = s^2+r and n < (s+1)^2.  In other words, it returns the floor of the square root and the 'remainder'.
For example, (exact-integer-sqrt 15) is [3 6] because 15 = 3^2+6."
  [n]
  (if (or (not (integer? n)) (neg? n))
    (throw (IllegalArgumentException. "exact-integer-sqrt requires a non-negative integer"))
    (let [isqrt (integer-sqrt n),
	  error (minus n (mult isqrt isqrt))]
      [isqrt error])))

(defn- sqrt-integer [n]
  (if (neg? n) Double/NaN
      (let [isqrt (integer-sqrt n),
	    error (minus n (mult isqrt isqrt))]
	(if (zero? error) isqrt
	    (Math/sqrt n)))))

(defn- sqrt-ratio [^clojure.lang.Ratio n]
  (if (neg? n) Double/NaN
      (let [numerator (.numerator n),
	    denominator (.denominator n),
	    sqrtnum (sqrt numerator)]
	(if (float? sqrtnum)
	  (Math/sqrt n)
	  (let [sqrtden (sqrt denominator)]
	    (if (float? sqrtden)
	      (Math/sqrt n)
	      (/ sqrtnum sqrtden)))))))

(defn- sqrt-decimal [n]
  (if (neg? n) Double/NaN
      (let [frac (rationalize n),
	    sqrtfrac (sqrt frac)]
	(if (ratio? sqrtfrac)
	  (/ (BigDecimal. (.numerator ^clojure.lang.Ratio sqrtfrac))
	     (BigDecimal. (.denominator ^clojure.lang.Ratio sqrtfrac)))
	  sqrtfrac))))
