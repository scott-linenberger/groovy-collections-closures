package com.linenberger

import java.lang.annotation.Repeatable

import org.codehaus.groovy.util.ReleaseInfo

import com.linenberger.objects.VideoGame

class ClosureWalkthrough {

	def runExamples() {
		/*
		 * Closures are functions: which means, they are like methods that 
		 * do not require a class. Closures can take parameters and 
		 * return a result. Closures work like Lambdas and are anonymous
		 * blocks of executable code. 
		 * 
		 * Closures are special because their scope has access to the 
		 * variables in which the closure was defined. Whoa..that's a 
		 * confusing statement. Don't worry, the examples will help clarify.
		 * 
		 * The best way to get familiar with closures is to start writing some.
		 * Here we go!
		 */

		/* look at this simple closure, it doesn't take any input parameters 
		 * and doesn't return anything. This closure simply prints some output */
		def printHello = { println("Hello, closures!") }

		/* let's call this closure */
		printHello()

		/* that printHello closure was neat! But, not very useful. Let's see a closure
		 * that takes an input parameter. See how we added `user ->` Now we can pass in
		 * a parameter in place of user when we call the closure*/
		def greetUser = { user -> println("Hello, " + user)}

		/* passing in the user name */
		greetUser('Scott')
		greetUser('Matt')
		greetUser('Jimbo')


		/* Okay, but what about passing more than one parameter into a Closure?? No problem! */
		def customGreeting = { phrase, user -> println(phrase + ", " + user) }

		/* let's call our two parameter Closure */
		customGreeting('Good Morning', 'Sally')
		customGreeting('Good Afternoon', 'Sam')
		customGreeting('Goodnight', 'Agnes')


		/* Closures can be confusing though because of how flexible they are. For example, 
		 * remember `printHello`? It doesn't take any input parameters...Or does it?
		 */
		printHello("Scott") // What the what?? How can I pass an argument to this Closure!?

		/* the reason we can pass an argument to our `printHello` Closure is: when you create a
		 * Closure without a parameter list using `->` there is ALWAYS an implicit parameter: `it`
		 * 
		 * So! Let's delcare a closure again and this time we will not define a parameter list 
		 * using the `->` arrow, but we WILL use the implicit `it` parameter. 
		 * 
		 * We will skip using the arrow `->` and just know the closure has an implicit `it`
		 * that gets assigned to whatever we pass it. However, it is important to know, the 
		 * implicit `it` parameter only works for ONE parameter. If you are going to pass
		 * more than one parameter into a Closure, you need to define the parameter list
		 * using the arrow `->`. 
		 */

		def implicitParamClosure = { println("Welcome to: " + it)} // see! no prameter list, but we used the implicit `it`

		/* now let's call this implicit paramter Closure */
		implicitParamClosure("Jurassic Park")


		/* We can even pass objects to a Closure */
		def frogger = new VideoGame('Frogger', 'PC', 1981)

		def printGameInfo = {game -> println(game.getTitle() + " was released for " + game.getPlatform() + " in " + game.getYearReleased())}

		printGameInfo(frogger)

		/* Hey! Maybe we can use that explicit `it` parameter with an object too. Let's try it */
		def printGameInfo2= { println(it.getTitle() + " was released for " + it.getPlatform() + " in " + it.getYearReleased())}

		printGameInfo2(frogger) // IT WORKS! BUT! Only if you pass in an object with corresponding methods: getPlatform, getTitle, etc...

		/* Okay! But what about returning values? Let's create some Closures that return something */
		def addTwo = { number -> number += 2}

		/* the same as
		 * def addTwo = { number -> return number+=2 }		
		 */

		def additionResult = addTwo(2)
		println(additionResult) // outputs 4

		/* Wait a minute, you said you were going to return something, but I don't see `return` anywhere
		 * In the Closure called `addTwo`!
		 * 
		 * This is another potential point of confusion for Closures. When you want to return something from
		 * a Closure you can shorthand it by putting everything on one line and skip adding the `return` keyword.
		 * This is great for keeping your code tiny, but can be difficult to understand if you're new to Closures.
		 * In fact, you can even skip using the `return` keyword in Closures with multiple lines. Just put 
		 * what you want to return on the last line. However, when using multi-line Closures, it's a good
		 * practice to use the `return` keyword for readability. 
		 */

		/* Okay, so we've made some simple, one-line closures, but what if my closure has more logic? 
		 * Not a problem, just add some space after your input parameters (or after the first curly brace if you are 
		 * using the implicit `it` parameter). 
		 */

		/* this Closure prints the name 5 times */
		def printNameManyTimes = { name ->
			// the input params stay on this first line though!
			for(int i = 0; i < 5; i++) {
				println(name);
			}
		}

		printNameManyTimes("Tom")

		def repeatName = { name ->
			for(int i = 0; i < 5; i++) {
				name += (" " + name)
			}

			// Do this instead: return name
			name // See, we skipped the return keyword. But, DON'T DO THIS!
		}

		/* exact same as `repeatName` but we use the `return` keyword at the end */
		def repeatName2 = { name ->
			for(int i = 0; i < 5; i++) {
				name += (" " + name)
			}

			return name
		}


		def repeatedName = repeatName("Vanessa")
		println(repeatedName)

		/* but what if we use the implicit `it` param with some logic AND pass an object.
		 * Well, that would look like this: */
		
		def videoGameReleasedNote = {
			if (it.getYearReleased() == 1990) {
				return it.getTitle() + " was released the same year Super Nintendo came out."
			}
			
			if (it.getYearReleased() > 1990) {
				return it.getTitle() + " was released after Super Nintendo came out."
			}
			
			if (it.getYearReleased() < 1990) {
				return it.getTitle() + " was released before Super Nintendo came out."
			}
		}
		
		def videoGameReleasedNote2 = { game -> // same as videoGameReleasedNote, but we used an explicit parameter
			if (game.getYearReleased() == 1990) {
				return game.getTitle() + " was released the same year Super Nintendo came out."
			}
			
			if (game.getYearReleased() > 1990) {
				return game.getTitle() + " was released after Super Nintendo came out."
			}
			
			if (game.getYearReleased() < 1990) {
				return igamet.getTitle() + " was released before Super Nintendo came out."
			}
		}
		
		def releaseNoteFrogger = videoGameReleasedNote(frogger);
		def releaseNoteFrogger2 = videoGameReleasedNote2(frogger);
		println(releaseNoteFrogger)
		println(releaseNoteFrogger2)
	}
}
