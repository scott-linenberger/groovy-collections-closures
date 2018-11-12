package com.linenberger

import com.linenberger.objects.VideoGame
import org.codehaus.groovy.transform.sc.ListOfExpressionsExpression

/* This is a Walkthrough, calling it's runExamples method will println example output */
class CollectionsWalkthrough {

	def runExamples() {
		println("Welcome to the Collections Walkthrough, here we go!")
		
		/* Let's talk about collections! Arrays, Lists, Sets and Maps */
		demoArrays()
		demoLists()
		demoSets()
		demoMaps()
	}

	def demoArrays() {
		println("Let's look at some arrays..")

		/* arrays are a very basic data structure:
		 *  -> fixed size (can't grow if you need to add more elements)
		 *  -> index based
		 *  -> all items must be the same type: String, number, class, etc..
		 *  -> insertion order is maintained
		 *  -> fast and take up little space
		 *  -> arrays are good when you have a fixed, known number of itemsz
		 *  -> generally, lists are preferred for flexibility and consistency
		 * 
		 * There are multiple ways to delcare an array. Keep 
		 * a sharp eye though. As you'll see, declaring an array
		 * and declaring a list look very similar.
		 * 
		 * Just remember, adding the data type in the declaration,
		 * makes it an array. For example:
		 * 
		 * Here, the data type is 'String' in 'String[]` makes this an array 
		 * String[] myArray = ["Jack", "Jill"]
		 * 
		 */
		
		/* this array is a fixed size, which is determined by items we assigned */
		String[] studentNames = ["Jack", "Jill", "Zach", "Cynthia", "Alex"]
		
		/* this array is a fixed size, which we explicitly set */
		def teacherNames = new String[5]
		
		/* now we can add 5 items using indexes*/
		teacherNames[0] = "Mr. Smith"
		teacherNames[1] = "Mrs. Belcher"
		teacherNames[2] = "Ms. Applebloom"
		teacherNames[3] = "Mr. Johanssen"
		teacherNames[4] = "Ms. Armstrong"
		
		/* you may also see arrays created using `toArray` or using the `as` syntax */
		def janitorNames = ["Mr. Holmes", "Mrs. Westing"].toArray();
		
		def principalNames = ["Mr. Holbrook", "Mr. Hamm", "Mrs. Morrow"] as String[]
		
		/* using the indexes lets you put items into and get items out of an array
		 * You can get items out of an array using the index with square braces like this
		 */
		println("One of the principals is named: " + principalNames[1])
		
		/* You can also get items using `getAt` like this */
		println("And, " + principalNames.getAt(2) + " is also a principal.") 
	}
	
	def demoLists() {
		/* Lists are similar to arrays, but they can grow  dynamically 
		 * and do not required you to set the size when creating one. 
		 *  -> grow as you add more items
		 *  -> maintain insertion order
		 *  -> all items must be of the same type: String, numbers, classes, etc..
		 *  -> index based (just like arrays)
		 *  -> based on Java's ArrayList
		 *  -> should be preferred over arrays
		 *  -> use `add` and `get` to insert and retrieve items
		 *  
		 *  Delcaring a list looks VERY similar to declaring an array,
		 *  just remember, when declaring a List, we do NOT set the size,
		 *  or specify the type.
		 *  
		 *  This is an array 
		 *  def myArray = String[5]
		 *  
		 *  This is a list
		 *  def myList = []
		 */
		
		/* this is a new, empty list */
		def listOf80sBands = [] 
		
		/* adding items to our 80s bands */
		listOf80sBands.add("Van Halen")
		listOf80sBands.add("Metallica")
		listOf80sBands.add("Poison")
		listOf80sBands.add("Journey")
		
		println("You can't go to karaoke without hearing: " + listOf80sBands.get(3) + " and " + listOf80sBands.get(2))
		
		/* you can also construct a list with items like this */
		def listOfFlavors = ["Vanilla", "Chocolate", "Strawberry"]
		
		/* look, we can add more items! If this were an array, we couldn't add more items 
		 * after we declared a populated list of a fixed size
		 */
		listOfFlavors.add("Neopolitan")
		
		
		println("There are " + listOfFlavors.size() + " popular flavors of icecream.")
		
		/* you can actually set the list to a Java ArrayList too */
		def listOfAnimals = new ArrayList<String>();
		
		listOfAnimals.add("Tiger")
		listOfAnimals.add("Lions")
		listOfAnimals.add("Bears")
		listOfAnimals.add("Short-faced Bear")
		
		
		println("My favorite anmial of all time is the: " + listOfAnimals.get(3))
	}
	
	def demoSets() {
		/* sets are identical to Lists, however, sets maintain uniqueness 
		 * That is: an item can only be added once. 
		 * 
		 * Sets:
		 *  -> grow as you add more items
		 *  -> maintains insertion order
		 *  -> based on Java's HashSet or LinkedHashSet
		 *  -> should be used when you do not want duplicates
		 *  
		 *  You can convert lists to sets, making the items in the list unique
		 *  using `toSet()` or `as <TypeOfSet>` like `as Set` or `as LinkedHashSet`
		 */
		
		def setOfCars = ["Ferrari", "Lamborghini", "Toyota", "Toyota"] as Set
		
		/* if we iterate this set, we'll see "Toyota" is only in the set one time */
		println("These are some vehicle manufacturers you may have heard of: ")
		setOfCars.forEach({println(it)}) // this is a shorthand for printing every item in the set
		
		
		/* we can also create a set from a list */
		def listOfFruit = ['Apples', 'Oranges', 'Bananas']
		
		/* look! adding more items, because we can do that with lists and sets */
		/* no fixed sizes here! We just add as many items as we want! */
		listOfFruit.add('Kiwi')
		listOfFruit.add('Kiwi')
		listOfFruit.add('Kiwi')
		listOfFruit.add('Kiwi')
		
		println("How about these types of fruit!")
		listOfFruit.forEach({println(it)})
		
		/* oh no! We have too many kiwi! Lets convert this fruit list to a set */
		println("Woops! I said 'Kiwi' too many times, let me try that again")
		def setOfFruit = listOfFruit.toSet();
	
		setOfFruit.forEach({println(it)})
		println("That's better! Now there is only 1 of each type.")
	}
	
	def demoMaps() {
		/* Maps
		 *  -> uses key value pairs for entering items
		 *  -> does not maintain insertion order
		 *  -> grows dynamically like a List or Set
		 *  -> lets you retrieve items with keys
		 * 
		 * Maps are different from arrays, lists and sets in that Maps use key-value pairs
		 * which let you retrieve items you want using keys so you don't have to iterate over
		 * each item in the collection to find what you want. Just like Sets, Maps have the 
		 * concept of being unique, in that the key, if added multiple times, only shows up
		 * once in the map. Unlike an Array, List or Set, the Map does not maintain your 
		 * insertion order. So, a Map is great for retrieving items without iterating, but
		 * should not be used when order is important.
		 * 
		 * Maps use 'hashing' to efficiently find the item you want. Hence the name: HashMap.
		 * Sets and lists  also use hashing that allows you to retrieve an index. Hence: HashSet
		 * 
		 * Just like other Collections, Maps can be instantiated several ways as well.
		 */
		
		
		/* creating a map with keys: Note how you can skip quotes around the keys here */
		def mapOfDefinitions = [
			optimistic: 'hopeful and confident about the future.',
			extol: 'praise enthusiastically.', // skippqued quotes around the key 
			"gold": 'a deep lustrous yellow or yellow-brown color.' // used quotes around the key
		]
		
		/* adding a new item */
		mapOfDefinitions.put('developer', 'organism that converts caffeine and calories into code')
		
		/* retrieve an item by its key */
		def definitionOfGold = mapOfDefinitions.get('gold')
		println("One could define gold as: " + definitionOfGold)
		
		/* you can also create a new empty map using the `[:]` syntax */
		def mapOfGames = [:]
		
		mapOfGames.put('Zelda', new VideoGame('The Legend of Zelda', 'Nintendo', 1986)) // Using an object as the value this time
		mapOfGames.put('SuperMarioWorld', new VideoGame('Super Mario World', 'Super Nintendo', 1990))
		
		/* retrieving our video games by name */
		def zelda = mapOfGames.get('Zelda')
		println("The game: '" + zelda.getTitle() + "' was released for " + zelda.getPlatform() + " in " + zelda.getYearReleased())	
		
		/* creating a map using LinkedHashMap */
		def mapOfBattlefield = new LinkedHashMap()
		
		mapOfBattlefield.put('BF5', new VideoGame('Battlefield 5', 'PC', 2018))
		mapOfBattlefield.put('BF2', new VideoGame('Battlefield 2', 'PC', 2005))
		mapOfBattlefield.put('BF3', new VideoGame('Battlefield 3', 'PC', 2011))
		
		/* retrieving Battlefield games using property notation to get this */
		def bf2 = mapOfBattlefield.BF2
		// also could have used mapOfBattlefield.get('BF2')
		
		println(bf2.getTitle() + " was released for " + bf2.getPlatform() + " in " + bf2.getYearReleased())
	
	}
}
