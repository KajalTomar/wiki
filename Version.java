class Version extends Entity{

	private int versionNumber;
	private List listOfLines;
	private int totalLines; 
	private int time;

	// -----------------------------------------------------------------------------------------
	// void constructor
	// -----------------------------------------------------------------------------------------
	public Version(int time){
		this.time = time;
		versionNumber = 1;
		totalLines = 0;
		listOfLines = new List();
	}

	// -----------------------------------------------------------------------------------------
	// contructor
	//
	//
	// -----------------------------------------------------------------------------------------
	public Version(Version lastVersion, int versionNumber, int time){
		this.time = time;
		this.versionNumber = versionNumber+1;
		totalLines = lastVersion.getTotalLines();
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
	private List versionClone(List listofLines){
		List copiedLines = new List();
		Line current = (Line) listofLines.getFirstItem();

		while(current != null){
			Line temp = new Line(current);
			copiedLines.add(temp);
			current = (Line) listofLines.getNextItem((Entity) current);
		}

		return copiedLines;
	} // copyList

	public int getVersionNumber(){
		return versionNumber;
	}

	public int getTime(){
		return time;
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
		listOfLines.add(line); // add this new line object to the list of lines so far
	} // append

	public Line getLineAt(int lineNumber){
		Line current = (Line) listOfLines.getFirstItem();
		Line foundLine = null;
		boolean found = false;

		while(current != null && !found){
			
			// line == current

			if (current.getLineNumber() == lineNumber){
				foundLine = current; 
				found = true;
			}

			current = (Line) listOfLines.getNextItem((Entity) current);
		}

		return foundLine;
	}

	public void decLineNumbers(Line linesAfter){
		Line curr = (Line) listOfLines.getNextItem((Entity) linesAfter);
		int lineNum; 

		// line == curr
		while(curr!= null){
			lineNum = curr.getLineNumber();
			curr.setLineNum(lineNum-1);

			curr = (Line) listOfLines.getNextItem((Entity) curr);
		}
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
