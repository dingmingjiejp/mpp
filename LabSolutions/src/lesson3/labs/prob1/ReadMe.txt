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
So we do not need to extend PersonWithJob from Person.
Instead of using inheritence, we should use association.
PersonWithJob should contains the Person.
