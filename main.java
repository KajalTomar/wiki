//-------------------------------------------------------------------------------
// NAME: 
// STUDENT NUMBER: 
// COURSE: , SECTION: 
// INSTRUCTOR: 
// ASSIGNMENT: 
// 
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

		System.out.print("Input file name: ");
		fileName = input.nextLine().trim();

		// will actually ask for file name and then call a method called read file
		reader.readFile(fileName);		
		
		input.close();

		System.out.print("\nend of processing.");
	}
}
