# Streamer
A small piece of code to provide a few methods from Java 8 Streams that often doesn't require PipeLines.
The main purpose of this utility is to execute the methods on-the-go instead of maintaing a stack till we hit a collect/reduce method.

Spliterator method is already available in the code. Use it if parallelStream is required. 
