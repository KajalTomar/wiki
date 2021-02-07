public class Users extends Entity{

	private String username; // for now
	private List createdDocs;
	private String commands; 
	private int time;
	// -----------------------------------------------------------------------------------------
	// Void contructor
	// -----------------------------------------------------------------------------------------
	public Users(){
			username = null; 
			commands = "";
			time = -1;
			createdDocs = new List();
	}

	// -----------------------------------------------------------------------------------------
	// Contructor
	// -----------------------------------------------------------------------------------------
	public Users(String username, int time){
			this.username = username; 
			this.time = time;
			commands="";
			createdDocs = new List();
			System.out.println("CONFIRMED");
			addCommand("t"+time+": CREATE "+username);
	}

	public void addCommand(String command){
		this.commands+= command;
		this.commands+="\n";	
	}

	public void addCreatedDocs(Document doc){
		createdDocs.addLast(doc);
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
		System.out.println("USER REPORT FOR "+username.toUpperCase());
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
