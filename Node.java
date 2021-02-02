public class Node{
	private Wiki data;
	private Node next;

	public Node(Wiki data, Node next){
		this.data = data; 
		this.next = next; 
	}

	public void print() {
		
		data.print(); // print out current node 

		// print out the rest of the list 
		if(next != null){
			next.print();
		}
	}

	public void setNext(Node next){
		this.next = next;
	}

	public boolean isUser(){
		boolean isUser = false;

		if(data instanceof Users){
			isUser = true;
		}

		return isUser;
	}

}
