package com.rockthejvm

object ObjectOriented extends App {
  // java equivalent to: public static void main(String[] args) {

  // class and instance
  class Animal {
    // define fields
    val age: Int = 0
    // define methods
    def eat(): Unit = println("Eating")
  }
  val animal = new Animal

  // inheritance
  class Dog(val name: String) extends Animal {
  } // constructor definition
  val dog = new Dog("Buddy")

  // constructor arguments are NOT fields, you can promote it with val
  dog.name

  // subtype polymorphism
  val newDog: Animal = new Dog("Hatchie")
  newDog.eat() // the most derived method will be called at runtime

  // abstract classes
  abstract class WalkingAnimal {
    val hasLegs = true // public by default, can restrict by using private/protected
    def walk(): Unit
  }

  // "interface" ultimate abstraction
  trait Carnivore {
    def eat(animal: Animal): Unit
  }

  trait Philosopher {
    def ?!(thought: String): Unit // valid method name
  }

  // single class inheritance and multiple trait mixing
  class Crocodile extends Animal with Carnivore with Philosopher {
    override def eat(animal: Animal): Unit = println("crunch")
    override def ?!(thought: String): Unit = println(s"I was thinking: $thought")
  }

  // this is where Scala shines
  val aCroc: Crocodile = new Crocodile
  aCroc.eat {
    newDog
  }
  aCroc eat newDog // methods that have single argument can be called without parentheses, called infix notation = "object method argument"
  aCroc ?! "What if we could fly?"

  // operators in scala are actually methods
  val basicMath = 1 + 2
  val methodMath = 1.+(2) // infix notation, equivalent to 1 + 2

  // anonymous classes
  val dinosaur = new Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinosaur and I am eating everything I want")
  }
  /*
  What compiler does in background:

  class Carnivore_Anonymous_3572 extend Carnivore {
    override def eat(animal: Animal): Unit = println("I am a dinosaur and I am eating everything I want")
  }
  */

  // singleton object
  object MySingleTon {
    val mySpecialValue = 53728
    def mySpecialMethod(): Int = 537
    def apply(x: Int): Int = x + mySpecialMethod()
  }

  MySingleTon.mySpecialMethod()
  MySingleTon.apply(10)
  MySingleTon(10) // equivalent to MySingleTon.apply(10)

  // companion object - companion class with the same name as the Class or traits
  object Animal {
  // companions can access each other's private fields/methods
    // singleton Animal and instance of Animal are different things
  val canLifeIndefinitely = false
  }

  val canLiveForever = Animal.canLifeIndefinitely // "static" fields/methods

  /*
  case classes = lightweight data structures with some boilerplate code
  - sensible equals and hashCode
  - serialization
  - companion with apply method (no need for new keyword)
  - pattern matching
   */
  case class Person(name: String, age: Int)

  val bob: Person = Person("Bob", 20) // Person.apply("Bob", 20)

  // exceptions
  try {
    // code that might throw an exception
    val x: String = null
    x.length // throws NullPointerException
  } catch { // in Java: catch(Exception e) {...}
    case e: Exception => "Something went wrong."
  } finally {
    // execute some code, regardless of what happens
    println("This code is always executed")
  }

  // generics
  abstract class MyList[T] {
    def head: T
    def tail: MyList[T]
  }

  // using a generic with a concrete type
  val aList: List[Int] = List(1,2,3) // List.apply(1,2,3)
  val first = aList.head
  val rest = aList.tail


  // Point #1: In Scala we usually operate with IMMUTABLE values/objects
  // Any modification to an object must RETURN a new object
  /*
    Benefits:
      1) works miracles in multi-threaded environments
      2) helps making sense of the code ("reasoning about")
   */
  val reversedList = aList.reverse // List.reverse(aList), RETURNS a new list

  // Point #2: In Scala we usually operate with CLASSES, not with OBJECTS
  // Point #3: Scala is closest to he OO ideal
}
