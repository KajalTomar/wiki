import java.lang.String;

public class Wiki{
    
    private List userList;
	private List documents; 
    
    //-----------------------------------------------------------------------------------------
	// Constructor
	// ----------------------------------------------------------------------------------------
	public Wiki(){
		userList = new List();
		documents = new List();
	} 

    // -----------------------------------------------------------------------------------------
	// user
	//
	// PURPOSE: creates a new user and adds it the list of all users.
	// INPUT: userid (String)
	// -----------------------------------------------------------------------------------------
	public void user(String userid) {  
        
		if (duplicateUser(userid)){
			// if the userid is already taken
			System.out.println("DUPLICATE");
		} else if (characterLength(userid)<= 80){
			// if the user id is unique and has less than 80 non-whitespace characters
			userList.addLast(new Users(userid));
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

		if(!duplicateUser(userid)){ 
			// user does not exist
			System.out.println("NOT FOUND");
		} else{
			// the user exists 
			Users user = retrieveUser(userid);
			user.addCommand("create"); // update user's command history 

			if (duplicateDocument(docTitle)){	
				// a document with the exact same title already exists
				System.out.println("DUPLICATE");

			} else if (characterLength(docTitle) <= 80){
					// no document with this title exists yet
					// and the title has less than or equal to 80 non-whitespace chatacters

					Document doc = new Document(docTitle, user);
					doc.addEdit("created by "+userid+". (version 0)");
					user.addCreatedDocs(doc);
					documents.addLast(doc);
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
		if(!duplicateUser(userid)){
			// the user does not exist
			System.out.println("NOT FOUND");
		} else {
			// the user exists
			Users user = retrieveUser(userid);
			user.addCommand("append");

			if (!duplicateDocument(docTitle)) {
				// the document does not exists 
				System.out.println("NOT FOUND");

			} else {
				// the user and the document exist

				Document doc = retrieveDocument(docTitle);
				doc.append(content);
				doc.addEdit("append by "+userid+". (version "+ doc.totalVersions()+ ")");
				System.out.println("SUCCESS");
			}
		}
    } // append

	public void replace(String docTitle, String userid, int lineNum, String replacementLine){
        Line line = null;

		if(!duplicateUser(userid)){
			// the user does not exist
			System.out.println("NOT FOUND");
		} else {
			// the user exists
			Users user = retrieveUser(userid);
			user.addCommand("replace");

			if (!duplicateDocument(docTitle)) {
				// the document does not exists 
				System.out.println("NOT FOUND");

			} else {
			// user and document exists
			
				Document doc = retrieveDocument(docTitle);
				
				if (lineNum <= doc.totalVersions() && lineNum > 0){
					// user and document exists and lineNumber is valid
					line = (Line) getLineN(doc, lineNum).getData();

					if(line != null){
						line.setLine(replacementLine);
							doc.addEdit("Replaced line "+lineNum+" by "+userid +". (version " + doc.totalVersions() + ")");
						System.out.println("SUCCESS");
					} else {
						System.out.println("FAIL");
					}

				} else {
					// there is no line number lineNum
					System.out.println("FAIL");
				}
			}
		}

    } // replace

	private void delete(String docTitle, String userid, int lineNum){
        Document doc = retrieveDocument(docTitle);
        Line line = null;
	}

    // -----------------------------------------------------------------------------------------
	// print
	//
	// PURPOSE: prints the current contents of the document.
	// INPUT: document title (String)
	// -----------------------------------------------------------------------------------------
    public void print(String docTitle){
        Document doc = retrieveDocument(docTitle);

         if(doc != null){
            doc.print();
         }
    } // print

	public void history(String docTitle){
		 Document doc = retrieveDocument(docTitle);

         if(doc != null){
            doc.history();
         }
	}

    // JUST FOR TESTING - delete me
    public void printAllUsers(){
        userList.print();
    } // print all users

    // JUST FOR TESTING - delete me
    public void printUser(String userid){
        Users u = retrieveUser(userid);

        if(u!=null){
            u.print();
            System.out.println();
        }

    } // print all users

	

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
		} else {
			System.out.println("NOT FOUND");
		}

		return data;

	} // retrieve

	private Document retrieveDocument(String docTitle){
		return (Document) retrieve(documents, docTitle);
	}

	private Users retrieveUser(String userid){
		return (Users) retrieve(userList, userid);
	}

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

		while(curr != null && !false){
			
			if (curr.isDuplicate(name)){
				foundAt = curr; 
				found = true;
			}

			curr = curr.getNext();
		}

		return foundAt;
	} // searchByNameByName

    private Node getLineN(Document doc, int lineNum){

        Version version = (Version) doc.getAllVerions().getLastItem().getData();
        Node lineAt = null; 
        
        if(version != null){
            lineAt = version.getLineAt(lineNum);
        }

        return lineAt;
    }    

	private int characterLength(String line){
		String tempLine = line; 
		tempLine.replaceAll("\\s+","");
		return tempLine.length();
	}

} // Wiki