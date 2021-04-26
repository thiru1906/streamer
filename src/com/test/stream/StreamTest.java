//$Id$
package com.test.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.util.streams.Stream;
import static java.lang.System.out;

public class StreamTest {

	public static void main(String[] args) {
		List<String> old = new ArrayList<>(Arrays.asList("abracadabra", "Laugh Out Loud!", "Is it though?", "Oh fresh hell!", "Its creepy", "I don't think so", "Luck", "Great", "Jeepers", "Oh MY GOOOOD!", "Joey", "Rachel", "Ross Geller", "Monica Geller", "Chandler Muriel Binggg!", "Sheldon Cooper", "Rajesh Koothrapali", "Howard Walowitz", "Stuart Bloom", "Penny - Wyatt", "Leonard Hafstader", "Chernobyl", "Neil DeGrass Tyson", "Late Show", "Ellen Show", "Buzz Aldrin", "Forrest Gump", "Identity", "Inglorious Biscuits", "Death Note", "Kuroku No Basket", "Black Clover", "Lord of the Rings", "Harry Potter", "Dhuruvangal Pathinaaru", "K.G.F", "American Sniper", "Project Almanac", "Predestination", "Interstellar", "World War Z", "The exam", "Heat", "Flubber", "Honey I shrunk the Kids!", "Java", "Google", "Atchaya", "DiCaprio", "The Usual Suspects", "Amy Farah Fowler", "Pricess Consuella ", "Pheobe Hannigan", "Rejina Philanje", "Virat Kohli", "Ken Adams"));
		
		
		out.println(
	   Stream.stream(old)
	   .computeNow(true)
	   .map(a -> a.length())
	   .toCollection()
	   );
		
		
		
		out.println(
		Stream.stream(old)
		.computeNow(true)
		.filter(a -> a.length() > 15)
		.map(a ->a.split("[ ]")[0])
		.filter(a -> a.startsWith(""))
		.toCollection()
		);
	
		out.println(
		Stream.stream(old)
		.computeNow(true)
		.dropWhile(a -> a.length() > 5)
		.toCollection()
		);
		
		out.println(
				Stream.stream(old)
				.computeNow(true)
				.takeWhile(a -> a.length() > 5)
				.toCollection()
				);
		
	}
	
}
