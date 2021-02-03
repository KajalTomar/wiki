class Line extends Wiki {
    private String line;

    public Line(String line){
		this.line = line;
	}

	public void print(){
		System.out.print(line + " \n");
	}

}