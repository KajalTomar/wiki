class Document extends Entity{
	
	private String title; // for now
	private List versions;
	private Version lastVersion;
	private User createdBy; 
	private String edits;
	
	// -----------------------------------------------------------------------------------------
	// void contructor
	// -----------------------------------------------------------------------------------------
	public Document(){
		title = null;
		versions = null;
		lastVersion = null;
		createdBy = null;
		edits = "";
		// lastVersion = new Version(); do i need this?
	}

	// -----------------------------------------------------------------------------------------
	// contructor
	// -----------------------------------------------------------------------------------------
	public Document(String title, User user, int time){
		this.title = title;
		this.createdBy = user;

		versions = new List();
		lastVersion = null;
		edits = "";

		addEdit("t"+time+": "+user.getUserId()+" created new document \"" + title +"\""); // add to the history of the document
		
		// update user
		user.addCommand("t" +time+ " CREATE \"" + title +"\".");
		System.out.println("CONFIRMED. "+user.getUserId()+" just created a new document called \""+title+"\"!");
		// lastVersion = new Version(); do i need this?
	}

	public List getAllVerions(){
		return versions;
	}

	public User createdBy(){
		return createdBy;
	}

	public String getTitle(){
		return title;
	}

	public void restoreToTime(int time, int newTime, User user){
		Version restoreToThis =  getVersionAtTime(time);

		if(restoreToThis != null){
			copyVersion(restoreToThis, newTime);

			addEdit("t"+newTime+": "+user.getUserId()+" restored to t"+time); // add to the history of the document
			
			user.addCommand("t"+newTime+": RESTORE \""+title+"\" t"+time+".");
			System.out.println("SUCCESS. \""+title+"\" has been restored to time "+time+".");

		} else {
			System.out.println("NOT FOUND. This document did not exist or contain content at time "+time+".");
		}
	}

	public Version getVersionAtTime(int time){
		boolean found = false;
		Version foundVersion = null;
		Version previous = null;
		Version current = (Version) versions.getFirstItem();
		
		if(totalVersions() > 0){
			// at least one version exists

			if(current == lastVersion && versions.getNextItem((Entity) current) == null){
				// there is only one version

				if (lastVersion.getTime() <= time){
					found = true;
					foundVersion = current;
				} 

			} else {
				// there are more than one versions

				while(versions.getNextItem((Entity)current)!=null && !found){
					// check the all versions or until we find one with the correct time

					previous = current;
					current = (Version) versions.getNextItem((Entity)current);


					if(current.getTime() == time){
						// this is the time we are looking for 

						found = true;
						foundVersion = current;
					} else if (current.getTime() > time && previous.getTime() <= time){
						// the current version was created at a time late than what we are seeking 
						// and previous version's time is less than what we are seeking

						found = true;
						foundVersion = previous;
					}
				}
			}
		}

		return foundVersion;

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

		System.out.println("SUCCESS. "+user.getUserId()+" has successfully contributed a line to \""+title+"\"");

		addEdit("t"+atTime+": "+user.getUserId()+" appended +++\""+line+"\" "); // add to the history of the document

		user.addCommand("t"+atTime+": APPEND \""+title+"\" \""+line+"\".");
	
	} // append

	public void copyVersion(Version toCopy, int atTime){
		Version newVersion = new Version(toCopy, versions.getTotal(), atTime);	
		lastVersion = newVersion;
		versions.add(newVersion); // add new version to the list of all versions
	}


	public void replaceLine(int lineNum, String replacementLine, User user, int atTime){
	    Line line = null;
		String oldLine;

		if (lineNum >= 0 && lineNum < lastVersion.getTotalLines()){
				// lineNumber is valid
				
				copyVersion(lastVersion,atTime); // this creates a new version that is identical to the current version
				line = lastVersion.getLineAt(lineNum);
				oldLine = line.getLine();

				line.setLine(replacementLine);
				System.out.println("SUCCESS. "+user.getUserId()+" replaced line "+lineNum+" in \""+title+"\".");

				addEdit("t"+atTime+": "+user.getUserId()+" replaced line "+lineNum+" \""+oldLine+"\" ---> \""+replacementLine+"\""); // add to the history of the document

				user.addCommand("t"+atTime+": REPLACE \""+title+"\" "+lineNum+" \""+replacementLine+"\".");

			} else {
				// there is no line number lineNum
				System.out.println("FAIL. Line number "+lineNum+" does not exist.");
			}
	}

	public void deleteLine(int lineNum, User user, int atTime){
		List allLines = null;
		Line deleteLine = null;  // lineAt == 
		String oldLineContent; 

		if(lineNum < lastVersion.getTotalLines() && lineNum >= 0){
			copyVersion(lastVersion, atTime); // this creates a new version that is identical to the current version
			allLines = lastVersion.getLines();
			deleteLine = lastVersion.getLineAt(lineNum);

			if(deleteLine != null){
				oldLineContent = deleteLine.getLine();
				
				lastVersion.decLineNumbers(deleteLine);
				allLines.delete(deleteLine);
				
				System.out.println("SUCCESS. "+user.getUserId()+" deleted line "+lineNum+".");
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
		System.out.println("*************************************************");
		System.out.println(title+"\n");
	
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
