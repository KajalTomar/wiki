//---------------------------------------------------------------------
// CLASS: FileReader.java
//
// Author: Kajal Tomar, 7793306
//
// REMARKS: creates a wiki. Reads commands from a text file and 
// manipulates the wiki (or not) accordingly. 
// 
//---------------------------------------------------------------------

import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files


public class FileReader{
	
	private Wiki wiki1;

	//-----------------------------------------------------------------------------------------
	// null constructor
	// ----------------------------------------------------------------------------------------
	public FileReader(){
		wiki1 = new Wiki();
	} 

	// -----------------------------------------------------------------------------------------
	// ReadFile
	//
	// PURPOSE: Reads the file line by line, seperates out the first word in each line until
	// a quit command is encountered or the end of the file is reached.
	// Calls methods based on the commands in order to manipulate the wiki.
	// PARAMETERS: file name (String)
	// -----------------------------------------------------------------------------------------
	public void readFile(String fileName){
		boolean quit = false;
		String command;
		int i; // index

		try {
      		File file = new File(fileName);
      		Scanner myReader = new Scanner(file);

			// read the file until the end of file or until a quit command is reached
      		while (myReader.hasNextLine() && !quit) {

        		String line = myReader.nextLine(); 
				line = line.replaceFirst("^\\s*", ""); // take out whitespace from the start of the command
				if (line.equals("QUIT")){ 
					// if it's a quit command
					quit();
					quit = true;
				} else if(line.charAt(0)!='#'){
					// only if the line isn't a comment or the quit command
					
					// get the first word in the line
					i = line.indexOf(' ');
					command = line.substring(0,i);
					line = line.substring(i);

					// call the corresponding method based on the command
					switch (command){
						case "USER":
							user(line);
							break;
						case "CREATE":
							create(line);
							break;
						case "APPEND":
							append(line);
							break;
						case "REPLACE":
							replace(line);
							break;
						case "DELETE":
							delete(line);
							break;
						case "PRINT":
							print(line);
							break;
						case "RESTORE":
							restore(line);
							break;
						case "HISTORY":
							history(line);
							break;
						case "USERREPORT":
							userreport(line);
							break;
					}
				}
			}

			// close scanner
      		myReader.close();

			if(!quit){
				// if the file didn't contain a quit command
				System.out.println("MISSING QUIT COMMAND. \nTERMINATING PROGRAM.");
				quit = true;
			}

    	} catch (FileNotFoundException e) {
      		System.out.println("That file does not exist.");
    	}
		
	} // readFile

	// -----------------------------------------------------------------------------------------
	// user
	//
	// PURPOSE: removes any extra spaces from userid and call's wiki's user method.
	// PARAMETERS: userid (String)
	// -----------------------------------------------------------------------------------------
	private void user(String userid){
		userid = userid.replaceAll("\\s+",""); // remove whitespace
		wiki1.user(userid);

	} // user

	// -----------------------------------------------------------------------------------------
	// create
	//
	// PURPOSE: tokenizes the string into the title and userid to send these as 
	// parameters and calls wiki's create method.
	// PARAMETERS: the line to tokenize (String)
	// -----------------------------------------------------------------------------------------
	private void create(String restOfLine){
		// tokenize restOfLine
		String[] line = restOfLine.split("\\s+");
		String title = line[1];
		String userid = line[2];

		wiki1.create(title,userid);
		
	} // create

	// -----------------------------------------------------------------------------------------
	// append
	//
	// PURPOSE: tokenizes the string into the title, userid and content to send these as 
	// parameters and calls wiki's append method.
	// PARAMETERS: restOfLine to seperate (String)
	// -----------------------------------------------------------------------------------------
	private void append(String restOfLine){
		// tokenize restOfLine
		String[] line = restOfLine.split("\\s+");
		String title = line[1];
		String userid = line[2];
		String content="";
		int i = 3; // index;

		// combine the words to make a line
		while(i < line.length){
			content+= line[i] + " ";
			i++;
		}

		wiki1.append(title,userid,content);

	} // append

	// -----------------------------------------------------------------------------------------
	// replace
	//
	// PURPOSE: tokenizes the string into the title, userid, lineNumber and content to send these as 
	// parameters and calls wiki's replace method.
	// PARAMETERS: restOfLine to seperate (String)
	// -----------------------------------------------------------------------------------------
	private void replace(String restOfLine){
		// tokenize restOfLine
		String[] line = restOfLine.split("\\s+");
		String title = line[1];
		String userid = line[2];
		int lineNumber = Integer.parseInt(line[3]);
		String content="";
		int i = 4; // index;

		// combine the words to make a line
		while(i < line.length){
			content+= line[i] + " ";
			i++;
		}

		wiki1.replace(title,userid,lineNumber,content);
	} // replace

	// -----------------------------------------------------------------------------------------
	// delete
	//
	// PURPOSE: tokenizes the string into the title, userid, and lineNumber to send these as 
	// parameters and calls wiki's delete method.
	// PARAMETERS: restOfLine to seperate (String)
	// -----------------------------------------------------------------------------------------
	private void delete(String restOfLine){
		// tokenize restOfLine
		String[] line = restOfLine.split("\\s+");
		String title = line[1];
		String userid = line[2];
		int lineNumber = Integer.parseInt(line[3]);

		wiki1.delete(title,userid,lineNumber);

	} // delete

	// -----------------------------------------------------------------------------------------
	// print
	//
	// PURPOSE: takes out any spaces from the document's title and calls wiki's print method
	// with the document title as the parameter.
	// PARAMETERS: document title (String)
	// -----------------------------------------------------------------------------------------
	private void print(String docTitle){
		docTitle = docTitle.replaceAll("\\s+",""); // replace whitespace
		wiki1.print(docTitle);

	} // print

	// -----------------------------------------------------------------------------------------
	// restore
	//
	// PURPOSE: tokenizes the string into the title, userid, and time to send these as 
	// parameters and calls wiki's restore method.
	// PARAMETERS: restOfLine to seperate (String)
	// -----------------------------------------------------------------------------------------
	private void restore(String restOfLine){
		// tokenize restOfLine
		String[] line = restOfLine.split("\\s+");
		String userid = line[1];
		String title = line[2];
		int time = Integer.parseInt(line[3]);

		wiki1.restore(userid,title,time);

	} // restore

	// -----------------------------------------------------------------------------------------
	// history
	//
	// PURPOSE: takes out any spaces from the document's title and call's wiki's history method 
	// with the doc title as a parameter.
	// PARAMETERS: document title (String)
	// -----------------------------------------------------------------------------------------
	private void history(String docTitle){
		docTitle = docTitle.replaceAll("\\s+",""); // remove whitespace
		wiki1.history(docTitle);

	} // history

	// -----------------------------------------------------------------------------------------
	// userreport
	//
	// PURPOSE: takes out any spaces from the userid and call's wiki's userReport method with
	// the userid as the parameter.
	// PARAMETERS: userid (String)
	// -----------------------------------------------------------------------------------------
	private void userreport(String userid){
		userid = userid.replaceAll("\\s+",""); // remove whitespace
		wiki1.userReport(userid);

	} // userreport

	// -----------------------------------------------------------------------------------------
	// quit
	//
	// PURPOSE: calls wiki's quit method.
	// -----------------------------------------------------------------------------------------
	private void quit(){
		wiki1.quit();

	} // quit

} // FileReader
