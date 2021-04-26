//$Id$
package com.util.streams;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;


public interface Stream<T> {
	
	public Stream<T> filter(Predicate<? super T> x);

	public Collection<? extends T> toCollection();
	
	public <R> Stream<R> map(StreamFunction<? super T, ? extends R> mapper);
	
	public Optional<T> findFirst();
	
	public Optional<T> findAny();
	
	public Optional<T> anyMatch(Predicate<? super T> x);
	
	public boolean noneMatch(Predicate<? super T> predicate);
	
	public boolean allMatch(Predicate<? super T> predicate);
	
	public Stream<T> takeWhile(Predicate<? super T> predicate);
	
	public Stream<T> dropWhile(Predicate<? super T> predicate);
	
	public T[] toArray();

	public void forEach(Consumer<? super T> consumer);

	//stream init method..
	public static <T> Streamer<T> stream(Collection<? extends T> col) {
		return new Streamer<T>(col);
	}
	
	static <T> Collection<? extends T> toCollect(ArrayList<T> t) {
		return t.toCollection();
	}
	
	
	static class Streamer<T> {
		
		Collection<? extends T> c;
		
		public Streamer(Collection<? extends T> col) {
			this.c = col;
		}
		
		public Stream<T> computeNow(Boolean now) {
			return new ActiveStream<T>(c);
			//return now ? new ActiveStream<T>(c) :  new LazyStream<T>(c); 
		}
		
	}

}