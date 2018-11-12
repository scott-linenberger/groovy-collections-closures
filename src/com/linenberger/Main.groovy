package com.linenberger

class Main {
	
	/* the entrypoint, where our app begins */
	static void main(String[] args) {
		println("Welcome to Groovy Collections and Closure Examples!")
		
		/* Our walkthroughs */
		CollectionsWalkthrough walkthroughCollections = new CollectionsWalkthrough();
		ClosureWalkthrough walkthroughClosure = new ClosureWalkthrough()
		
		
		println("Running walkthroughs...")
		
		/* To run any of these walkthroughs, uncomment their runExamples line */
//		walkthroughCollections.runExamples();
		walkthroughClosure.runExamples();
	}
	
}
