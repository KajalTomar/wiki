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
		
	public Node getFirstItem(){
		return head;
	}

	public Node getLastItem(){
		return tail;
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
	// add
	//
	// PURPOSE: adds data of type Entity to the end of the list.
	// INPUT: Entity type 
	// -----------------------------------------------------------------------------------------
	public void add(Entity data){
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
	} // add

	public void delete(Node deleteMe){

		Node prev = null;
		Node next = null;

		if(deleteMe != null){
			if(deleteMe == head){
				// if deleteMe is the first item 

				if(head.getNext() == null){	
					// if deleteMe is the only item		
					head = null;
					tail = null;
				} else {
					next = head.getNext();
					head = next;
					next.setPrev(null);	
				}

			} else if (deleteMe == tail){
				// if deleteMe is that last node on the list 

				prev = deleteMe.getPrev();
				
				tail = prev;
				prev.setNext(null);
			} else {
				// deleteMe is somewhere in the middle
				prev = deleteMe.getPrev();
				next = deleteMe.getNext();

				prev.setNext(next);
				next.setPrev(prev);
			}

			deleteMe.setNext(null);
			deleteMe.setPrev(null);
			deleteMe = null;

			total--;
		}

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


} // List
