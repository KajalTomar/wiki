import java.util.*;

public class List{
	private Node head; 
	private Node tail;
	private int total;

	public List(){
		head = null;
		tail = null;
		total = 0;
	}
	
	public void print(){
		// if (head.isUserType()){
		// 	System.out.print("USERS: [ ");
		// } else {
		// 	System.out.println("-------------");
		// }
		

		// prints the list starting from the head node
		if(head!=null){
			head.print();
		}
		
		// if (head.isUserType()){
		// 	System.out.print(" ] \n");
		// } else {
		// 	System.out.println("-------------");
		// }
	}

	// adds the new data to the front of the list 
	public void addFront(Wiki data){
		head = new Node(data, head); 
		total++;
	}

	// adds the new data to the back of the list 
	public void addLast(Wiki data){
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
	}

	public int getTotal(){
		return total;
	}

	public List copyList(){
		List newCopy = new List();
		Node curr = head;

		while(curr != null){
			newCopy.addLast(curr.getData());
			curr = curr.getNext();
		}
		return newCopy;
	}

	
	// search method?
}
