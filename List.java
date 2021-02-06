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

	// //-----------------------------------------------------------------------------------------
	// // addFront
	// //
	// // PURPOSE: adds data of type Entity to the front of the list.
	// // INPUT: Entity type 
	// // -----------------------------------------------------------------------------------------
	// public void addFront(Entity data){
	// 	head = new Node(data, head); 
	// 	total++;
	// } // addFront

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
			newNode.setPrev(tail);
			tail = newNode;
		}

		total++;
	} // addLast

	public Node getFirstItem(){
		return head;
	}

	public Node getLastItem(){
		return tail;
	}

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


} // List
