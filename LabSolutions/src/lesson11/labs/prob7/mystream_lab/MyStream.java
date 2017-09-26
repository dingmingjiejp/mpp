package lesson11.labs.prob7.mystream_lab;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class MyStream<T> {
	private List<T> elements;
	public static <T> MyStream<T> of(List<T> aList) {
		MyStream<T> stream=new MyStream<T>(aList);
		return stream;
	}
	private MyStream(List<T> aList) {
		elements=new ArrayList<T>();
		if (aList!=null)
			aList.forEach(e->elements.add(e));
	}
	
	public static <S> MyStream<S> concat(MyStream<S> s1, MyStream<S> s2) {
		List<S> list=new ArrayList<S>();
		list.addAll(s1.asList());
		list.addAll(s2.asList());
		
		return new MyStream<S>(list);
	}
	
	public <R> MyStream<R> flatMap(Function<T, MyStream<R>> mapper) {
		MyStream<R> mystream=new MyStream<R>(null);
		for (T e:elements)
		{
			MyStream<R> stream=mapper.apply(e);
			mystream=MyStream.concat(mystream, stream);
		}
		
		return mystream;
	}
	
	public List<T> asList() {
		return elements;
	}
	
	public <R> MyStream<R> map(Function<T,R> mapper) {
		List<R> list=new ArrayList<R>();
		elements.forEach(e->
		{
			list.add(mapper.apply(e));
		});
		
		return new MyStream(list);
	}
	
	public MyStream<T> filter(Predicate<T> predicate) {
		List<T> list=new ArrayList<T>();
		elements.forEach(e->
		{
			if (predicate.test(e))
				list.add(e);
		});
		
		return new MyStream(list);
	}
}
