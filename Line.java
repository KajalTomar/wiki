//---------------------------------------------------------------------
// CLASS: Line.java
//
// Author: Kajal Tomar, 
//
// REMARKS: holds a line and a corresponding line number. Has methods
// to be able to get, set, copy and print these properties. 
// 
//---------------------------------------------------------------------

class Line extends Entity{

	private int lineNumber; 
    private String line;

	// -----------------------------------------------------------------------------------------
	// null constructor
	// -----------------------------------------------------------------------------------------
    public Line(){
		lineNumber = 0;
		line = null;
	}

	// -----------------------------------------------------------------------------------------
	// constructor
	// -----------------------------------------------------------------------------------------
    public Line(String line, int lineNumber){
		this.lineNumber = lineNumber;
		this.line = line;
	}

	// -----------------------------------------------------------------------------------------
	// constructor
	//
	// PURPOSE: sets the parameter of this to the parameters of copyLine (Line).
	// PARAMETERS: the line to copy (Line)
	// -----------------------------------------------------------------------------------------
	public Line(Line copyLine){
		this.lineNumber = copyLine.getLineNumber();
		this.line = copyLine.getLine();
	}

	// -----------------------------------------------------------------------------------------
	// getLineNumber
	//
	// PURPOSE: returns the line number
	// RETURNS: lineNumber (int)
	// -----------------------------------------------------------------------------------------
	public int getLineNumber(){
		return lineNumber;
	} // getLineNumber

	// -----------------------------------------------------------------------------------------
	// getLine
	//
	// PURPOSE: returs the line 
	// RETURNS: line (String)
	// -----------------------------------------------------------------------------------------	
	public String getLine(){
		return line;
	} // getLine

	// -----------------------------------------------------------------------------------------
	// setLine
	//
	// PURPOSE: sets line to the input line
	// PARAMETER: line (String)
	// -----------------------------------------------------------------------------------------	
	public void setLine(String line){
		this.line = line;
	} // setLine

	// -----------------------------------------------------------------------------------------
	// setLineNum
	//
	// PURPOSE: sets the line number to the input line number
	// PARAMETER: lineNumber (int)
	// -----------------------------------------------------------------------------------------
	public void setLineNum(int lineNumber){
		this.lineNumber = lineNumber;
	} // setLineNum

	// -----------------------------------------------------------------------------------------
	// print
	//
	// PURPOSE: prints line.
	// -----------------------------------------------------------------------------------------
	public void print(){
		System.out.print(lineNumber + ": " + line + " \n");
	} // print

} // line