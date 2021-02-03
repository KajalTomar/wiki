public class Node{
	private Wiki data;
	private Node next;

	public Node(Wiki data, Node next){
		this.data = data; 
		this.next = next; 
	}

	public Wiki getData(){
		return data;
	}

	public Node getNext(){
		return next;
	}

	public void setNext(Node next){
		this.next = next;
	}

	public void print() {
		
		data.print(); // print out current node 

		// print out the rest of the list 
		if(next != null){
			next.print();
		}
	}


}
