public class Users extends Entity{

	private String username; // for now
	private List createdDocs;
	private String commands; 
	// -----------------------------------------------------------------------------------------
	// Void contructor
	// -----------------------------------------------------------------------------------------
	public Users(){
			username = null; 
			commands = null;
			createdDocs = new List();
	}

	// -----------------------------------------------------------------------------------------
	// Contructor
	// -----------------------------------------------------------------------------------------
	public Users(String username){
			this.username = username; 
			createdDocs = new List();
			System.out.println("CONFIRMED");
	}

	public void addCommand(String command){
		commands+= command + "\n";
	}

	public void addCreatedDocs(Document doc){
		createdDocs.addLast(doc);
	}

	// -----------------------------------------------------------------------------------------
	// print
	//
	// PURPOSE: prints the user's information/
	// -----------------------------------------------------------------------------------------
	public void print(){
		System.out.print(username + ' ');
	} // print

	// -----------------------------------------------------------------------------------------
	// isDuplicate
	//
	// PURPOSE: to check if input 'name' matches username
	// INPUT: name (String)
	// OUTPUT: returns true if name matches username exactly. Returns false otherwise.
	// -----------------------------------------------------------------------------------------
	public boolean isDuplicate(String name){
		return name.equals(username);
	} // isDuplicate

} // Entity
