class Document extends Entity{
	
	private String title; // for now
	private List versions;
	private Version lastVersion; 
	
	// -----------------------------------------------------------------------------------------
	// void contructor
	// -----------------------------------------------------------------------------------------
	public Document(){
		title = null;
		versions = null;
		lastVersion = null;
		// lastVersion = new Version(); do i need this?
	}

	// -----------------------------------------------------------------------------------------
	// contructor
	// -----------------------------------------------------------------------------------------
	public Document(String title){
		this.title = title;
		versions = new List();
		lastVersion = null;
		// lastVersion = new Version(); do i need this?
	}

	public List getAllVerions(){
		return versions;
	}

	public int totalVersions(){
		return versions.getTotal();
	}
  	
	// -----------------------------------------------------------------------------------------
	// append
	//
	// PURPOSE: create a new version of the document and append the new line to it. Add the new
	// version to the list of all versions so far.
	// INPUT: line to add to the document (String)
	// -----------------------------------------------------------------------------------------
	public void append(String line){
		Version newVersion;

		if (lastVersion == null){
			// if no current version of the document exist then create a new Version.
			newVersion = new Version();
		} else {
			// create a new Version by sending along the most recent one.
			newVersion = new Version(lastVersion);
		}
	
		newVersion.append(line); // append line to this new version
		lastVersion = newVersion; // update last version to the latest one
		versions.addLast(newVersion); // add new version to the list of all versions
	
	} // append

	// -----------------------------------------------------------------------------------------
	// print
	//
	// PURPOSE: prints the most recent version of the document if it exists.
	// -----------------------------------------------------------------------------------------
	public void print(){
		System.out.println("----------");
		System.out.print(title + " | Number of versions: " + versions.getTotal() + "\n");
	
		// if at least one version exsits then call the version's print method
		if(lastVersion != null){
			lastVersion.print();
		 }
		
	} // print

	// -----------------------------------------------------------------------------------------
	// isDuplicate
	//
	// PURPOSE: checks if the input name matches the Document title.
	// INPUT: name to compare with (String).
	// OUTPUT: returns true if the document title and name match exactly, returns false otherwise.
	// -----------------------------------------------------------------------------------------
	public boolean isDuplicate(String name){
		return name.equals(title);
	}

} // Document
