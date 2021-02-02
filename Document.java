class Document extends Wiki {
	private String doc; // for now

	public Document(String line){
		doc = line;
	}

	public void print(){
		System.out.print(doc + "\n");
	}
}
