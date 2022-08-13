package com.rockthejvm

import scala.annotation.unused

object Basics extends App {
  // define values
  val meaningOfLife: Int = 42 // const int meaningOflife = 42; in Java

  // meaningOfLife = 1; // reassign value is illegal in Scala

  // figure out type of value
  val booleanVal = false; // type is optional in Scala

  // string and string operations
  val aString = "Scala"; // type is String
  val aComposedString = "This is " + aString
  val aInterpolatedString = s"$aString is not $aString"

  // expressions = structures that can be reduced to a value
  val anExpression = 1 + 2

  // if-expression
  val ifExpression = if (aString == "Scala") "yes" else "no"
  val chainedExpression =
    if (aString == "Scala") "yes"
    else if (aString == "Java") "no"
    else if (aString == "Python") "hell no"
    else "neither"

  // code-blocks
  val aCodeBlock = {
    val y = 2
    val z = y + 1
    // value of the block is the value of the last expression
    z + 100
  }

  // define a function
  def aFunction(x: Int, y: String): String = y + " " + x

  // recursive function
  def factorial(n: Int): Int = {
    if (n <= 1) 1
    else n * factorial(n - 1)
  }

  // In Scala we do not use loops or iterations, but instead we use RECURSION to solve problems.

  // Unit type is used to represent void in Scala.
  println("Hello World")

  def myUnitReturnFunc(): Unit = {
    println("Hello World, wrong.")
  }

  @unused
  val theUnit: Unit = ()
}
