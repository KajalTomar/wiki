//---------------------------------------------------------------------
// REMARKS: the purpose of this class is to hold information about a 
// user. It also provides methods to set, access, and print this 
// information.
//---------------------------------------------------------------------

public class User extends Entity{

	private String username; 
	private String commands; // will hold all the commands a user makes as a string

	// -----------------------------------------------------------------------------------------
	// null contructor
	// -----------------------------------------------------------------------------------------
	public User(){
			username = null; 
			commands = "";

	} // User

	// -----------------------------------------------------------------------------------------
	// Contructor
	// -----------------------------------------------------------------------------------------
	public User(String username, int time){
			this.username = username; 
			commands="";

			System.out.println("CONFIRMED. User \'"+username+"\' has been created.");
			addCommand("t"+time+": CREATE "+username);

	} // User

	// -----------------------------------------------------------------------------------------
	// addCommand
	//
	// PURPOSE: adds the command to string of commands
	// PARAMETERS: command (String)
	// -----------------------------------------------------------------------------------------
	public void addCommand(String command){
		this.commands+= command; // add to the end of commands
		this.commands+="\n";	

	} // addCommand

	// -----------------------------------------------------------------------------------------
	// getUserId
	//
	// PURPOSE: returns the userid
	// RETURNS: username (String)
	// -----------------------------------------------------------------------------------------
	public String getUserId(){
		return username;

	} // getUserId

	// -----------------------------------------------------------------------------------------
	// print
	//
	// PURPOSE: prints the user's information
	// -----------------------------------------------------------------------------------------
	public void print(){
		System.out.println("*************************************************");	
		System.out.println("USER REPORT FOR "+username);
		System.out.println("time: command. (document version)");
		System.out.println("----------");
		System.out.print(commands);

	} // print

	// -----------------------------------------------------------------------------------------
	// isDuplicate
	//
	// PURPOSE: to check if input 'name' matches username
	// PARAMETERS: name (String) 
	// RETURNS: true if 'name' matches username exactly. Returns false otherwise. (boolean)
	// -----------------------------------------------------------------------------------------
	public boolean isDuplicate(String name){
		return name.equals(username);
	} // isDuplicate

} // Entity
