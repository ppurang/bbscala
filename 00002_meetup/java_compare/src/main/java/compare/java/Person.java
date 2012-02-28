package compare.java;

public class Person {
    public final String firstName;
    public final String lastName;
    public final String petName;

    public Person(final String firstName, final String petName, final String lastName) {
        if(firstName == null) throw new IllegalArgumentException("first name is null");
        if(lastName == null) throw new IllegalArgumentException("last name is null");
        if(petName == null) throw new IllegalArgumentException("pet name is null");
        this.firstName = firstName;
        this.petName = petName;
        this.lastName = lastName;
    }

    public String toString() {
        return String.format("%s '%s' %s", firstName, petName, lastName);
    }

    @Override
    public boolean equals(final Object obj) {
        if(this == obj) {
            return true;
        } else if(obj instanceof Person) {
            final Person that = (Person) obj;
            //there is a possibility of a very subtle bug here.. and subtle bugs are hard to find! except if you did TDD but then ..
            return  this.firstName.equals(that.firstName) && this.petName.equals(that.petName)  && this.lastName.equals(that.lastName);
        } else {
            return false;
        }
    }

    //we need hashcode now...
}

interface Mortal {
    public String firstName();
}