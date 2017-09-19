It does not work correctly because in removeDuplicates,
it changes visited variable by setVisited method when the list items with same key appears.
This visited variable is involve in equal and hashCode overridden methods.
Since visited variable changed, in the third duplication entry it cannot be assumed as same.
That's why the third duplication entry cannot be removed from the list.
So we do not need to involve visited variable in equal and hashCode since we need to change.


previous version was =>

	@Override
	public boolean equals(Object ob) {
		if(ob == null) return false;
		if(!(ob instanceof Employee)) return false;
		Employee emp = (Employee)ob;
		return emp.name.equals(name) && emp.salary == salary && emp.visited == visited;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		long longval = Double.doubleToLongBits(salary);
		int salaryHash = (int) (longval ^ (longval >>> 32));
		result += 31 * result + name.hashCode();
		result += 31 * result + salaryHash;
		result += 31 * result + (visited ? 1 : 0);
		return result;
	}


corrected version is =>
Remove visited variable in equals and hashCode methods.

	@Override
	public boolean equals(Object ob) {
		if(ob == null) return false;
		if(!(ob instanceof Employee)) return false;
		Employee emp = (Employee)ob;
		return emp.name.equals(name) && emp.salary == salary;
	}
	
	@Override
	public int hashCode() {
		int result = 17;
		long longval = Double.doubleToLongBits(salary);
		int salaryHash = (int) (longval ^ (longval >>> 32));
		result += 31 * result + name.hashCode();
		result += 31 * result + salaryHash;
		return result;
	}