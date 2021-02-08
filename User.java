public class User extends Entity{

	private String username; // for now
	private List createdDocs; // may not need this
	private String commands; 
	private int time;
	// -----------------------------------------------------------------------------------------
	// Void contructor
	// -----------------------------------------------------------------------------------------
	public User(){
			username = null; 
			commands = "";
			time = -1;
			createdDocs = new List();
	}

	// -----------------------------------------------------------------------------------------
	// Contructor
	// -----------------------------------------------------------------------------------------
	public User(String username, int time){
			this.username = username; 
			this.time = time;
			commands="";
			createdDocs = new List();
			System.out.println("CONFIRMED. User \'"+username+"\' has been created.");
			addCommand("t"+time+": CREATE "+username);
	}

	public void addCommand(String command){
		this.commands+= command;
		this.commands+="\n";	
	}

	// may not need this
	public void addCreatedDocs(Document doc){
		createdDocs.add(doc);
	}

	public String getUserId(){
		return username;
	}

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
