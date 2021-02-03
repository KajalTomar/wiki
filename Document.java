class Document extends Wiki {
	private String title; // for now
	private List versions;
	private Version lastVersion; 

	public Document(String title){
		this.title = title;
		versions = new List();
		Version lastVersion = new Version();
	}

	public void append(String line){
		Version newVersion;

		if (lastVersion == null){
			newVersion = new Version();
		} else {
			newVersion = new Version(lastVersion);
		}
	
		newVersion.append(line);
		lastVersion = newVersion;
		versions.addLast(newVersion);
	}

	public void print(){
		System.out.print(title + "\n");
		System.out.print("Number of versions: " + versions.getTotal() + "\n");
	
		if(lastVersion != null){
			lastVersion.print();
		 }
		
	}
}
