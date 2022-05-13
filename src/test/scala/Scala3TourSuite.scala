package com.example

import cats.effect.IO
import munit.CatsEffectSuite
import munit.Clue.generate

import scala.annotation.tailrec
import scala.language.{ postfixOps, unsafeNulls }

class Scala3TourSuite extends CatsEffectSuite {
  test("scala 3 if") {
    def scala3If(i: Int) =
      if i > 0 then "positive"
      else "negative"
      end if

    assertEquals(scala3If(1), "positive")
    assertEquals(scala3If(0), "negative")
    assertEquals(scala3If(-1), "negative")
  }

  test("scala 3 context parameters") {
    case class LoggingContext(username: String)

    def makeTransfer(amount: Int)(using c: => LoggingContext): String =
      s"${c.username} is making a transfer amount $amount"

    given lc: LoggingContext = LoggingContext("John")

    assertEquals(makeTransfer(4), "John is making a transfer amount 4")
  }

  test("scala 3 extensions") {
    extension (s: String)
      def isPalindrome(): Boolean = {
        val withoutSpaces = s.replaceAll("\\s", "").nn.toLowerCase
        withoutSpaces == withoutSpaces.reverse
      }

    assertEquals("Anna".isPalindrome(), true)
    assertEquals("anna".isPalindrome(), true)
    assertEquals("cat".isPalindrome(), false)
  }

  test("explicit nulls") {
    // -Yexplicit-nulls

    val maybeNull: String | Null = "Something"
    assertEquals(maybeNull, "Something")
  }

  test("enums") {
    enum Job(salary: Long):
      case Manager extends Job(4000)
      case Developer extends Job(2000)

    assertEquals(Job.values.toSeq, Seq(Job.Manager, Job.Developer))
    assertEquals(Job.Developer.ordinal, 1)
  }

  test("universal apply") {
    class UniversalApply(val value: String) {}

    val c = UniversalApply("1")

    assertEquals(c.value, "1")
  }

  test("multiversal strict equality") {
    import scala.language.strictEquality

    case class Money(value: Int)
    case class Time(value: Int)

    // val r = Money(1) == Time(1) compilation error
  }

  test("intersection types") {
    trait Trait1
    trait Trait2

    class Class1 extends Trait1
    class Class2 extends Trait1 with Trait2

    // val complexClass1: Trait1 & Trait2 = Class1() compilation error
    val complexClass2: Trait1 & Trait2 = Class2()
  }
}
