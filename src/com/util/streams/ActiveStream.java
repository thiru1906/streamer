//$Id$
package com.util.streams;
import java.util.Collection;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Predicate;

import com.util.streams.Stream;

class ActiveStream<T> implements Stream<T>{
	
	ArrayList<T> obj;
	
	ActiveStream(Collection<? extends T> c) {
		obj = new ArrayList<T>(c);
	}

	ActiveStream(ArrayList<T> r) {
		this.obj= r;
	}

	public Stream<T> filter(Predicate<? super T> predicate) {
		for(int i = 0; i < obj.size(); ++i) {
			T t = (T)obj.get(i);
			if(!predicate.test(t)) {
				obj.remove(i+1, i--);
			}
		}
		return this;
	}	
	
	
	public <R> Stream<R> map(StreamFunction<? super T,? extends R> x) {
		return new ActiveStream<R>(ArrayList.convertTo(obj, x));
	}
	
	@Override
	public Collection<? extends T> toCollection() {
		return Stream.toCollect(obj);
	}

	@Override
	public Optional<T> findFirst() {
		if(obj.size() == 0) return Optional.empty();
		return (Optional<T>) Optional.ofNullable(obj.get(0));
	}

	@Override
	public Optional<T> findAny() {
		return findFirst();
	}

	@Override
	public Optional<T> anyMatch(Predicate<? super T> x) {
		for(T y : obj) {
			if(x.test(y)) {
				return Optional.ofNullable(y);
			}
		}
		return Optional.empty();
	}

	@Override
	public boolean noneMatch(Predicate<? super T> predicate) {
		if(obj.size() == 0) return true;
		
		for(T x : obj) {
			if(predicate.test(x)) return false;
		}
		return true;
	}

	@Override
	public boolean allMatch(Predicate<? super T> predicate) {
		if(obj.size() == 0) return true;
		
		for(T x : obj) {
			if(!predicate.test(x)) return false;
		}
		
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		return (T[]) obj.ans;
	}

	@Override
	public void forEach(Consumer<? super T> consumer) {
		Objects.requireNonNull(consumer);
		for(int i = 0; i < obj.size(); ++i) {
			consumer.accept((T)obj.get(i));
		}
	}

	@Override
	public Stream<T> takeWhile(Predicate<? super T> predicate) {
		for(int i = 0 ; i < obj.size(); ++i) {
			if(!predicate.test(obj.get(i))) {
				obj.maxCapacity = i-1;
				break;
			}
		}
		return this;
	}

	@Override
	public Stream<T> dropWhile(Predicate<? super T> predicate) {
		for(int i = 0 ; i < obj.size(); ++i) {
			if(!predicate.test(obj.get(i))) {
				obj.remove(i, 0);
				break;
			}
		}
		return this;
	}	
}