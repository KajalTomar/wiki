class Version extends Entity{

	private int versionNumber;
	private List listOfLines;
	private int totalLines; 

	// -----------------------------------------------------------------------------------------
	// void constructor
	// -----------------------------------------------------------------------------------------
	public Version(){
		versionNumber = 0;
		listOfLines = new List();
	}

	// -----------------------------------------------------------------------------------------
	// contructor
	// -----------------------------------------------------------------------------------------
	public Version(Version lastVersion){

		listOfLines = new List(); // create instance of List

		// if a previous version of the document exists
		if(lastVersion!=null){

			// copy all the lines of the previous version to the current version
			listOfLines = lastVersion.getLines().copyList();
		}

	} 

	// -----------------------------------------------------------------------------------------
	// getLines
	//
	// PURPOSE: returns the current list of lines
	// OUTPUT: returns the current list of lines 
	// -----------------------------------------------------------------------------------------
	public List getLines(){
		return listOfLines;
	} // getLines

	// -----------------------------------------------------------------------------------------
	// append
	//
	// PURPOSE: appends content of a single line at the end of a this version of the document.
	// INPUT: line to append (String)
	// -----------------------------------------------------------------------------------------
	public void append(String addLine){
		Line line = new Line(addLine); // create a new line object with input 
		listOfLines.addLast(line); // add this new line object to the list of lines so far
	} // append

	// -----------------------------------------------------------------------------------------
	// isDuplicate
	//
	// PURPOSE: checks if the input line is the last line of this version. Could be used to 
	// check if a duplicate line is being inserted in a document in a row. 
	// INPUT: line to compare with (String)
	// OUTPUT: returns true if line matches the last line of the current version.
	// -----------------------------------------------------------------------------------------
	public boolean isDuplicate(String line){	
		return listOfLines.isDuplicate(line);
	} // isDuplicate

	// -----------------------------------------------------------------------------------------
	// print
	//
	// PURPOSE: print this version of the document.
	// -----------------------------------------------------------------------------------------
	public void print(){
			listOfLines.print();	
	} // print

} // Version
