public class Node{
	
	private Entity data;
	private Node next;
	private Node prev;

	//-----------------------------------------------------------------------------------------
	// Void Constructor
	// ----------------------------------------------------------------------------------------
	public Node(){
		this.data = null; 
		this.next = null;
		this.prev = null; 
	}

	//-----------------------------------------------------------------------------------------
	// Constructor
	// ----------------------------------------------------------------------------------------
	public Node(Entity data, Node next){
		this.data = data; 
		this.prev = null;
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
	// getNextItem
	//
	// PURPOSE: accessor method
	// OUTPUT: Returns next Entity
	// -----------------------------------------------------------------------------------------
	public Entity getNextItem(){
		return next.getData();
	} // getNext

	// -----------------------------------------------------------------------------------------
	// getPrev
	//
	// PURPOSE: accessor method
	// OUTPUT: Returns prev (Node)
	// -----------------------------------------------------------------------------------------
	public Node getPrev(){
		return prev;
	} // getPrev

	// -----------------------------------------------------------------------------------------
	// setNext
	//
	// PURPOSE: sets next Node 
	// INPUT: Node to set 'next' to
	// -----------------------------------------------------------------------------------------
	public void setNext(Node next){
		this.next = next;
	} // setNext

	// -----------------------------------------------------------------------------------------
	// setPrev
	//
	// PURPOSE: sets previous Node 
	// INPUT: Node to set 'prev' node to
	// -----------------------------------------------------------------------------------------
	public void setPrev(Node prev){
		this.prev = prev;
	} // setPrev

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
