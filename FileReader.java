public class FileReader{
	private List userList;
	private List documents; 

	public FileReader(){
		userList = new List();
		documents = new List();
	}

	void addUsers(){
		userList.addFront(new Users("Bob"));
		userList.addFront(new Users("Stacey"));
		userList.addFront(new Users("Benny"));
		userList.addFront(new Users("Jo"));
	
		documents.append(new Document("This is the first line"));
		documents.append(new Document("This is the second line"));
		documents.append(new Document("This is the third line"));
		documents.append(new Document("This is the fourth line"));

		userList.print();
		documents.print();
		
	}

}
