class Version extends Entity{

	private int versionNumber;
	private List listOfLines;
	private int totalLines; 

	// -----------------------------------------------------------------------------------------
	// void constructor
	// -----------------------------------------------------------------------------------------
	public Version(){
		versionNumber = 0;
		totalLines = 0;
		listOfLines = new List();
	}

	// -----------------------------------------------------------------------------------------
	// contructor
	// -----------------------------------------------------------------------------------------
	public Version(Version lastVersion){
		totalLines = lastVersion.getTotalLines();
		listOfLines = new List(); // create instance of List
		// if a previous version of the document exists
		if(lastVersion!=null){

			// copy all the lines of the previous version to the current version
			listOfLines = lastVersion.getLines().copyList();
		}

	} // Version

	public int getVersionNumber(){
		return versionNumber;
	}

	// -----------------------------------------------------------------------------------------
	// getTotalLines
	//
	// PURPOSE: returns the current total number of lines
	// -----------------------------------------------------------------------------------------
	public int getTotalLines(){
		return totalLines;
	} // getTotalLines

	// -----------------------------------------------------------------------------------------
	// getLines
	//
	// PURPOSE: returns the current list of lines
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
		totalLines++;

		Line line = new Line(addLine, totalLines); // create a new line object with input 
		listOfLines.addLast(line); // add this new line object to the list of lines so far
	} // append

	public Node getLineAt(int lineNumber){
		Node curr = listOfLines.getFirstItem();
		Node foundLine = null;
		boolean found = false;

		while(curr != null && !false){
			
			Line line = (Line)curr.getData();

			if (line.getLineNumber() == lineNumber){
				foundLine = curr; 
				found = true;
			}

			curr = curr.getNext();
		}

		if (!found){
			System.out.println("NOT FOUND");
		}

		return foundLine;
	}

	// -----------------------------------------------------------------------------------------
	// print
	//
	// PURPOSE: print this version of the document.
	// -----------------------------------------------------------------------------------------
	public void print(){
			listOfLines.print();	
	} // print

} // Version
