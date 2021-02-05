public class Users extends Entity{
	
	private String username; // for now

	// -----------------------------------------------------------------------------------------
	// Void contructor
	// -----------------------------------------------------------------------------------------
	public Users(){
			username = null; 
	}

	// -----------------------------------------------------------------------------------------
	// Contructor
	// -----------------------------------------------------------------------------------------
	public Users(String username){
			this.username = username; 
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
	// print
	//
	// PURPOSE: to check if input 'name' matches username
	// INPUT: name (String)
	// OUTPUT: returns true if name matches username exactly. Returns false otherwise.
	// -----------------------------------------------------------------------------------------
	public boolean isDuplicate(String name){
		return name.equals(username);
	} // isDuplicate

} // Entity
