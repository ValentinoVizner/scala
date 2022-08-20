package com.rockthejvm

object PatternMatching extends App {
  // switch expression
  val anInteger = 55
  val order = anInteger match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => anInteger + "th"
  }
  // PM is an EXPRESSION

  println(order)

  // Case class decomposition
  case class Person(name: String, age: Int)
  val bob = Person("Bob", 20)


  val personGreeting = bob match {
    case Person(n, a) => s"Hi, my name is $n and I am $a years old"
    case _ => "I don't know my name and age"
  }
  println(personGreeting)

  // deconstructing tuple
  val aTupple = ("Bon Jovi", "Rock")
  val bandDesc = aTupple match {
    case (bandName, genre) => s"$bandName belongs to genre $genre"
    case _ => "I don't know the band name and genre"
  }

  // decomposing lists
  val aList: List[Int] = List(1, 2, 3)
  val listDeconstruction = aList match {
    case List(1, _, _) => "1st element of list"
    case List(_, 2, _) => "2nd element of list"
    case List(_, _, 3) => "3rd element of list"
    case _ => "unknown list"
  }

  // if PM does not match, it will throw MatchError
  // PM will try all cases in sequence

}
