import java.lang.String;

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
	// PURPOSE: creates a new user and adds it the list of all users.
	// INPUT: userid (String)
	// -----------------------------------------------------------------------------------------
	public void user(String userid) {  
		time++;

		if (duplicateUser(userid)){
			// if the userid is already taken
			System.out.println("DUPLICATE");
		} else if (characterLength(userid)<= 80){
			// if the user id is unique and has less than 80 non-whitespace characters
			userList.addLast(new Users(userid, time));
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
		Users user = handleUser(userid); // check if the user exists and returns it if it does

		if (user != null){
			// the user exists 

			if (duplicateDocument(docTitle)){	
				// a document with the exact same title already exists
				System.out.println("DUPLICATE");

			} else if (characterLength(docTitle) <= 80){
					// no document with this title exists yet
					// and the title has less than or equal to 80 non-whitespace chatacters

					Document doc = new Document(docTitle, user, time); // create a new document
					doc.addEdit("t"+time+" v0: "+userid+" created new document \"" + docTitle +"\""); // add to the history of the document
					documents.addLast(doc); // add the document to Wiki's list of document

					// update user
					user.addCreatedDocs(doc);
					user.addCommand("t" +time+ " CREATE \"" + docTitle +"\". (v0)");
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
		Users user = handleUser(userid); // check if the user exists and returns user.
		Document doc = handleDocument(docTitle); // checks if the document exists, prints not found if it doesn't. Returns deocument.

		if(user!=null && doc!=null){
			// the user and document exist

			doc.append(content); 
			doc.addEdit("t"+time+" v"+doc.totalVersions()+": "+userid+" appended \""+content+"\" "); // add to the history of the document

			// update user
			user.addCommand("t"+time+": APPEND \""+docTitle+"\" \""+content+"\". (v"+ doc.totalVersions()+")");
			System.out.println("SUCCESS");
		}
		
    } // append

	public void replace(String docTitle, String userid, int lineNum, String replacementLine){
		time++;
		Users user = handleUser(userid); // check if the user exists and adds the command to user history if it does
		Document doc = handleDocument(docTitle); // checks if the document exists, prints not found if it doesn't 
        Line line = null;
		String oldLine;

		if(user != null && doc!=null && doc.totalVersions() > 0){
			// user and document exists

			doc.copyVersion(); // this creates a new version that is identical to the current version
			
			doc = retrieveDocument(docTitle);// retrieve it again because it's been updated
			
			if (lineNum >= 0 && lineNum <= doc.getLatestVersion().getTotalLines()){
				// user and document exists and lineNumber is valid
				line = retrieveLine(doc, lineNum);
				oldLine = line.getLine();
				if(line != null){
					line.setLine(replacementLine);
					doc.addEdit("t"+time+" v"+doc.totalVersions()+": "+userid+" replaced line "+lineNum); // add to the history of the document
					doc.addEdit("\t\""+oldLine+"\" ---> \""+replacementLine+"\"");

					user.addCommand("t"+time+": REPLACE \""+docTitle+"\" "+lineNum+" \""+replacementLine+"\". (v"+ doc.totalVersions()+")");
					System.out.println("SUCCESS");
				} else {
					System.out.println("FAIL");
				}

			} else {
				// there is no line number lineNum
				System.out.println("FAIL");
			}
			
		}

    } // replace

	private void delete(String docTitle, String userid, int lineNum){
		time++;
		Users user = handleUser(userid); // check if the user exists and adds the command to user history if it does
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
		time++;
        Document doc = retrieveDocument(docTitle);

         if(doc != null){
            doc.print();
         }
    } // print

	public void history(String docTitle){
		time++;
		Document doc = retrieveDocument(docTitle);

        if(doc != null){
			System.out.println("*************************************************");
			System.out.println("HISTORY FOR "+docTitle.toUpperCase());
			System.out.println("created by: "+doc.createdBy().getUserId());

			System.out.println("time version: user's edit");
            doc.history();
			System.out.println();
        }
	}

    // JUST FOR TESTING - delete me
    public void printAllUsers(){
        userList.print();
    } // print all users

	   // JUST FOR TESTING - delete me
    public void printAllDocs(){
        documents.print();
    } // print all users


    public void userReport(String userid){
        Users user = retrieveUser(userid);

        if(user==null){
			System.out.println("NOT FOUND");
        } else {
			user.print();
		}

		System.out.println();

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
		} 

		return data;

	} // retrieve

	private Document retrieveDocument(String docTitle){
		return (Document) retrieve(documents, docTitle);
	}

	private Users retrieveUser(String userid){
		return (Users) retrieve(userList, userid);
	}

	private Line retrieveLine(Document doc, int lineNum){
		// get the current version of the document
		Version version = null;
        Node lineAt = null; 
		Line line = null;

		if(doc.totalVersions() != 0){
			// at least on version exists 
			version = (Version) doc.getAllVerions().getLastItem().getData();
            lineAt = version.getLineAt(lineNum); // does this line exist in the list?

			if(lineAt != null){
				// the line exists in the list of lines!

				line = (Line) lineAt.getData(); // retrieve the content of this line
			}
        } 

		return line;
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

	private Users handleUser(String userid){
		Users user = null;

		if(!duplicateUser(userid)){ 
			// user does not exist
			System.out.println("NOT FOUND");
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
			System.out.println("NOT FOUND");
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

		while(curr != null && !false){
			
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