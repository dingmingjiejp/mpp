Explanation:
------------------------------------------
It returns false when comparing p1(PersonWithJob) to p2(Person).
Because the equal method of PersonWithJob class returns false if
the comparing object is not an instance of PersonWithJob.

public class PersonWithJob extends Person {
	@Override
	public boolean equals(Object aPerson) {
		...
		if(!(aPerson instanceof PersonWithJob)) return false;
		...
	}
}

Solution:
------------------------------------------
So we need to add a statement that checks if the object is an instance
of Person(the parent class) we need to call parent's equal method to compare it.

public class PersonWithJob extends Person {
	@Override
	public boolean equals(Object aPerson) {
		...
		//Added to fix the problem
		if(aPerson instanceof Person) return super.equals(aPerson);

		if(!(aPerson instanceof PersonWithJob)) return false;
		...
	}
}