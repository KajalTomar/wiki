//---------------------------------------------------------------------
// CLASS: Document.java
//
// Author: Kajal Tomar, 7793306
//
// REMARKS: holds information about a document (title, a list of all 
// versions, author, and all the edits done to it). Provides methods
// to be able to access, set and manipulate the document. This class
// is a subclass of the class Entity.java.
// 
//---------------------------------------------------------------------
class Document extends Entity{
	
	private String title; 
	private List versions; // list of all Versions of this document
	private Version lastVersion; // the latest version of the document
	private User createdBy; 
	private String edits; // all the edits made on this document
	
	// -----------------------------------------------------------------------------------------
	// null contructor
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
	//
	// PURPOSE: store the title of the document, the user who created it and the time it was 
	// created at. Add the create command to the User's command history and to the string of edits 
	// for this document. Lastly, display an output confirming the creation of this.
	// -----------------------------------------------------------------------------------------
	public Document(String title, User user, int time){
		this.title = title;
		this.createdBy = user;

		versions = new List(); 
		lastVersion = null;
		edits = "";

		// update edit history
		addEdit("t"+time+": "+user.getUserId()+" created new document \"" + title +"\""); // add to the history of the document
		
		// update user
		user.addCommand("t" +time+ " CREATE \"" + title +"\".");
		System.out.println("CONFIRMED. "+user.getUserId()+" just created a new document called \""+title+"\"!");
	
	}

	// -----------------------------------------------------------------------------------------
	// getAllVerions
	//
	// PURPOSE: returns the list of all the versions
	// RETURNS: versions (List)
	// -----------------------------------------------------------------------------------------
	public List getAllVerions(){
		return versions;
	}

	// -----------------------------------------------------------------------------------------
	// createdBy
	//
	// PURPOSE: returns User that created this document
	// RETURNS: createdBy (User)
	// -----------------------------------------------------------------------------------------
	public User createdBy(){
		return createdBy;
	}

	// -----------------------------------------------------------------------------------------
	// getTitle
	//
	// PURPOSE: returns the title of this document
	// RETURNS: title (String)
	// -----------------------------------------------------------------------------------------
	public String getTitle(){
		return title;
	}

	// -----------------------------------------------------------------------------------------
	// totalVersions
	//
	// PURPOSE: returns the total versions held 
	// RETURNS: (int)
	// -----------------------------------------------------------------------------------------
	public int totalVersions(){
		return versions.getTotal();
	}

	// -----------------------------------------------------------------------------------------
	// getLatestVersion
	//
	// PURPOSE: returns the latest Version
	// RETURNS: (int) or null is no versions exist
	// -----------------------------------------------------------------------------------------
	public Version getLatestVersion(){
		return lastVersion;
	}

	// -----------------------------------------------------------------------------------------
	// getVersionAtTime
	//
	// PURPOSE: searches the list of versions for a version that has the same time field. 
	// PARAMETERS: time (int) to use as key for the when searching for the version. 
	// RETURNS: the Version if it finds it and null otherwise.
	// -----------------------------------------------------------------------------------------
	public Version getVersionAtTime(int time){
		boolean found = false;
		Version foundVersion = null; // will hold the version we are loking for
		Version previous = null; // the previous version in the list
		Entity result = (Version) versions.getFirstItem(); // store in Entity 
		Version current = null; // will hold the current version (to iterate)
		
		// safe cast
		if (result instanceof Version){
			current = (Version) result;
			result = null;
		}
		
		if(totalVersions() > 0){
			// at least one version exists

			if(current == lastVersion && versions.getNextItem((Entity) current) == null){
				// there is only one version

				if (lastVersion.getTime() <= time){
					// the only version existed at the time or before the time
					found = true;
					foundVersion = current;
				} 

			} else {
				// there are more than one versions

				// check the all versions or until we find one with the correct time
				while(versions.getNextItem((Entity)current)!=null && !found){
					
					// iterate
					previous = current;
					result = versions.getNextItem((Entity)current);
					current = (Version) result;


					if(current.getTime() == time){
						// this is the time we are looking for 
						found = true;
						foundVersion = current; // so return the current version

					} else if (current.getTime() > time && previous.getTime() <= time){
						// the current version was created at a time later than what we are seeking 
						// and previous version's time is less than what we are seeking

						found = true;
						foundVersion = previous; // so return the previous version
					}
				}
			}
		}

		return foundVersion;

	}

	public void addEdit(String edit){
		edits = (edit +"\n" + edits); // add edit to the front 
	}

	// -----------------------------------------------------------------------------------------
	// append
	//
	// PURPOSE: create a new version of the document and append the new line to it. Add the new
	// version to the list of all versions so far.
	// PARAMETERS: line to add to the document (String)
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

		// update user's command history
		user.addCommand("t"+atTime+": APPEND \""+title+"\" \""+line+"\".");
	
	} // append

	// -----------------------------------------------------------------------------------------
	// copyVersion
	//
	// PURPOSE: creates a new version by copying the given parameter version.
	// PARAMETERS: version to copy (Version), time (int)
	// -----------------------------------------------------------------------------------------
	public void copyVersion(Version toCopy, int atTime){
		Version newVersion = new Version(toCopy, versions.getTotal(), atTime);	
		lastVersion = newVersion;
		versions.add(newVersion); // add new version to the list of all versions
	}

	// -----------------------------------------------------------------------------------------
	// replaceLine
	//
	// PURPOSE: creates a copy of the lastest Version, then replaces a specified line number
	// with a new given line. Updates the document's history (if line is successfully deleted) 
	// and the user's command history. Also prints out a SUCCESS / FAIL (if the line number trying 
	// to replace does not exist). 
	// PARAMETERS: line number to replace in new Version (int), the line to replace it with (String)
	// the user that is attempting to replace the line (User), and at what time (int).
	// -----------------------------------------------------------------------------------------
	public void replaceLine(int lineNum, String replacementLine, User user, int atTime){
	    Line line = null;
		String oldLine;

		if (lineNum >= 0 && lineNum < lastVersion.getTotalLines()){
				// lineNumber is valid
				
				copyVersion(lastVersion,atTime); // this creates a new version that is identical to the current version
				line = lastVersion.getLineAt(lineNum); // find the Line with line number as the last line in the list of lines
				oldLine = line.getLine(); // get the line and store it

				line.setLine(replacementLine); // replace it with this new line
				System.out.println("SUCCESS. "+user.getUserId()+" replaced line "+lineNum+" in \""+title+"\".");

				addEdit("t"+atTime+": "+user.getUserId()+" replaced line "+lineNum+" \""+oldLine+"\" ---> \""+replacementLine+"\""); // add to the history of the document

				// add to user's history
				user.addCommand("t"+atTime+": REPLACE \""+title+"\" "+lineNum+" \""+replacementLine+"\".");

			} else {
				// there is no line number lineNum
				System.out.println("FAIL. Line number "+lineNum+" does not exist.");
			}

	} // replacementLine

	// -----------------------------------------------------------------------------------------
	// deleteLine
	//
	// PURPOSE: creates a copy of the lastest Version, then deletes the Line object with a 
	// specified line number. Updates the document's history (if line is successfully deleted) 
	// and the user's command history. Also prints out a SUCCESS / FAIL (if the line number trying 
	// to delete does not exist). 
	// PARAMETERS: line number to delete in the new Version (int), the user that is attempting 
	// delete the line (User), and at what time (int).
	// -----------------------------------------------------------------------------------------
	public void deleteLine(int lineNum, User user, int atTime){
		List allLines = null; 
		Line deleteLine = null; 
		String oldLineContent; 

		if(lineNum < lastVersion.getTotalLines() && lineNum >= 0){
			// the line number we are trying to delete is non-negative and less than the highest line number in the document so far

			copyVersion(lastVersion, atTime); // this creates a new version that is identical to the current version
			allLines = lastVersion.getLines();
			deleteLine = lastVersion.getLineAt(lineNum); // get the line we are trying to delete

			if(deleteLine != null){
				// the line we are trying to delete is valid
				oldLineContent = deleteLine.getLine();
				
				// decrement the line number of the lines following the delete line by one
				lastVersion.decLineNumbers(deleteLine);
				allLines.delete(deleteLine); // delete the line
				
				System.out.println("SUCCESS. "+user.getUserId()+" deleted line "+lineNum+".");
				addEdit("t"+atTime+": "+user.getUserId()+" deleted line "+lineNum+" ---\""+oldLineContent+"\""); // add to the history of the document

				// update use
				user.addCommand("t"+atTime+": DELETE \""+title+"\" "+lineNum+".");
			} else {
				System.out.println("FAIL. Line number "+lineNum+" does not exist.");
			}
		} else {
			System.out.println("FAIL. Line number "+lineNum+" does not exist.");
		}
	
	} // deleteLine

	// -----------------------------------------------------------------------------------------
	// restoreToTime
	//
	// PURPOSE: retrieves a version that matches the time given. If such a version exists,
	// then it copies the contents of this version to a new version. 
	// PARAMETERS: time of the version we want to copy (int), the time the new copy of the version
	// got created it (int), the user that created this new version (User).
	// -----------------------------------------------------------------------------------------
	public void restoreToTime(int time, int newTime, User user){
		Version restoreToThis =  getVersionAtTime(time); 

		if(restoreToThis != null){
			// a version of this document existed at time

			copyVersion(restoreToThis, newTime); // create a copy of this version but with the new time

			addEdit("t"+newTime+": "+user.getUserId()+" restored to t"+time); // add to the history of the document
			
			// update user
			user.addCommand("t"+newTime+": RESTORE \""+title+"\" t"+time+".");
			System.out.println("SUCCESS. \""+title+"\" has been restored to time "+time+".");

		} else {
			System.out.println("NOT FOUND. This document did not exist or contain content at time "+time+".");
		}
	} // restoreToTime

	// -----------------------------------------------------------------------------------------
	// isDuplicate
	//
	// PURPOSE: checks if the input name matches the Document title.
	// PARAMETER: name to compare with (String).
	// RETURNS: returns true if the document title and name match exactly, returns false otherwise.
	// -----------------------------------------------------------------------------------------
	public boolean isDuplicate(String name){
		return name.equals(title);
	}

	// -----------------------------------------------------------------------------------------
	// history
	//
	// PURPOSE: prints the edits made to this document
	// -----------------------------------------------------------------------------------------
	public void history(){
		System.out.println("----------");
		System.out.println(edits); 
		
	} // print

	// -----------------------------------------------------------------------------------------
	// print
	//
	// PURPOSE: prints the most recent version of the document (if it exists) by calling that
	// Verison's print method.
	// -----------------------------------------------------------------------------------------
	public void print(){
		System.out.println("*************************************************");
		System.out.println(title+"\n");
	
		//	if at least one version exsits then call the version's print method
		if(lastVersion != null){
			lastVersion.print();
		 }
		
	} // print

} // Document
