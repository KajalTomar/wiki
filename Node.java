public class Node{
	
	private Entity data;
	private Node next;

	//-----------------------------------------------------------------------------------------
	// Void Constructor
	// ----------------------------------------------------------------------------------------
	public Node(){
		this.data = null; 
		this.next = null; 
	}

	//-----------------------------------------------------------------------------------------
	// Constructor
	// ----------------------------------------------------------------------------------------
	public Node(Entity data, Node next){
		this.data = data; 
		this.next = next; 
	}

	// -----------------------------------------------------------------------------------------
	// getData
	//
	// PURPOSE: accessor method
	// OUTPUT: Returns data (Entity)
	// -----------------------------------------------------------------------------------------
	public Entity getData(){
		return data;
	} // getData

	// -----------------------------------------------------------------------------------------
	// getNext
	//
	// PURPOSE: accessor method
	// OUTPUT: Returns next (Node)
	// -----------------------------------------------------------------------------------------
	public Node getNext(){
		return next;
	} // getNext

	// -----------------------------------------------------------------------------------------
	// getNext
	//
	// PURPOSE: sets next Node 
	// INPUT: Node to set 'next' to
	// -----------------------------------------------------------------------------------------
	public void setNext(Node next){
		this.next = next;
	} // setNext

	// -----------------------------------------------------------------------------------------
	// isDuplicate
	//
	// PURPOSE: calls Entity's isDuplicate method to check if it matches the input key (String).
	// INPUT: some String
	// OUTPUT: result from Entity's isDuplicate method (boolean)
	// -----------------------------------------------------------------------------------------
	public boolean isDuplicate(String name){
		return data.isDuplicate(name);
	} // isDuplicate

	// -----------------------------------------------------------------------------------------
	// print
	//
	// PURPOSE: prints the entire list starting from the first item.
	// -----------------------------------------------------------------------------------------
	public void print() {
		
		data.print(); // print out current node 

		// print out the rest of the list 
		if(next != null){
			next.print();
		}
	} // print

} // Node
