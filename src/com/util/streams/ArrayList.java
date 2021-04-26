//$Id$
package com.util.streams;

import java.util.Collection;
import java.util.Iterator;
import java.util.Objects;
import java.util.Spliterator;
import java.util.function.Consumer;

class ArrayList<T> implements Iterable<T>{
	
	int maxCapacity;
	
	Object[] ans;
	
	ArrayList(Collection<? extends T> c) {
		ans = c.toArray();
		maxCapacity = ans.length-1;
	}
	
	ArrayList(int capacity) {
		maxCapacity = capacity;
	}
	
	
	public void remove(int srcIndex, int destIndex) {
		System.arraycopy(ans, srcIndex, ans, destIndex, size() - (srcIndex - destIndex));
		maxCapacity -= (srcIndex - destIndex);
	}
	
	ArrayList(Object[] copy) {
		ans = copy;
		maxCapacity = ans.length-1;
	}
	
	public int size() {
		return maxCapacity+1;
	}

	@SuppressWarnings("unchecked")
	public T get(int index) {
		return (T)ans[index];
	}
	
	@Override
	public Iterator<T> iterator() {
		return new ArrIterator<T>();
	}
	
	public static <T,R> ArrayList<T> convertTo(ArrayList<R> that, StreamFunction<? super R,? extends T> x) {
		Object[] res = new Object[that.size()];
		for(int i = 0 ; i < that.size(); ++i) {
			@SuppressWarnings("unchecked")
			R temp = (R)that.ans[i];
			res[i] = x.apply(temp);
		}
		ArrayList<T> y = new ArrayList<T>(res);
		return y;
	}
	
	class ArrIterator<Y> implements Iterator<Y>{
		int curr = -1;
		@Override
		public boolean hasNext() {
			return ++curr <= maxCapacity;
		}

		
		@Override
		@SuppressWarnings("unchecked")
		public Y next() {
			return (Y) ans[curr];
		}
		
	}
	
	@SuppressWarnings("unchecked")
	Collection<T> toCollection() {
		Collection<T> res = new java.util.ArrayList<T>();
		
		for(int i = 0; i <= maxCapacity; ++i) res.add((T)ans[i]);
		return res;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public Spliterator<T> spliterator() {
		return new ArrayListSpliterator<T>((T[])ans, -1, maxCapacity);
	}
	
	class ArrayListSpliterator<H> implements Spliterator<H> {
		
		H[] halfAns;
		int maxCapacity, start = -1;
		
		ArrayListSpliterator(H[] half, int start, int end) {
			halfAns = half;
			maxCapacity = end;
			this.start = start;
		}
		
		@Override
		public boolean tryAdvance(Consumer<? super H> action) {
			
			Objects.requireNonNull(action);
			if(start < maxCapacity) {
				action.accept(halfAns[++start]);
			}
			
			return start < maxCapacity;
		}

		@Override
		public Spliterator<H> trySplit() {
			if((start+1) == maxCapacity) return null;
			
			int toSplitIndex = (maxCapacity - start)/2;			
			ArrayListSpliterator<H> subItr = new ArrayListSpliterator<>(halfAns, start, start+toSplitIndex);
			start +=toSplitIndex;
			return subItr;
			
		}

		@Override
		public long estimateSize() {
			return maxCapacity-start;
		}

		@Override
		public int characteristics() {
			return Spliterator.SIZED | Spliterator.SUBSIZED | Spliterator.ORDERED;
		}
		
		@Override
		public void forEachRemaining(Consumer<? super H> action) {
			Spliterator.super.forEachRemaining(action);
		}
		
	}
	
}

