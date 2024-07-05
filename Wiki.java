//---------------------------------------------------------------------
// REMARKS: manages a list of documents and users. Allows methods
// to create, set, manipulate and print objects of type User.class and
// Document.class (and Version.class and Line.class). 
// keeps track of a global time.
//---------------------------------------------------------------------

public class Wiki{
    
    private List userList; 
	private List documents; // list of users
	private int time; // time is incremented by exactly one at the start of every public method
    
    //-----------------------------------------------------------------------------------------
	// Constructor
	// ----------------------------------------------------------------------------------------
	public Wiki(){
		time = -1;
		userList = new List();
		documents = new List();
	} 

    // -----------------------------------------------------------------------------------------
	// user
	//
	// PURPOSE: creates a new user with the given userid and adds it the list of all User. If the
	// userid is already on the list then a new user is NOT created and an output is displayed.
	// PARAMETER: userid (String)
	// -----------------------------------------------------------------------------------------
	public void user(String userid) {  
		time++; // update time
		
		if (duplicateUser(userid)){
			// if the userid is already taken
			System.out.println("DUPLICATE. user \'"+userid+"\' already exists.");

		} else if (characterLength(userid)<= 80){
			// if the user id is unique and has less than 80 non-whitespace characters

			// create new user with this userid and add it to the list
			userList.add(new User(userid, time));
		} 

    } // user

    // -----------------------------------------------------------------------------------------
	// create
	//
	// PURPOSE: if the the user exists and no document with this title exists then a new document
	// is created with the specified name, which is created by the the specified user. Adds the 
	// document to the list of all documents. If the document is a duplicate then an output is 
	// created. 
	// PARAMETER: document title (String), userid (String)
	// -----------------------------------------------------------------------------------------
    public void create(String docTitle, String userid) {
		time++; // update movie

		User user = handleUser(userid); // check if the user exists and returns it if it does

		if (user != null){
			// the user exists 

			if (duplicateDocument(docTitle)){	
				// a document with the exact same title already exists
				System.out.println("DUPLICATE. Sorry "+userid+", a document titled \""+docTitle+"\" already exists." );

			} else if (characterLength(docTitle) <= 80){
					// no document with this title exists yet
					// and the title has less than or equal to 80 non-whitespace chatacters

					Document doc = new Document(docTitle, user, time); // create a new document
					documents.add(doc); // add the document to Wiki's list of document
			}
		}
		
    } // create

    // -----------------------------------------------------------------------------------------
	// append
	//
	// PURPOSE: if the user and document exist then the User and Document are retrieved. the 
	// Document's append function is called and user (User), content to append (String) and time (int)
	// are sent as parameters.
	// INPUT: document title (String), userid (String), the line to append to document (String)
	// -----------------------------------------------------------------------------------------
    public void append(String docTitle, String userid, String content){
		time++;
		User user = handleUser(userid); // check if the user exists and returns user.
		Document doc = handleDocument(docTitle); // checks if the document exists, prints not found if it doesn't. Returns deocument.

		if(user!=null && doc!=null){
			// the user and document exist

			doc.append(user, content, time); 
		}
		
    } // append

    // -----------------------------------------------------------------------------------------
	// replace
	//
	// PURPOSE: if the user and document exist then the User and Document are retrieved. If the line
	// number is valid, the Document's replaceLine function is called and the line number (int), 
	// replacement line (String), user (User) and time (int) are sent as parameters. If no such 
	// line number exists then a fail message is printed.
	// PARAMETERS: document title (String), userid (String), the line number to replace (int),
	//  the line to replace the existing line with (String).
	// -----------------------------------------------------------------------------------------
	public void replace(String docTitle, String userid, int lineNum, String replacementLine){
		time++; // update time

		User user = handleUser(userid); // check if the user exists and retrieves it
		Document doc = handleDocument(docTitle); // checks if the document exists and retrieves it
		if(user != null && doc!=null){
			if(doc.totalVersions() > 0){
				// user and document exists and there is at least one version of this document
				doc.replaceLine(lineNum, replacementLine, user, time);		

			} else {
				// there are no lines in this document.
				System.out.println("FAIL. There are no lines in \'"+docTitle+"\". Can't replace line "+lineNum+".");
			}

		}

    } // replace

    // -----------------------------------------------------------------------------------------
	// delete
	//
	// PURPOSE: if the user and document exist then the User and Document are retrieved. If the line
	// number is valid, the Document's deleteLine function is called and the line number (int), 
	// user (User) and time (int) are sent as parameters. If no such line number exists then a 
	// fail message is printed.
	// PARAMETERS: document title (String), userid (String), the line number to delete (String)
	// -----------------------------------------------------------------------------------------
	public void delete(String docTitle, String userid, int lineNum){
		time++; // update time

		User user = handleUser(userid); // check if the user exists and retrieves it
		Document doc = handleDocument(docTitle); // checks if the document exists and retrieves it

		if(user != null && doc!=null){
			if(doc.totalVersions() > 0){
			// user and document exists and there is at least one version of this document
			doc.deleteLine(lineNum, user, time);

			} else {
				// there are no lines in this document.
				System.out.println("FAIL. There are no lines in \'"+docTitle+"\" to delete.");
			}		
		} 

    } // delete

    // -----------------------------------------------------------------------------------------
	// restore
	//
	// PURPOSE: if the user and document exist then the User and Document are retrieved. If the 
	// restoreTime (int) is valid, the Document's restoreToTime function is called and the
	// the time to restore to (int), time of command (int) and the user (User) are sent as 
	// parameters. If the document didn't exist at restore time then a fail message is printed.
	// PARAMETERS: userid (String), the document's title (String), time to restore to (int).
    // -----------------------------------------------------------------------------------------
	public void restore(String userid, String docTitle, int restoreTime){
		time++; // update time

		User user = handleUser(userid); // check if the user exists and retrieve it
		Document doc = handleDocument(docTitle); // checks if the document exists and retrieve it

		if(user != null && doc!=null){
			// user and document exists 
			if(restoreTime >= 0 && restoreTime <= time-1 && doc.totalVersions() >= 0){
				// restore time is non-negative and less than the most recent time 
				// there is atleast one line in the document
				doc.restoreToTime(restoreTime, time, user);	
			} else {
				System.out.println("NOT FOUND. This document did not exist or contain content at time "+restoreTime+".");
			}		
		} 

	} // restore

    // -----------------------------------------------------------------------------------------
	// print
	//
	// PURPOSE: prints the current contents of the document.
	// PARAMETERS: document title (String)
	// -----------------------------------------------------------------------------------------
    public void print(String docTitle){
		time++; // update time

        Document doc = retrieveDocument(docTitle); // checks if the document exists and retrieve it
         if(doc != null){
			// the document exists. print it!
            doc.print();

         } else {
			 System.out.println("NOT FOUND. No document with the title \""+docTitle+"\" exists :(");
		 }

    } // print

    // -----------------------------------------------------------------------------------------
	// history
	//
	// PURPOSE: if the document exists then it is retrieved from the list and the document's 
	// history method is called. 
	// PARAMETERS: the document's title (String)
    // -----------------------------------------------------------------------------------------	
	public void history(String docTitle){
		time++; // time
		Document doc = retrieveDocument(docTitle); // // check if the Document exists and retrieve it

        if(doc != null){
			// the document exists! Print out info and history
			System.out.println("*************************************************");
			System.out.println("HISTORY FOR "+docTitle.toUpperCase());
			System.out.println("created by: "+doc.createdBy().getUserId());

			System.out.println("t(= time): user's edit");
            doc.history();
			System.out.println();
        } else {
			System.out.println("NOT FOUND. No document with the title \""+docTitle+"\" exists :(");
		}

	} // history 

    // -----------------------------------------------------------------------------------------
	// userreport
	//
	// PURPOSE: if the user exists then User's print method to print the user's information. 
	// if the user doesn't exist a fail method is printed. 
	// PARAMETERS: userid (String).
    // -----------------------------------------------------------------------------------------
    public void userReport(String userid){
		time++; // update time

        User user = retrieveUser(userid); // check if the user exists and retrieve it

        if(user==null){
			System.out.println("NOT FOUND. User \""+userid+"\" does not exist.");
        } else {
			// the user exists. print it!
			user.print();
		}

		System.out.println();

    } // print all User

    // -----------------------------------------------------------------------------------------
	// quit
	//
	// PURPOSE: print out 'bye'
    // -----------------------------------------------------------------------------------------
	public void quit(){

		System.out.print("\n********\nBYE\n");

	} // quit 

    // -----------------------------------------------------------------------------------------
	// PRIVATE METHODS
    // -----------------------------------------------------------------------------------------

    // -----------------------------------------------------------------------------------------
	// retrieveDocument
	//
	// PURPOSE: looks through the list of Document to find a document with the given title. 
	// PARAMETERS: the document title to look for (String)
	// RETURNS: the Document that has the given title (Document)
    // -----------------------------------------------------------------------------------------	
	private Document retrieveDocument(String docTitle){
		Document foundDoc = null; // will hold the doument if it is found on the list
		boolean found = false; 
		Entity result = documents.getFirstItem(); // the first item on the list
		Document current = null; // the current item to compare with

		// confirm that the result is a Document to safely cast it 
		if(result instanceof Document){
			current = (Document) result;
			result = null;
		}

		// this loop iterates through the document list until the end or until 
		// a document with the matching title is found.
		while(current != null && !found){

			if(current.isDuplicate(docTitle)){
				// the current document's title matched the title we are looking for!
				foundDoc = current; 
				found = true;
			}
			
			// get the next item on the document list
			result = documents.getNextItem((Entity) current);

			// safe casting
			if(result instanceof Document){
				current = (Document) result;
			} else {
				current = null;
			}

			result = null;
		}

		return foundDoc;

	} // retrieveDocument

    // -----------------------------------------------------------------------------------------
	// retrieveUser
	//
	// PURPOSE: looks through the list of Users to find a user with the given userid. 
	// PARAMETERS: the userid to look for (String)
	// RETURNS: the User with the given userid (User)
    // -----------------------------------------------------------------------------------------
	private User retrieveUser(String userid){
		User foundUser = null; // will hold the user we are looking for with the matching userid
		boolean found = false;
		User current = null; // the current user we are comparing against

		Entity result = userList.getFirstItem(); // the first item on the list of users

		// safely cast
		if(result instanceof User){
			current = (User) result;
			result = null;
		}

		// this loop iterates through the user list until the end or until 
		// a user with the matching userid is found.
		while(current != null && !found){

			if(current.isDuplicate(userid)){
				// the user id of the current user matches the user id we are looking for!
				foundUser = current;
				found = true;
			}

			// get the next item!
			result = userList.getNextItem((Entity) current);

			// safe casting
			if (result instanceof User && result != null){
				current = (User) result;
			} else {
				current = null;
			}
			
			result = null;
		}
		
		return foundUser;

	} // retrieveUser

    // -----------------------------------------------------------------------------------------
	// duplicateUser
	//
	// PURPOSE: looks through the list of Users to find a user with the given userid. 
	// PARAMETERS: the userid to look for (String)
	// RETURNS: true if a user with the given userid is on the list and false if otherwise.
    // -----------------------------------------------------------------------------------------
	private boolean duplicateUser(String userid){
		boolean duplicate = false;

		if(retrieveUser(userid)!=null){
			// the user already exists on the list

			duplicate = true;
		} 

		return duplicate;  

	} // duplicateUser

    // -----------------------------------------------------------------------------------------
	// duplicateDocument
	//
	// PURPOSE: looks through the list of Documets to find a Document with the given userid. 
	// PARAMETERS: the title to look for (String)
	// RETURNS: true if a Document with the given title is on the list and false if otherwise.
    // -----------------------------------------------------------------------------------------
	private boolean duplicateDocument(String title){
		boolean duplicate = false;

		if(retrieveDocument(title)!=null){
			// the document already exists on the list 
			duplicate = true;
		} 

		return duplicate; 

	} //duplicateDocument

    // -----------------------------------------------------------------------------------------
	// handleUser
	//
	// PURPOSE: check if the given userid is a duplicate. If it is UNIQUE then print a 'not found'
	// message. Return the User if it exists.
	// PARAMETERS: userid (String)
	// RETURNS: User with the given username (Userid)
    // -----------------------------------------------------------------------------------------
	private User handleUser(String userid){
		User user = null;

		if(!duplicateUser(userid)){ 
			// user does not exist
			System.out.println("NOT FOUND. User \""+userid+"\" does not exist.");
		} else{
			// the user exists 
			user = retrieveUser(userid);
		}
	 
	 	return user;

	} // handeUser

    // -----------------------------------------------------------------------------------------
	// handleDocument
	//
	// PURPOSE: check if the given document title is a duplicate. If it is UNIQUE then print a 'not found'
	// message. Return the Document if it exists.
	// PARAMETERS: title to look for (String)
	// RETURNS: Document with the given title (Userid)
    // -----------------------------------------------------------------------------------------
	private Document handleDocument(String title){
		Document doc = null;

		if(!duplicateDocument(title)){
			// the document does not exist
			System.out.println("NOT FOUND. No document with the title \""+title+"\" exists :(");
		} else {
			doc = retrieveDocument(title);
		}

		return doc;

	} // handleDocument

    // -----------------------------------------------------------------------------------------
	// characterLength
	//
	// PURPOSE: Return the amount of characters in a string (without whitespace)
	// PARAMETERS: line (String)
	// RETURNS: the number of lines (Userid)
    // -----------------------------------------------------------------------------------------
	private int characterLength(String line){
		String tempLine = line; // so we don't change the original line
		tempLine.replaceAll("\\s+",""); // take out any whitespace
		return tempLine.length(); // return the length

	} // characterLength

} // Wiki
