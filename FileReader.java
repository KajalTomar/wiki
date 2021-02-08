import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files


public class FileReader{
	
	Wiki wiki1;

	//-----------------------------------------------------------------------------------------
	// Constructor
	// ----------------------------------------------------------------------------------------
	public FileReader(){
		wiki1 = new Wiki();
	} 

	// -----------------------------------------------------------------------------------------
	// ReadFile
	//
	// PURPOSE: Reads the file line by line and calls the correct methods to manipulate the wiki.
	// INPUT: file name (String)
	// -----------------------------------------------------------------------------------------
	public void readFile(String fileName){
		boolean quit = false;
		String command;
		int i; // index

		try {
      		File file = new File(fileName);
      		Scanner myReader = new Scanner(file);

      		while (myReader.hasNextLine() && !quit) {
        		String line = myReader.nextLine();

				if (line.equals("QUIT")){
					quit();
					quit = true;
				} else if(line.charAt(0)!='#'){
					// only if the line isn't a comment or the quit command

					i = line.indexOf(' ');
					command = line.substring(0,i);
					line = line.substring(i);

					// should lower case commands be expected? or mixed case?
					switch (command){
						case "USER":
							user(command, line);
							break;
						case "CREATE":
							create(command, line);
							break;
						case "APPEND":
							append(command, line);
							break;
						case "REPLACE":
							replace(command, line);
							break;
						case "DELETE":
							delete(command, line);
							break;
						case "PRINT":
							print(command, line);
							break;
						case "RESTORE":
							restore(command, line);
							break;
						case "HISTORY":
							history(command, line);
							break;
						case "USERREPORT":
							userreport(command, line);
							break;
					}
				}
			  }

      			myReader.close();

			  	if(!quit){
				System.out.println("MISSING QUIT COMMAND. \nTERMINATING PROGRAM.");
				quit = true;
			}

    	} catch (FileNotFoundException e) {
      		System.out.println("That file does not exist.");
    	}
		
	}

	private void user(String command, String userid){
		userid = userid.replaceAll("\\s+","");
		wiki1.user(userid);
	}

	private void create(String command, String restOfLine){
		String[] line = restOfLine.split("\\s+");
		String title = line[1];
		String userid = line[2];

		wiki1.create(title,userid);
		
	}	

	private void append(String command, String restOfLine){
		String[] line = restOfLine.split("\\s+");
		String title = line[1];
		String userid = line[2];
		String content="";
		int i = 3; // index;

		while(i < line.length){
			content+= line[i] + " ";
			i++;
		}

		wiki1.append(title,userid,content);
	}

	private void replace(String command, String restOfLine){
		String[] line = restOfLine.split("\\s+");
		String title = line[1];
		String userid = line[2];
		int lineNumber = Integer.parseInt(line[3]);
		String content="";
		int i = 4; // index;

		while(i < line.length){
			content+= line[i] + " ";
			i++;
		}

		wiki1.replace(title,userid,lineNumber,content);
	}

	private void delete(String command, String restOfLine){
		String[] line = restOfLine.split("\\s+");
		String title = line[1];
		String userid = line[2];
		int lineNumber = Integer.parseInt(line[3]);

		wiki1.delete(title,userid,lineNumber);
	}	

	private void print(String command, String docTitle){
		docTitle = docTitle.replaceAll("\\s+","");
		wiki1.print(docTitle);
	}

	private void restore(String command, String restOfLine){
		String[] line = restOfLine.split("\\s+");
		String userid = line[1];
		String title = line[2];
		int time = Integer.parseInt(line[3]);

		wiki1.restore(userid,title,time);
	}

	private void history(String command, String docTitle){
		docTitle = docTitle.replaceAll("\\s+","");
		wiki1.history(docTitle);
	}

	private void userreport(String command, String userid){
		userid = userid.replaceAll("\\s+","");
		wiki1.userReport(userid);
	}

	private void quit(){
		wiki1.quit();
	}
}
