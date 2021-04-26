# Streamer
A small piece of code to provide a few methods from Java 8 Streams that often doesn't require PipeLines.
The main purpose of this utility is to execute the methods on-the-go instead of maintaing a stack till we hit a collect/reduce method.

Spliterator method is already available in the code. Use it if parallelStream is required. 

Use the code 
```
Stream.stream(Collection<T>)
```
to convert a collection object into a stream.

First thing to do after creating a stream is to set the parameter :: computeNow(true) to make the stream as ActiveStream (No stack)
Eventhough the code doesn't support LazyStream yet, it's better to follow this procedure in case if the LazyStream class is available. 
```
Stream.stream(Collection<T> collection)
    .computeNow(true)
```
Methods available in the current Stream Interface are :
MAP, FILTER, TAKEWHILE, DROPWHILE, TOCOLLECTION, TOARRAY, ANYMATCH, ALLMATCH, NONEMATCH, FINDFIRST, FINDANY, FOREACH

takeWhile & dropWhile methods are referred from Java 9 Streams API.

```
List<String> old = new ArrayList<>(Arrays.asList("abracadabra","Laugh out Loud!","Is it though?"));
Stream.stream(old)
	   .computeNow(true)
	   .map(a -> a.length())
	   .toCollection()
```
