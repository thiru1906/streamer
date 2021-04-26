//$Id$
package com.util.streams;


@FunctionalInterface
public interface StreamFunction<A,B> {

	B apply(A a) throws RuntimeException;
	
	
	static <A> Boolean isNull(A a) {
		return a == null;
	}
	
	
	
}
