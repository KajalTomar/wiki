class Line extends Entity{

	private int lineNumber; 
    private String line;

	// -----------------------------------------------------------------------------------------
	// void constructor
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
	// PURPOSE: sets the line and line number to input line's line number and line
	// -----------------------------------------------------------------------------------------
	public Line(Line copyLine){
		this.lineNumber = copyLine.getLineNumber();
		this.line = copyLine.getLine();
	}

	// -----------------------------------------------------------------------------------------
	// getLineNumber
	//
	// PURPOSE: returns the line number
	// OUTPUT: lineNumber (int)
	// -----------------------------------------------------------------------------------------
	public int getLineNumber(){
		return lineNumber;
	} // getLineNumber

	// -----------------------------------------------------------------------------------------
	// getLine
	//
	// PURPOSE: returs the line 
	// OUTPUT: line (String)
	// -----------------------------------------------------------------------------------------	
	public String getLine(){
		return line;
	} // getLine

	// -----------------------------------------------------------------------------------------
	// setLine
	//
	// PURPOSE: sets line to the input line
	// INPUT: line (String)
	// -----------------------------------------------------------------------------------------	
	public void setLine(String line){
		this.line = line;
	} // setLine

	// -----------------------------------------------------------------------------------------
	// setLineNum
	//
	// PURPOSE: sets the line number to the input line number
	// INPUT: lineNumber (int)
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