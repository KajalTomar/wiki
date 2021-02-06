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
        // check if duplicate
		userList.addLast(new Users(userid));	
    } // user

    // -----------------------------------------------------------------------------------------
	// create
	//
	// PURPOSE: creates a new documents with the specified name, which is created by the 
    // the specified user. Adds the document to the list of all documents.
	// INPUT: document title, userid (String)
	// -----------------------------------------------------------------------------------------
    public void create(String docTitle, String userid) {
        Document doc = new Document(docTitle);
        documents.addLast(doc);
    } // create

    // -----------------------------------------------------------------------------------------
	// append
	//
	// PURPOSE: appends content to a single line at the end of a document.
	// INPUT: document title, userid, content (String)
	// -----------------------------------------------------------------------------------------
    public void append(String docTitle, String userid, String content){

        Document doc = (Document)retrieve(documents, docTitle);

        if(doc != null){
             doc.append(content);
        }
    } // append

    // -----------------------------------------------------------------------------------------
	// print
	//
	// PURPOSE: prints the current contents of the document.
	// INPUT: document title (String)
	// -----------------------------------------------------------------------------------------
    public void print(String docTitle){
        Document doc = (Document)retrieve(documents, docTitle);

         if(doc != null){
            doc.print();
         }
    } // print

    // JUST FOR TESTING - delete me
    public void printAllUsers(){
        userList.print();
    } // print all users

    // JUST FOR TESTING - delete me
    public void printUser(String userid){
        Users u = (Users)retrieve(userList, userid);

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

    // -----------------------------------------------------------------------------------------
	// isDuplicate
	//
	// PURPOSE: checks is an Entity with input key (String) already exists on the list.
	// INPUT: username, document title (String)
	// OUTPUT: Returns true if an Entity with the same key exists on the list, returns false
	// otherwise. Prints the result.
	// -----------------------------------------------------------------------------------------
	public boolean isDuplicate(List list, String name){
		boolean duplicate = false;

		if(searchByName(list, name)!=null){
			duplicate = true;
			System.out.println("DUPLICATE");	
		} 

		return duplicate; 
	} // isDuplicate

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

        if (!found){
			System.out.println("NOT FOUND");
		}

		return foundAt;
	} // searchByNameByName

    public void replace(String docTitle, int lineNum, String replacementLine){
        Document doc = (Document) retrieve(documents, docTitle);
        Line line = null;

        if(doc!= null){
            if (lineNum <= doc.totalVersions() && lineNum > 0){
                line = (Line) getLineN(doc, lineNum).getData();

                if(line != null){
                    line.setLine(replacementLine);
                    System.out.println("SUCCESS");
                }

            } else {
                System.out.println("FAIL");
            }
        }
    }

    private Node getLineN(Document doc, int lineNum){

        Version version = (Version) doc.getAllVerions().getLastItem().getData();
        Node lineAt = null; 
        
        if(version != null){
            lineAt = version.getLineAt(lineNum);
        }

        return lineAt;
    }

    

} // Wiki