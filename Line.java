class Line extends Entity{

	private int lineNum; 
    private String line;

	// -----------------------------------------------------------------------------------------
	// void constructor
	// -----------------------------------------------------------------------------------------
    public Line(){
		lineNum = 0;
		line = null;
	}

	// -----------------------------------------------------------------------------------------
	// constructor
	// -----------------------------------------------------------------------------------------
    public Line(String line){
		lineNum = 0;
		this.line = line;
	}

	// -----------------------------------------------------------------------------------------
	// isDuplicate
	//
	// PURPOSE: checks if the input line matches line.
	// INPUT: line to compare with (String)
	// OUTPUT: returns true if the lines match, otherwise it returns false.
	// -----------------------------------------------------------------------------------------
	public boolean isDuplicate(String duplicateLine){
		return line.equals(duplicateLine);
	}

	// -----------------------------------------------------------------------------------------
	// print
	//
	// PURPOSE: prints line.
	// -----------------------------------------------------------------------------------------
	public void print(){
		System.out.print(line + " \n");
	} // print

} // line