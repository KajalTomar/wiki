class Version extends Entity{

	private int versionNumber;
	private List listOfLines;
	private int totalLines; 
	
	// -----------------------------------------------------------------------------------------
	// void constructor
	// -----------------------------------------------------------------------------------------
	public Version(){
		versionNumber = 1;
		totalLines = 0;
		listOfLines = new List();
	}

	// -----------------------------------------------------------------------------------------
	// contructor
	// -----------------------------------------------------------------------------------------
	public Version(Version lastVersion, int versionNumber){
		this.versionNumber = versionNumber+1;
		totalLines = lastVersion.getTotalLines();
		List newList = new List(); // create instance of List
		// if a previous version of the document exists
		if(lastVersion!=null){
			// copy all the lines of the previous version to the current version
			listOfLines = versionClone(lastVersion.getLines());
		}

	} // Version

	// -----------------------------------------------------------------------------------------
	// versionClone
	//
	// PURPOSE: creates a new list that is a clone of the this current list.
	// OUTPUT: Returns a clones List 
	// -----------------------------------------------------------------------------------------
	public List versionClone(List listofLines){
		List copiedLines = new List();
		Node curr = listofLines.getFirstItem();

		while(curr != null){
			Line temp = new Line((Line) curr.getData());
			copiedLines.addLast(temp);
			curr = curr.getNext();
		}

		return copiedLines;
	} // copyList

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

		Line line = new Line(addLine, totalLines-1); // create a new line object with input 
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
			//System.out.println("VERSION: "+versionNumber);
			listOfLines.print();	
	} // print

} // Version
