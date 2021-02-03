public class FileReader{
	private List userList;
	private List documents; 

	private Document doc;
	private Document doc2;

	public FileReader(){
		userList = new List();
		documents = new List();
	}

	void readFile(){
		userList.addFront(new Users("Bob"));
		userList.addFront(new Users("Stacey"));
		userList.addFront(new Users("Benny"));
		userList.addFront(new Users("Jo"));
	
		doc = new Document("Document 1");
		doc2 = new Document("Document 2");

		documents.addLast(doc);
		documents.addLast(doc2);
		documents.addLast(new Document("Document 3"));
		documents.addLast(new Document("Document 4"));

		doc.append("Yo, what's up");
		doc.append("Nothing much");
		doc.print();

		doc2.append("Hi there duda");
		//userList.print();

		doc2.print();

		doc2.append("Fancy SHmansy");

		doc2.print();

		doc2.append("Yessss");
		
		doc2.print();



	}

}
