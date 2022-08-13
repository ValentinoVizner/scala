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

  Conclusion: FunctionX
   */
}
