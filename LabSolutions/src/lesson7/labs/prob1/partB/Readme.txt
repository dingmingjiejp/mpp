It does not work correctly because in Employee class, 
it was not overridden equals method of Object correctly.


previous version was =>

	public boolean equals(Employee e) {
		return e.name.equals(name) && e.salary == salary;
	}
	
	
	
corrected version is =>
Add override to check properly overriding and change parameter class as Object.

	@Override
	public boolean equals(Object e) {
		if (e==null)
			return false;
		if(!(e instanceof Employee)) return false;
		Employee emp = (Employee) e;
		return emp.name.equals(name) && emp.salary == salary;
	}
	
	