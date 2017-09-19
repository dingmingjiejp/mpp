It does not work correctly because in Employee class, 
it was not overridden Object's equals method correctly.
It checks whether the lists are equal by using contains method of List.
List's contains method compares the pointer of the objects. 
So we need to override equals to compare it correctly.

previous version was =>

	public boolean equals(Employee e) {
		return e.name.equals(name) && e.salary == salary;
	}
	
	
	
corrected version is =>
Overriding the Object's equal method by changing the parameter class as Object.

	@Override
	public boolean equals(Object e) {
		if (e==null)
			return false;
		if(!(e instanceof Employee)) return false;
		Employee emp = (Employee) e;
		return emp.name.equals(name) && emp.salary == salary;
	}
	
	