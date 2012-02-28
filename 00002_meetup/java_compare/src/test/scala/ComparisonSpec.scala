package compare.test

import org.scalatest.FunSuite
import org.scalatest.matchers.ShouldMatchers
import compare.scala.{HelloGreeter, Mortal}

class ComparisonSpec extends FunSuite with ShouldMatchers {

  test("feels same") {
    import compare.java.{Person => JPerson}
    import compare.scala.{Person => SPerson}

    val j = new JPerson("java", "", "duke")
    val s = new SPerson("scala", "", "")

    j.firstName should be("java")
    s.firstName should be("scala")

    //the following doesn't even compile
    //j.firstName = "new name"
    //s.firstName = "new name"
  }

  test("override toString note how override is necessary for scala but optional for java") {
    import compare.java.{Person => JPerson}
    import compare.scala.{Person => SPerson}

    val j = new JPerson("java", "duke", "")
    val s = new SPerson("scala", "", "")

    (j toString) should be("""java 'duke' """)
    (s toString) should be("""scala '' """)
  }

  test("null values; require is a good vehicle for testing pre-conditions") {
    import compare.java.{Person => JPerson}
    import compare.scala.{Person => SPerson}

    intercept[IllegalArgumentException] {
      new JPerson(null, "", "duke")
    }

    intercept[IllegalArgumentException] {
      new SPerson(null, "", "duke")
    }
  }

  test("""null values for person2, shows how repeated constructs can be captured easily in abstractions that ease boiler-plate""") {
    import compare.scala.{Person2 => SPerson}
    intercept[IllegalArgumentException] {
      new SPerson(null, "", "duke")
    }
  }


  test("equals, isInnstanceOf and asInstanceOf are verbose for a reason (alternative use patern matching)") {
    import compare.java.{Person => JPerson}
    import compare.scala.{Person => SPerson}

    val j1 = new JPerson("java", "duke", "")
    val j2 = new JPerson("java", "duke", "")

    val s1 = new SPerson("scala", "dude", "")
    val s2 = new SPerson("scala", "dude", "")

    j1 should be(j2)
    s1 should be(s2)
  }

  //this is about time when you should realize scala is better and dump java


  test("equals take two, case classes are great and help remove a lot of the boilerplate (equals, hashcode and toString)") {
    import compare.scala.{Individual => Person}

    val s1 = Person("scala", "dude", "")
    val s2 = Person("scala", "dude", "")

    s1 should be(s2)
    s1.hashCode() should be(s2.hashCode())
    s1.toString should be("Individual(scala,dude,)")
  }


  test("greet a person; traits .. see the scala alternative for a java interface, can have implementation" +
    " and objects the replacement for statics where some objects are special and are called companion objects ") {
    import compare.scala.{Individual => IPerson}
    import compare.scala.{Person => SPerson}

    val s1 = IPerson("scala", "", "dude")
    val s2 = new SPerson("scala", "", "dude")

    s1.isInstanceOf[Mortal] should be(true)
    s2.isInstanceOf[Mortal] should be(true)

    HelloGreeter.greet(s1) should be("Hello mortal scala 'dude' ")

  }

  test("mixins: we mix individual with hellogreeter, shows defs can be replaced by vals in certain situations") {
    import compare.scala.{IndividualGreeter => APersonWhoCanGreet}

    val tom = new APersonWhoCanGreet("tom", "", "dude")
    val harry = new APersonWhoCanGreet("harry", "", "dude")

    tom.greet(harry) should be("Hello mortal harry 'dude' ")
  }


  test("and so much more, a taste of things possible") {
    trait SelfAwareMortalGreeter {
      self: Mortal =>
      def greet(m: Mortal) = "Hi! I am %s. Are you %s?".format(self, m)
    }

    trait BetterPresenter {
      self: Mortal =>
      override def toString() = "%s '%s' %s".format(firstName, petName, lastName)
    }

    //we correct the order to a more pleasing combo that places petname in the middle
    case class ConsciousIndividual(firstName: String, petName: String, lastName: String)
      extends Mortal
      with BetterPresenter
      with SelfAwareMortalGreeter

    //the following will not compile, try it!
    //case class ConsciousIndividual(firstName: String, petName: String, lastName: String) with SelfAwareMortalGreeter

    //Wow!
    ConsciousIndividual("Homer", "Doh!", "Simpson") greet ConsciousIndividual("James", "007", "Bond") should be("Hi! I am Homer 'Doh!' Simpson. Are you James '007' Bond?")

  }
  
  
  def tillNextTime = pending

  test("optional values: pet name is optional")(tillNextTime)

}