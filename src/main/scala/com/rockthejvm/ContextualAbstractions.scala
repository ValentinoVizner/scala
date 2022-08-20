package com.rockthejvm

import com.rockthejvm.ObjectOriented.Person

object ContextualAbstractions {

  /*
    * Contextual Abstraction
    * 1 - context parameters/arguments *
   */
  val aList: List[Int] = List(2, 1, 3, 4)
  val anOrderedList: List[Int] = aList.sorted // (descOrdering) inbuilt

  // Ordering, given keyword overrides inbuilt ordering from standard library
  given descOrdering: Ordering[Int] = Ordering.fromLessThan(_ > _) // (a,b) => a > b

  // analogous to implicit val
  trait Combinator[A] { // monoid
    def combine(a: A, b: A): A
  }

  def combineAll[A](list: List[A])(using combinator: Combinator[A]): A =
    list.reduce((a,b) => combinator.combine(a,b))

  given intCombinator: Combinator[Int] = new Combinator[Int] {
    def combine(a: Int, b: Int): Int = a + b
  }
  val theSum: Int = combineAll {
    aList
  } // intCombinator

  /*
  Given places
  - local scope
  - imported scope (import youpackage.{given})
  - companion scope (companion object) with all the types involved in the call
    - companion of list
    - companion of int
    */

  // context bounds
  def combineAll_v2[A](list: List[A])(using Combinator[A]): A = ???
  def combineAll_v3[A: Combinator](list: List[A]): A = ??? // must have a given instance of Combinator[A]

  /*
    where contextual arguments are useful:
    - type classes
    - dependency injection
    - context dependent functionality
    - type-level programming
    -
  */

  /*
 * Contextual Abstraction
  * 2 - extension methods *
  */

  case class User(name: String) {
    def greet: String = s"Hello, my name is $name"
  }

  extension (string: String) def greet: String = User(string).greet // "type enriching" - pimping the string class

  val danielGreet: String = "Daniel".greet

  // POWER
  extension [A] (list: List[A])
    def combineAllValues(using combinator: Combinator[A]): A =
      list.reduce(combinator.combine)

  val theSum_v2: Int = aList.combineAllValues

  def main(args: Array[String]): Unit = {
  println(anOrderedList)
    println(theSum)
    println(theSum_v2)
    println(danielGreet)
  }

}
