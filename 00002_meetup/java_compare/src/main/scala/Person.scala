package compare.scala

class Person(val firstName: String,  val lastName: String,  val petName: String)  extends Mortal {
  //demo how to use scala to reduce boilerplate.
  require(firstName != null, "first name's null")
  require(lastName != null, "last name's null")
  require(petName != null, "pet name's null")

  def someMethod() = "my string"

  //override is required
  override def toString() = "%s '%s' %s".format(firstName, petName, lastName)

  override def equals(obj: Any) = (obj.isInstanceOf[Person] && equalTo(obj.asInstanceOf[Person]))

  //I predicted it and I made it, find the error in the following line
  //private def equalTo(p: Person) = this.firstName == p.firstName && this.petName == p.lastName && this.lastName == p.lastName
  private def equalTo(p: Person) = this.firstName == p.firstName && this.petName == p.petName && this.lastName == p.lastName
}

object Person {
   val CONSTANT = "bla"
}

class Person2(val firstName: String,  val lastName: String,  val petName: String) extends Mortal {
  satisfies(Map(
    (firstName != null) -> "first name's null",
    (lastName != null) -> "last name's null",
    (petName != null) -> "pet name's null")
  )

  def satisfies(m: Map[Boolean, String]) = m.map(p => require(p._1, p._2))

  //override is required
  override def toString() = "%s '%s' %s".format(firstName, petName, lastName)
}

case class Individual(firstName: String,  lastName: String,  petName: String) extends Mortal

trait Mortal {
  def firstName: String
  def petName: String
  def lastName: String
}

trait MortalGreeter {
  def greet(m: Mortal): String
}

//aha traits can be used for implementations too! Mixins!
trait HelloGreeter extends MortalGreeter {
  def greet(m: Mortal) =
    "Hello mortal %s '%s' %s".format(m.firstName, m.petName, m.lastName)
}

//the following is a singleton
object HelloGreeter extends HelloGreeter

//mixin in action ->
class IndividualGreeter(override val firstName: String,  override val lastName: String, override val petName: String) extends Individual(firstName, lastName, petName) with HelloGreeter