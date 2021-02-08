//import java.lang.String;

public class Wiki{
    
    private List userList;
	private List documents; 
	private int time; 
    
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
	// PURPOSE: creates a new user and adds it the list of all User.
	// INPUT: userid (String)
	// -----------------------------------------------------------------------------------------
	public void user(String userid) {  
		time++;

		if (duplicateUser(userid)){
			// if the userid is already taken
			System.out.println("DUPLICATE. user \'"+userid+"\' already exists.");
		} else if (characterLength(userid)<= 80){
			// if the user id is unique and has less than 80 non-whitespace characters
			userList.add(new User(userid, time));
		} 

    } // user

    // -----------------------------------------------------------------------------------------
	// create
	//
	// PURPOSE: creates a new documents with the specified name, which is created by the 
    // the specified user. Adds the document to the list of all documents.
	// INPUT: document title, userid (String)
	// -----------------------------------------------------------------------------------------
    public void create(String docTitle, String userid) {
		time++;
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
	// PURPOSE: appends content to a single line at the end of a document.
	// INPUT: document title, userid, content (String)
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

	public void replace(String docTitle, String userid, int lineNum, String replacementLine){
		time++;
		User user = handleUser(userid); // check if the user exists and adds the command to user history if it does
		Document doc = handleDocument(docTitle); // checks if the document exists, prints not found if it doesn't 

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

	public void delete(String docTitle, String userid, int lineNum){
		time++;
		User user = handleUser(userid); // check if the user exists and adds the command to user history if it does
		Document doc = handleDocument(docTitle); // checks if the document exists, prints not found if it doesn't

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

	public void restore(String userid, String docTitle, int restoreTime){
		time++;

		User user = handleUser(userid); // check if the user exists and adds the command to user history if it does
		Document doc = handleDocument(docTitle); // checks if the document exists, prints not found if it doesn't

		if(user != null && doc!=null){
			// user and document exists and there is at least one version of this document
			if(restoreTime >= 0 && restoreTime <= time-1 && doc.totalVersions() >= 0){
				doc.restoreToTime(restoreTime, time, user);	
			} else {
				System.out.println("NOT FOUND. This document did not exist or contain content at time "+restoreTime+".");
			}		
		} 

	}

    // -----------------------------------------------------------------------------------------
	// print
	//
	// PURPOSE: prints the current contents of the document.
	// INPUT: document title (String)
	// -----------------------------------------------------------------------------------------
    public void print(String docTitle){
		time++;
        Document doc = retrieveDocument(docTitle);

         if(doc != null){
            doc.print();
         } else {
			 System.out.println("NOT FOUND. No document with the title \""+docTitle+"\" exists :(");
		 }
    } // print

	public void history(String docTitle){
		time++;
		Document doc = retrieveDocument(docTitle);

        if(doc != null){
			System.out.println("*************************************************");
			System.out.println("HISTORY FOR "+docTitle.toUpperCase());
			System.out.println("created by: "+doc.createdBy().getUserId());

			System.out.println("t(= time): user's edit");
            doc.history();
			System.out.println();
        } else {
			System.out.println("NOT FOUND. No document with the title \""+docTitle+"\" exists :(");
		}
	}

    // JUST FOR TESTING - delete me
    public void printAllUser(){
        userList.print();
    } // print all User

	   // JUST FOR TESTING - delete me
    public void printAllDocs(){
        documents.print();
    } // print all User


    public void userReport(String userid){
		time++;
        User user = retrieveUser(userid);

        if(user==null){
			System.out.println("NOT FOUND. User \""+userid+"\" does not exist.");
        } else {
			user.print();
		}

		System.out.println();

    } // print all User

	public void quit(){

		System.out.print("\n********\nBYE\n");
	}

	

	// -----------------------------------------------------------------------------------------
	// retrieve
	//
	// PURPOSE: Returns an entity type (user, document) based on a key (document name, username)
	// INPUT: username, document title (String)
	// OUTPUT: On success it returns an Entity. On failure it returns null and prints 'not found'.
	// -----------------------------------------------------------------------------------------
	private Entity retrieve(List list, String name){
		Node dataAtNode = searchByName(list, name); 
		Entity data = null;

		if(dataAtNode != null){
			data = dataAtNode.getData();
		} 

		return data;

	} // retrieve

	private Document retrieveDocument(String docTitle){
		return (Document) retrieve(documents, docTitle);
	}

	private User retrieveUser(String userid){
		return (User) retrieve(userList, userid);
	}

	// private Node getLineN(Document doc, int lineNum){

    //     Version version = (Version) doc.getAllVerions().getLastItem().getData();
    //     Node lineAt = null; 
        
    //     if(version != null){
    //         lineAt = version.getLineAt(lineNum);
    //     }

    //     return lineAt;
    // }  

    // -----------------------------------------------------------------------------------------
	// isDuplicate
	//
	// PURPOSE: checks is an Entity with input key (String) already exists on the list.
	// INPUT: username, document title (String)
	// OUTPUT: Returns true if an Entity with the same key exists on the list, returns false
	// otherwise. Prints the result.
	// -----------------------------------------------------------------------------------------
	private boolean isDuplicate(List list, String name){
		boolean duplicate = false;

		if(searchByName(list, name)!=null){
			duplicate = true;
		} 

		return duplicate; 
	} // isDuplicate

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
	}


	private Document handleDocument(String title){
		Document doc = null;

		if(!duplicateDocument(title)){
			// the document does not exist
			System.out.println("NOT FOUND. No document with the title \""+title+"\" exists :(");
		} else {
			doc = retrieveDocument(title);
		}

		return doc;
	}

	private boolean duplicateUser(String userid){
		return isDuplicate(userList, userid);	 
	}

	private boolean duplicateDocument(String title){
		return isDuplicate(documents, title);
	}

    // -----------------------------------------------------------------------------------------
	// searchByName
	//
	// PURPOSE: searchByNamees for an entity type (user, document) based on a key (document name, 
	// username)
	// INPUT: username, document title (String)
	// OUTPUT: On success it returns the node corresponding to the Entity. On failure it returns 
	// null.
	// -----------------------------------------------------------------------------------------
	private Node searchByName(List list, String name){
		Node curr = list.getFirstItem(); 
		Node foundAt = null;
		boolean found = false;

		while(curr != null && !found){
			
			if (curr.isDuplicate(name)){
				foundAt = curr; 
				found = true;
			}

			curr = curr.getNext();
		}

		return foundAt;
	} // searchByNameByName
  

	private int characterLength(String line){
		String tempLine = line; 
		tempLine.replaceAll("\\s+","");
		return tempLine.length();
	}

} // Wiki