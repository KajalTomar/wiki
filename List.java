//---------------------------------------------------------------------
// CLASS: List.java
//
// Author: Kajal Tomar, 
//
// REMARKS: implementation of a linked list. Holds the head and tail 
// node. Provied access to the Entity objects help in the Node. 
// It also provides methods to set, access, manipulate and print the 
// list.
// 
//---------------------------------------------------------------------

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

	} // List
	
	// -----------------------------------------------------------------------------------------
	// getFirstItem
	//
	// PURPOSE: returns the first item on the list. 
	// RETURNS: returns the data held in the head node if it is not null, returns null 
	// otherwise (if the list is empty).
	// -----------------------------------------------------------------------------------------
	public Entity getFirstItem(){
		Entity firstItem = null;

		if(head != null){
			// if head is not null then get the data
			firstItem = head.getData();
		}

		return firstItem;

	} // getFirstItem

	// -----------------------------------------------------------------------------------------
	// getLastItem
	//
	// PURPOSE: returns the last item on the list. 
	// RETURNS: returns the data held in the tail node if it is not null, returns null 
	// otherwise (if the list is empty).
	// -----------------------------------------------------------------------------------------
	public Entity getLastItem(){
		Entity lastItem = null;

		if(tail != null){
			// if the tail is not empty then get the data 
			lastItem = tail.getData();
		}

		return lastItem;

	} // getLastItem
	
	// -----------------------------------------------------------------------------------------
	// getLastItem
	//
	// PURPOSE: returns the data in the next node.
	// PARAMETER: recieves an Entity object. Searches the list for a node containing this entity. 
	// If such a node exists then it returns the Enity held in the node.
	// RETURNS: returns the data held next Node or null if no such node exists.
	// -----------------------------------------------------------------------------------------
	public Entity getNextItem(Entity item){
		Node foundAt = null;
		Entity entity = null;

		if(item != null){
			// if item isn't null then search for it
			foundAt = search(item);
		}

		if(foundAt != null){
			// if the node that the item was found in isn't null
			if(foundAt.getNext() != null){
				// and it has a next item
				entity = foundAt.getNext().getData();
			}
		}

		return entity;

	} // getNextItem

	//-----------------------------------------------------------------------------------------
	// getTotal
	//
	// PURPOSE: Returns the total number of items on the list
	// RETURNS: total (int).
	// -----------------------------------------------------------------------------------------	
	public int getTotal(){
		return total;
	} // getTotal

	// -----------------------------------------------------------------------------------------
	// add
	//
	// PURPOSE: creates a new node to hold the Entity object sent and add it to the list. 
	// Updates the total amount of Nodes on the list.
	// PARAMETER: recieves an Entity object. 
	// -----------------------------------------------------------------------------------------
	public void add(Entity data){
		Node newNode; 

		if(head == null){
			// the list is empty

			tail = new Node(data, head); // create new new node
			head = tail; 

		} else {
			// the list is not empty
			newNode = new Node(data, null); // create node
			 // add it to the end of the list
			tail.setNext(newNode);
			newNode.setPrev(tail); 
			tail = newNode; // update tail
		}

		total++;

	} // add

	// -----------------------------------------------------------------------------------------
	// delete
	//
	// PURPOSE: searches for the Entity sent as a parameter. If it exists on the list then it is
	// removed from it. The prev and next pointers are updated accordingly. 
	// PARAMETER: recieves an Entity object. 
	// -----------------------------------------------------------------------------------------
	public void delete(Entity deleteEntity){
		Node deleteMe = search(deleteEntity); // find the node we are trying to delete
		Node prev = null; 
		Node next = null;

		if(deleteMe != null){
			// deleteMe is a valid node on this list

			if(deleteMe == head){
				// if deleteMe is the first item 

				if(head.getNext() == null){	
					// if deleteMe is the only item	

					head = null;
					tail = null;
				} else {
					// there are exactly two items on the list
					// we are trying to delete the first one
					next = head.getNext();
					head = next;
					next.setPrev(null);	// unlink them
				}

			} else if (deleteMe == tail){
				// if deleteMe is that last node on the list 

				prev = deleteMe.getPrev(); // get the node before deleteMe	
				tail = prev; // make it the last node on the list
				prev.setNext(null); // disconnet it from the list
			} else {
				// deleteMe is somewhere in the middle

				prev = deleteMe.getPrev();  // get the node right before deleteMe
				next = deleteMe.getNext(); // and right after

				// then exclude deleteMe from the list 
				prev.setNext(next);
				next.setPrev(prev);
			}

			// set deleteMe to null
			deleteMe.setNext(null);
			deleteMe.setPrev(null);
			deleteMe = null;

			// update the total
			total--;
		}

	} // delete

	//-----------------------------------------------------------------------------------------
	// Print
	//
	// PURPOSE: calls the head Node's print method (which prints the rest of the list).
	// -----------------------------------------------------------------------------------------	
	public void print(){
		// prints the list starting from the head node

		if(head!=null){
			// at least one item in on the list
			head.print();
		}

		System.out.println("");

	} // print

	//-----------------------------------------------------------------------------------------
	// search
	//
	// PURPOSE: searches for the node containing Entity on the list.
	// PARAMETERS: the Entity item to look for.
	// RETURNS: the Node where the Entity exists or null if it is not found. 
	// -----------------------------------------------------------------------------------------	
	private Node search(Entity item){
		Node curr = head; // will be used to iterate through the list
		Node foundNode = null;
		boolean found = false;

		// iterate the list till the end or until item is found
		while(curr != null && !found){

			if(curr.getData() == item){
				// we found item!
				foundNode = curr;
				found = true;
			}
				curr = curr.getNext(); // iterate
		}

		return foundNode;
	} // search

} // List
