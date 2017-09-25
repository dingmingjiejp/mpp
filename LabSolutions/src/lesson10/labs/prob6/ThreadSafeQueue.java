package lesson10.labs.prob6;

//Show the methods are not threadsafe.
//Then modify so that they are threadsafe.
public class ThreadSafeQueue {
	class Node {
		Object value;
		Node next;
	}
	private Node head;
	private Node tail;

	// ThreadSafe add method
	public synchronized void add(Object newValue) {
		Node n = new Node();
		if(head == null) head = n;
		else tail.next = n;
		tail = n;
		tail.value = newValue;
	}

	// ThreadSafe remove method
	public synchronized Object remove() {
		if(head == null) return null;
		Node n = head;
		head = n.next;
		return n.value;
	}
}
