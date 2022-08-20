package com.rockthejvm

import scala.annotation.unused
import scala.concurrent.ExecutionContext.Implicits.global
import scala.concurrent.Future
import scala.util.{Failure, Success, Try}

object Advanced extends App{

  /*
    lazy evaluation
   */
  lazy val lazyValue = 2
  lazy val lazyValueWithSideEffect = {
    println("hello")
    2
  } // this will be evaluated only when it is used, println will not be evaluated on runtime either

  val eagerValue = lazyValueWithSideEffect + 1 // this will be evaluated on runtime and println will be executed
  // usefull in infinite collections, lazy evaluation is used in collections when we need to iterate over them

  /*
    "pseudo-collections": Option, Try, Either, Future
   */
  def methodWhichCanReturnNull(): String = "hello Scala"
  val anOption = Option(methodWhichCanReturnNull()) // Some("hello Scala") or None
  // option = "collection" which contains at most one element: Some(value) or None

  val stringProcessing = anOption match {
    case Some(string) => s"I have obtained a string: $string"
    case None => "I have not obtained a string"
  }

  def methodWhichCanThrowException(): String = throw new RuntimeException
  val aTry = Try(methodWhichCanThrowException()) // Success("hello Scala") or Failure(exception)
  // a try = "collection" with either a value if the code went well, or an exception if the code failed

  val anotherStringProcessing = aTry match {
    case Success(string) => s"I have obtained a string: $string"
    case Failure(exception) => "I have not obtained a string"
  }
  // map, flatMap, filter

  /*
    Evaluate something on another thread
    (asynchronous programming)
   */
  val aFuture = Future({  // Future.apply(block)
    println("loading...")
    Thread.sleep(1000)
    println("I have computed a value.")
    67

  })
  // a future is a "collection" which contains a value if it is evaluated
  // future is composable with map, flatMap, filter
  // monads = functions which can be composed with other functions

  // if we want to see the value from aFuture, we need to wait for it to be computed more than 1000s
  Thread.sleep(2000)

  /*
    Implicits basics
   */

  // 1. implicit arguments
  def aMethodWithImplicitArgument(implicit arg: Int): Unit = arg + 1
  implicit val myImplicitArgument: Int = 46
  println(aMethodWithImplicitArgument) // aMethodWithImplicitArgument(myImplicitArgument) = 47

  // 2. implicit conversions
  implicit class MyRichInteger(n: Int) {
    def isEven: Boolean = n % 2 == 0
  }

  println(23.isEven) // new MyRichInteger(23).isEven = false
}
