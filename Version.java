class Version extends Wiki {
	private List listOfLines;
	
	public Version(){
		listOfLines = new List();
	}

	public Version(Version lastVersion){
		listOfLines = new List();
		if(lastVersion!=null){
			listOfLines = lastVersion.getLines().copyList();
		}
	}

	public List getLines(){
		return listOfLines;
	}

	public void append(String addLine){
		Line line = new Line(addLine);
		listOfLines.addLast(line);
	}

	public void print(){
		System.out.println("----------");
		listOfLines.print();
		System.out.println("----------");
	}


}
