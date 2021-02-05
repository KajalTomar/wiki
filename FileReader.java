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

		wiki1.printAllUsers();

		wiki1.create("Document 1", "Bob");
		wiki1.create("Document 2", "Bob");
		wiki1.create("Document 3", "Bob");
		wiki1.create("Document 4", "Bob");

		wiki1.append("Document 1", "Bob", "Yo,what's up?");
		wiki1.append("Document 1", "Bob", "Nothing much");
		wiki1.append("Document 1", "Bob", "Niceeeee");

		wiki1.append("Document 2", "Bob", "Blah blah blah?");
		wiki1.append("Document 2", "Bob", "BLah!!!!");

		wiki1.append("Document 7", "Bob", "Niceeeee");

		wiki1.print("Document 1");
		wiki1.print("Document 2");
		wiki1.print("Document 3");
		wiki1.print("Document 4");
	}

}
