import java.util.*;

public class List{
	
	private Node head; 
	private Node tail;
	private int total;

	//-----------------------------------------------------------------------------------------
	// Constructor
	// -----------------------------------------------------------------------------------------
	public List(){
		head = null;
		tail = null;
		total = 0;
	}

	//-----------------------------------------------------------------------------------------
	// addFront
	//
	// PURPOSE: adds data of type Entity to the front of the list.
	// INPUT: Entity type 
	// -----------------------------------------------------------------------------------------
	public void addFront(Entity data){
		head = new Node(data, head); 
		total++;
	} // addFront

	// -----------------------------------------------------------------------------------------
	// addLast
	//
	// PURPOSE: adds data of type Entity to the end of the list.
	// INPUT: Entity type 
	// -----------------------------------------------------------------------------------------
	public void addLast(Entity data){
		Node newNode; 

		if(head == null){
			tail = new Node(data, head);
			head = tail; 
		} else {
			newNode = new Node(data, null);
			tail.setNext(newNode);
			tail = newNode;
		}

		total++;
	} // addLast

	//-----------------------------------------------------------------------------------------
	// Print
	//
	// PURPOSE: Prints the contents of the list
	// -----------------------------------------------------------------------------------------	
	public void print(){
		// prints the list starting from the head node
		if(head!=null){
			head.print();
		}

		System.out.println("");
	}

	//-----------------------------------------------------------------------------------------
	// getTotal
	//
	// PURPOSE: Returns the total number of items on the list
	// -----------------------------------------------------------------------------------------	
	public int getTotal(){
		return total;
	}

	// -----------------------------------------------------------------------------------------
	// copyList
	//
	// PURPOSE: creates a new list that is a clone of the this current list.
	// OUTPUT: Returns a clones List 
	// -----------------------------------------------------------------------------------------
	public List copyList(){
		List newCopy = new List();
		Node curr = head;

		while(curr != null){
			newCopy.addLast(curr.getData());
			curr = curr.getNext();
		}
		return newCopy;
	} // copyList

	// -----------------------------------------------------------------------------------------
	// isDuplicate
	//
	// PURPOSE: checks is an Entity with input key (String) already exists on the list.
	// INPUT: username, document title (String)
	// OUTPUT: Returns true if an Entity with the same key exists on the list, returns false
	// otherwise. Prints the result.
	// -----------------------------------------------------------------------------------------
	public boolean isDuplicate(String name){
		boolean duplicate = false;

		if(search(name)!=null){
			duplicate = true;
			System.out.println("DUPLICATE");	
		} else {
			System.out.println("NOT FOUND");
		}

		return duplicate; 
	} // isDuplicate

	// -----------------------------------------------------------------------------------------
	// retrieve
	//
	// PURPOSE: Returns an entity type (user, document) based on a key (document name, username)
	// INPUT: username, document title (String)
	// OUTPUT: On success it returns an Entity. On failure it returns null and prints 'not found'.
	// -----------------------------------------------------------------------------------------
	public Entity retrieve(String name){
		Node dataAtNode = search(name); 
		Entity data = null;

		if(dataAtNode != null){
			data = dataAtNode.getData();
		} else {
			System.out.println("NOT FOUND");
		}

		return data;

	} // retrieve

	// -----------------------------------------------------------------------------------------
	// search
	//
	// PURPOSE: Searches for an entity type (user, document) based on a key (document name, 
	// username)
	// INPUT: username, document title (String)
	// OUTPUT: On success it returns the node corresponding to the Entity. On failure it returns 
	// null.
	// -----------------------------------------------------------------------------------------
	public Node search(String name){
		Node curr = head; 
		Node foundAt = null;
		boolean found = false;

		while(curr != null && !false){
			
			if (curr.isDuplicate(name)){
				foundAt = curr; 
				found = true;
			}

			curr = curr.getNext();
		}

		return foundAt;
	} // search

} // List
