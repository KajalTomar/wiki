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
		userList.addFront(new Users(userid));	
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

        Document doc = (Document)documents.retrieve(docTitle);

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
        Document doc = (Document)documents.retrieve(docTitle);

         if(doc != null){
            doc.print();
         }
    } // print

    // JUST FOR TESTING - delete me
    public void printAllUsers(){
        userList.print();
    } // print all users

} // Wiki