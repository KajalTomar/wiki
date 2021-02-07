public class FileReader{
	
	Wiki wiki1;

	//-----------------------------------------------------------------------------------------
	// Constructor
	// ----------------------------------------------------------------------------------------
	public FileReader(){
		wiki1 = new Wiki();
	} 

	// -----------------------------------------------------------------------------------------
	// ReadFile
	//
	// PURPOSE: Reads the file line by line and calls the correct methods to manipulate the wiki.
	// INPUT: file name (String)
	// -----------------------------------------------------------------------------------------
	public void readFile(){
		wiki1.user("Bob");
		wiki1.user("Stacey");
		wiki1.user("Benny");
		wiki1.user("Jo");

		wiki1.printUser("Bob");
		wiki1.printUser("Stacey");		
		wiki1.printUser("Benny");		
		wiki1.printUser("Jo");		
		wiki1.printUser("Bobby");		
		wiki1.printUser("Bo");

		System.out.println("-----------------------------------------");

		wiki1.create("Document 1", "Bob");
		wiki1.create("Document 2", "Bob");
		wiki1.create("Document 3", "Bob");
		wiki1.create("Document 4", "Bob");

		wiki1.append("Document 1", "Bob", "Yo,what's up?");
		wiki1.append("Document 1", "Bob", "Nothing much");
		wiki1.append("Document 1", "Bob", "Niceeeee");

		wiki1.append("Document 1", "Bob", "Blah blah blah?");
		wiki1.append("Document 1", "Bob", "BLah!!!!");

		
		wiki1.append("Document 1", "Bob", "ohkay");
		wiki1.append("Document 1", "Bob", "These are plants");
		wiki1.append("Document 1", "Bob", "I know");

		wiki1.append("Document 1", "Bob", "THis is a line");
		wiki1.append("Document 1", "Bob", "I know it is.");

		wiki1.append("Document 7", "Bob", "Niceeeee");

		//wiki1.print("Document 1");
		// wiki1.print("Document 2");
		// wiki1.print("Document 3");
		// wiki1.print("Document 4");

		// REPLACEMENTS ARE NOT CREATNG NEW VERSIONS

		wiki1.replace("Document 1","Bob",2,"THIS IS A NEW LINE");
		wiki1.replace("Document 1","Bob",5,"THIS IS A NEW LINE");
		wiki1.replace("Document 1","Bob",11,"THIS IS A NEW LINE");
		wiki1.replace("Document 1","Stacey",1,"THIS IS A NEW LINE");
		wiki1.replace("Document 2","Bob",0,"THIS IS A NEW LINE");
		wiki1.replace("Document 90","Kajal",2,"THIS IS A NEW LINE");

		wiki1.print("Document 1");

		wiki1.history("Document 1");
	}

}
