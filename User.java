public class User extends Entity{

	private String username; 
	private String commands; 

	// -----------------------------------------------------------------------------------------
	// Void contructor
	// -----------------------------------------------------------------------------------------
	public User(){
			username = null; 
			commands = "";
	}

	// -----------------------------------------------------------------------------------------
	// Contructor
	// -----------------------------------------------------------------------------------------
	public User(String username, int time){
			this.username = username; 
			commands="";
			System.out.println("CONFIRMED. User \'"+username+"\' has been created.");
			addCommand("t"+time+": CREATE "+username);
	}

	// -----------------------------------------------------------------------------------------
	// addCommand
	//
	// PURPOSE: adds the command to string of commands
	// INPUT: command (String)
	// -----------------------------------------------------------------------------------------
	public void addCommand(String command){
		this.commands+= command;
		this.commands+="\n";	
	} // addCommand

	// -----------------------------------------------------------------------------------------
	// getUserId
	//
	// PURPOSE: returs the userid
	// OUTPUT: username (String)
	// -----------------------------------------------------------------------------------------
	public String getUserId(){
		return username;
	} // getUserId

	// -----------------------------------------------------------------------------------------
	// print
	//
	// PURPOSE: prints the user's information/
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
	// INPUT: name (String)
	// OUTPUT: returns true if name matches username exactly. Returns false otherwise.
	// -----------------------------------------------------------------------------------------
	public boolean isDuplicate(String name){
		return name.equals(username);
	} // isDuplicate

} // Entity
