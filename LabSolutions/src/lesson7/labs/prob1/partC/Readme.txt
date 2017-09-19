It does not work correctly because in removeDuplicates method
it used HashMap to find the duplicated objects.
Whenever equals is overridden, hashCode should also be overridden.
The hashCode method should take into account the same fields as the equals method
So we need to override hashCode as well to use an object as a key in HashMap.


corrected version is =>
Override hashCode method in Employee class

	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + name.hashCode();
		result = 31 * result + salary;
		return result;
	}