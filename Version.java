//---------------------------------------------------------------------
// CLASS: Version.java
//
// Author: Kajal Tomar, 7793306
//
// REMARKS: Holds a list of lines and information of the list. 
// It also provides methods to set, access, manipulate and print this 
// the list of lines and the related information.
// 
//---------------------------------------------------------------------

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
		versionNumber = 1; // starts at version one
		totalLines = 0;
		listOfLines = new List();
	}

	// -----------------------------------------------------------------------------------------
	// contructor
	//
	// PURPOSE: creates a new Version object by cloning the Version recieved as a parameter.  
	// PARAMETER: recieves a Version to clone (Version), the most recent version number (which 
	// will be incremented once this new Version is created) (int), and the time at which this 
	// version was 'created' (int).
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
	// getVersionNumber
	//
	// PURPOSE: returns the version number
	// RETURNS: versionNumber (int)
	// -----------------------------------------------------------------------------------------
	public int getVersionNumber(){
		return versionNumber;
	}

	// -----------------------------------------------------------------------------------------
	// getTime
	//
	// PURPOSE: returns the time
	// RETURNS: time (int)
	// -----------------------------------------------------------------------------------------
	public int getTime(){
		return time;
	}

	// -----------------------------------------------------------------------------------------
	// getTotalLines
	//
	// PURPOSE: returns the current total number of lines.
	// RETURNS: totalLines (int)
	// -----------------------------------------------------------------------------------------
	public int getTotalLines(){
		return totalLines;
	} // getTotalLines

	// -----------------------------------------------------------------------------------------
	// getLines
	//
	// PURPOSE: returns the current list of lines
	// RETURNS: listOfLines (List)
	// -----------------------------------------------------------------------------------------
	public List getLines(){
		return listOfLines;
	} // getLines


	// -----------------------------------------------------------------------------------------
	// append
	//
	// PURPOSE: appends content of a single line at the end of the list of lines (List).
	// INPUT: line to append (String)
	// -----------------------------------------------------------------------------------------
	public void append(String addLine){
		totalLines++;

		Line line = new Line(addLine, totalLines-1); // create a new line object with input 
		listOfLines.add(line); // add this new line object to the list of lines so far
	} // append

	// -----------------------------------------------------------------------------------------
	// getLineAt
	//
	// PURPOSE: checks the list of lines for a line that has a particular line number. If it 
	// exists then this method returns this lines. 
	// PARAMETER: line number to search for (int)
	// RETURNS: the line (Line) with the corresponding lineNumber if it exists in the list, 
	// returns null otherwise. 
	// -----------------------------------------------------------------------------------------
	public Line getLineAt(int lineNumber){
		Line foundLine = null;
		boolean found = false;
		Line current = null; // to iterate through the list of lines
		Entity result = listOfLines.getFirstItem(); 

		// safe casting
		if (result instanceof Line){
			current = (Line) result;
			result = null;
		}

		// iterate to the end of the list or unil we find the line with the line number
		while(current != null && !found){
	
			if (current.getLineNumber() == lineNumber){
				// this is the line we were looking for!
				foundLine = current; 
				found = true;
			}

			// iterate
			result = listOfLines.getNextItem((Entity) current);

			if (result instanceof Entity && result != null) {
				current = (Line) result;
				result = null;
			} else {
				current = null;
			}
		}

		return foundLine;
	}

	// -----------------------------------------------------------------------------------------
	// decLineNumbers
	//
	// PURPOSE: Recieves a line. If this line is a part of a list of lines then it decrements 
	// the following list of line's lineNumber by exactly one.
	// PARAMETER: line (Line)
	// -----------------------------------------------------------------------------------------
	public void decLineNumbers(Line linesAfter){
		int lineNum; 
		Line current = null; // to iterate through the list of lines
		Entity result = listOfLines.getNextItem((Entity) linesAfter);

		// safe casting
		if (result instanceof Line){
			current = (Line) result; 
			result = null; 
		}
		
		// iterate through the list until the end
		while(current!= null){
			lineNum = current.getLineNumber(); // get the line number
			current.setLineNum(lineNum-1); // subtract by exactly 1

			// iterate
			result = listOfLines.getNextItem((Entity) current);

			if(result instanceof Line && result != null){
				current = (Line) result;
				result = null;
			} else {
				current = null;
			}
		}

	} // decLineNumbers

	// -----------------------------------------------------------------------------------------
	// print
	//
	// PURPOSE: print list of list of lines by calling it's print method. 
	// -----------------------------------------------------------------------------------------
	public void print(){
			listOfLines.print();	
	} // print

	// -----------------------------------------------------------------------------------------	
	// PRIVATE METHODS
	// -----------------------------------------------------------------------------------------

	// -----------------------------------------------------------------------------------------
	// versionClone
	//
	// PURPOSE: recieves a list of lines and creates a new copy of the list.
	// OUTPUT: Returns a cloned List 
	// -----------------------------------------------------------------------------------------
	private List versionClone(List listofLines){
		List copiedLines = new List();
		Line current = null; // to iterate through the lines
		Entity result = listofLines.getFirstItem();

		//  safe casting!
		if (result instanceof Line){
			current = (Line) result;
			result = null;
		}

		// iterate the list of lines until we get to the end
		while(current != null){
			Line temp = new Line(current); // create a copy
			copiedLines.add(temp); // add to the list of copies lines

			result = listofLines.getNextItem((Entity) current); // iterate
			
			if (result instanceof Line){
				current = (Line) result;
				result = null; 
			} else {
				current = null;
			}
			
		}

		return copiedLines;

	} // versionClone

} // Version
