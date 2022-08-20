package com.rockthejvm

object FunctionalProgramming extends App {
  // Scala is OO
  class Person(name: String) {
    def apply(age: Int): Unit = println(s"$name is $age years old")
  }
  val bob = new Person("Bob")
  bob.apply(42)
  bob(43) // INVOKING bob as function === bob.apply(43)

  /*
  Scala runs on the JVM
  Functional programming:
  - compose functions
  - pass functions as arguments
  - return functions as results

  Conclusion: FunctionX: Function1, Function2, Function3, ... Function22 (as 22 arguments)
   */
  val simpleIncrementor = new Function1[Int, Int] {
    override def apply(start: Int): Int = start + 1
  }

  simpleIncrementor.apply(1)
  simpleIncrementor(1)

  // ALL SCALA FUNCTIONS ARE INSTANCES OF THESE FUNCTION_X TYPES

  // function with 2 args
  val stringConcat = new Function2[String, String, String] {
    override def apply(arg1: String, arg2: String): String = arg1 + arg2
  }

  stringConcat("Hello", " World")

  // syntax sugars
  val doubler = (x: Int) => 2 * x
  doubler(4) // 8

  /*
  equivalent to much longer:
  new Function3[Int, Int] {
    override def apply(x: Int): Int = 2 * x
   */

  // higher order functions (HOF)
  val aMappedList = List(1,2,3).map( (x: Int) => 2 * x) // HOF
  val flatMappedList = List(1,2,3).flatMap( x => List(x, 2 * x) )
  val filteredList = List(1,2,3,4,5).filter( _ <= 3) // equivalent to filter(x => x <= 3)
  println(aMappedList)
  println(flatMappedList)
  println(filteredList)

  // all pairs between the numbers 1, 2, 3 and the letters 'a', 'b', 'c'
  val allPairs = List(1,2,3).flatMap( number => List('a', 'b', 'c').map( letter => s"$number-$letter" ) )
  println(allPairs)

  // for-comprehensions, preferable!
  val forPairs = for {
    n <- List(1,2,3)
    l <- List('a', 'b', 'c')
  } yield s"$n-$l"

  /**
   * Collections *
   */
  // lists
  val aList = List(1,2,3,4,5)
  val aHead = aList.head
  val aTail = aList.tail

  println(s"head: $aHead, tail: $aTail")

  val aPrependedList = 0 :: aList
  val anExtendedList = 0 +: aList :+ 6
  println(s"prepended: $aPrependedList, extended: $anExtendedList")

  // sequences
  val aSequence: Seq[Int] = Seq(1, 2, 3, 4, 5) // Seq.apply(1,2,3,4,5)
  val accessedElement = aSequence(2) // output: 3

  // vectors: fast Seq implementation, very fast for large data structures
  val aVector: Vector[Int] = Vector(1, 2, 3, 4, 5)

  // sets
  val aSet: Set[Int] = Set(1, 2, 3, 4, 1, 2, 3, 4) // Set.apply(1,2,3,4) or Set(1,2,3,4)
  val hasElement = aSet.contains(5) // false
  val anAddedSet = aSet + 5 // Set(1,2,3,4,5) - not necessarily in the same order
  val aRemovedSet = aSet - 3 // Set(1,2,4)

  // ranges
  val aRange = 1 to 100 // 1 to 100 (inclusive)
  val twoByTwo = aRange.map( _ * 2 ) // Vector
  println(twoByTwo)

  // tuples
  val aTuple = ("Python", 3,  "Scala", 3, "C++", 18)

  // maps
  val aPhoneBook: Map[String, Int] = Map(
    ("Jim", 555), ("Pam", 554), "Dwight" -> 553, // same as ("Dwight", 553)
    "Michael" -> 556
  )
}
