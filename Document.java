class Document extends Entity{
	
	private String title; // for now
	private List versions;
	private Version lastVersion;
	private User createdBy; 
	private String edits;
	private int time;
	
	// -----------------------------------------------------------------------------------------
	// void contructor
	// -----------------------------------------------------------------------------------------
	public Document(){
		title = null;
		versions = null;
		lastVersion = null;
		createdBy = null;
		edits = "";
		time = -1;
		// lastVersion = new Version(); do i need this?
	}

	// -----------------------------------------------------------------------------------------
	// contructor
	// -----------------------------------------------------------------------------------------
	public Document(String title, User user, int time){
		this.title = title;
		this.createdBy = user;
		this.time = time;

		versions = new List();
		lastVersion = null;
		edits = "";

		addEdit("t"+time+": "+user.getUserId()+" created new document \"" + title +"\""); // add to the history of the document
		
		// update user
		user.addCreatedDocs(this); // may not need this
		user.addCommand("t" +time+ " CREATE \"" + title +"\".");
		System.out.println("CONFIRMED. You just created a new document called \""+title+"\"!");
		// lastVersion = new Version(); do i need this?
	}

	public List getAllVerions(){
		return versions;
	}

	public User createdBy(){
		return createdBy;
	}

	public void restoreToTime(int time, int newTime, User user){
		Node restorePoint = getVersionAtTime(time);
		Version restoreToThis = null;

		if(restorePoint != null){
			restoreToThis = (Version) restorePoint.getData();

			copyVersion(restoreToThis, newTime);

			addEdit("t"+newTime+": "+user.getUserId()+" restored to t"+time); // add to the history of the document
			
			user.addCommand("t"+newTime+": RESTORE \""+title+"\" t"+time+".");
			System.out.println("SUCCESS. \""+title+"\" has been restored to time "+time+".");

		} else {
			System.out.println("NOT FOUND. This document did not exist or contain content at time "+time+".");
		}
	}

	public Node getVersionAtTime(int time){
		boolean found = false;
		Node foundAt = null;
		Node curr = versions.getFirstItem();
		Node prev = null;

		Version prevVersion = null;
		Version currVersion = null;

		if(totalVersions() > 0){
			// at least one version exists

			if((Version)curr.getData() == lastVersion && curr.getNext() == null){
				// there is only one version

				if (lastVersion.getTime() <= time){
					found = true;
					foundAt = curr;
				} else {
					System.out.println("NOT FOUND");
				}

			} else {
				// there are more than one versions

				while(curr.getNext()!=null && !found){
					// check the all versions or until we find one with the correct time

					prev = curr;
					curr = curr.getNext();

					prevVersion = (Version) prev.getData();
					currVersion = (Version) curr.getData();

					if(currVersion.getTime() == time){
						// this is the time we are looking for 

						found = true;
						foundAt = curr;
					} else if (currVersion.getTime() > time && prevVersion.getTime() <= time){
						// the current version was created at a time late than what we are seeking 
						// and previous version's time is less than what we are seeking

						found = true;
						foundAt = prev;
					}
				}
			}
			
		}

		return foundAt;
	
	}

	public int totalVersions(){
		return versions.getTotal();
	}

	public Version getLatestVersion(){
		return lastVersion;
	}
  	
	public void addEdit(String edit){
		edits = (edit +"\n" + edits);
	}

	// -----------------------------------------------------------------------------------------
	// append
	//
	// PURPOSE: create a new version of the document and append the new line to it. Add the new
	// version to the list of all versions so far.
	// INPUT: line to add to the document (String)
	// -----------------------------------------------------------------------------------------
	public void append(User user, String line, int atTime){
		Version newVersion;

		if (lastVersion == null){
			// if no current version of the document exist then create a new Version.
			newVersion = new Version(atTime);
		} else {
			// create a new Version by sending along the most recent one.
			newVersion = new Version(lastVersion, versions.getTotal(), atTime);
		}

		newVersion.append(line); // append line to this new version
		lastVersion = newVersion; // update last version to the latest one
		versions.add(newVersion); // add new version to the list of all versions

		addEdit("t"+atTime+": "+user.getUserId()+" appended +++\""+line+"\" "); // add to the history of the document

		user.addCommand("t"+atTime+": APPEND \""+title+"\" \""+line+"\".");
		System.out.println("SUCCESS. You have successfully contributed a line to \""+title+"\"");
	
	} // append

	public void copyVersion(Version toCopy, int atTime){
		Version newVersion = new Version(toCopy, versions.getTotal(), atTime);	
		lastVersion = newVersion;
		versions.add(newVersion); // add new version to the list of all versions
	}


	public void replaceLine(int lineNum, String replacementLine, User user, int atTime){
	    Line line = null;
		String oldLine;

		if (lineNum >= 0 && lineNum < lastVersion.getTotalLines()-1){
				// lineNumber is valid
				
				copyVersion(lastVersion,atTime); // this creates a new version that is identical to the current version
				line = (Line)lastVersion.getLineAt(lineNum).getData();
				oldLine = line.getLine();

				if(line != null){
					line.setLine(replacementLine);
					System.out.println("SUCCESS. Line "+lineNum+" has been replaced.");

					addEdit("t"+atTime+": "+user.getUserId()+" replaced line "+lineNum); // add to the history of the document
					addEdit("\t\""+oldLine+"\" ---> \""+replacementLine+"\"");

					user.addCommand("t"+atTime+": REPLACE \""+title+"\" "+lineNum+" \""+replacementLine+"\".");
				} else {
					System.out.println("FAIL. Line number "+lineNum+" does not exist.");
				}

			} else {
				// there is no line number lineNum
				System.out.println("FAIL. Line number "+lineNum+" does not exist.");
			}
	}

	public void deleteLine(int lineNum, User user, int atTime){
		List allLines = null;
		Node lineAt = null; 
		Line oldLine = null; 
		String oldLineContent; 

		if(lineNum < lastVersion.getTotalLines()-1 && lineNum >= 0){
			copyVersion(lastVersion, atTime); // this creates a new version that is identical to the current version
			allLines = lastVersion.getLines();
			lineAt = lastVersion.getLineAt(lineNum);

			if(lineAt != null){
				oldLine = (Line) lineAt.getData();
				oldLineContent = oldLine.getLine();
				
				lastVersion.decLineNumbers(lineAt);
				allLines.delete(lineAt);
				
				System.out.println("SUCCESS. Line "+lineNum+" has been deleted.");
				addEdit("t"+atTime+": "+user.getUserId()+" deleted line "+lineNum+" ---\""+oldLineContent+"\""); // add to the history of the document

				user.addCommand("t"+atTime+": DELETE \""+title+"\" "+lineNum+".");
			} else {
				System.out.println("FAIL. Line number "+lineNum+" does not exist.");
			}
		} else {
			System.out.println("FAIL. Line number "+lineNum+" does not exist.");
		}
	}


	// -----------------------------------------------------------------------------------------
	// print
	//
	// PURPOSE: prints the most recent version of the document if it exists.
	// -----------------------------------------------------------------------------------------
	public void print(){
		System.out.println("----------");
		System.out.println(title);
	
	//	if at least one version exsits then call the version's print method
		if(lastVersion != null){
			lastVersion.print();
		 }

		//versions.print();
		
	} // print

	public void history(){
		System.out.println("----------");
		System.out.println(edits);
		
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
