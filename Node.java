//---------------------------------------------------------------------
// REMARKS: this is a node class that holds an object of type Entity.
// It also provides methods to set, access, and print this 
// information. It also hold information about the previous and next
//  Node object and allows access to them.
// 
//---------------------------------------------------------------------

public class Node{
	
	private Entity data;
	private Node next;
	private Node prev;

	//-----------------------------------------------------------------------------------------
	// null Constructor
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
	// RETURNS: data (Entity)
	// -----------------------------------------------------------------------------------------
	public Entity getData(){
		return data;
	} // getData

	// -----------------------------------------------------------------------------------------
	// getNext
	//
	// PURPOSE: accessor method
	// RETURNS: next (Node) if it exists or null otherwise.
	// -----------------------------------------------------------------------------------------
	public Node getNext(){
		return next;
	} // getNext

	// -----------------------------------------------------------------------------------------
	// getNextItem
	//
	// PURPOSE: accessor method
	// RETURNS: Returns next the Entity if it exists or null otherwise.
	// -----------------------------------------------------------------------------------------
	public Entity getNextItem(){
		return next.getData();
	} // getNext

	// -----------------------------------------------------------------------------------------
	// getPrev
	//
	// PURPOSE: accessor method
	// RETURNS: prev (Node) or null current Node == head
	// -----------------------------------------------------------------------------------------
	public Node getPrev(){
		return prev;
	} // getPrev

	// -----------------------------------------------------------------------------------------
	// setNext
	//
	// PURPOSE: sets next Node 
	// PARAMETERS: Node to set 'next' to
	// -----------------------------------------------------------------------------------------
	public void setNext(Node next){
		this.next = next;
	} // setNext

	// -----------------------------------------------------------------------------------------
	// setPrev
	//
	// PURPOSE: sets previous Node 
	// PARAMETERS: Node to set 'prev' node to
	// -----------------------------------------------------------------------------------------
	public void setPrev(Node prev){
		this.prev = prev;
	} // setPrev

	// -----------------------------------------------------------------------------------------
	// print
	//
	// PURPOSE: prints the entire list starting from the current node.
	// -----------------------------------------------------------------------------------------
	public void print() {
		
		data.print(); // print out current node 

		// print out the rest of the list 
		if(next != null){
			next.print();
		}
	} // print

} // Node
