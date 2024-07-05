//-------------------------------------------------------------------------------
// REMARKS: in this assignments we built the tools to manage a wiki using Object 
// Orientation concepts. Because of this, the assignment is given in terms of
// behaviour that it should  achieve, rather than a how the code should be 
// structured.
//-------------------------------------------------------------------------------
import java.util.Scanner;

class Main {

	public static void main(String args[]){	

		Scanner input = new Scanner(System.in);
		FileReader reader = new FileReader();
		String fileName;

		// get the input file
		System.out.print("Input file name: ");
		fileName = input.nextLine().trim();

		// send the file to readerFile
		reader.readFile(fileName);		
		
		// close scanner
		input.close();

		System.out.print("\nend of processing.");

	}

} // Main

