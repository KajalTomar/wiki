import java.util.*;

public class List{
	private Node head; 
	private Node tail;

	public List(){
		head = null;
		tail = null;
	}
	
	public void print(){
		if (head.isUser()){
			System.out.print("USERS: [ ");
		} else {
			System.out.println("-------------");
		}

		// prints the list starting from the head node
		if(head!=null){
			head.print();
		}
		
		if (head.isUser()){
			System.out.print(" ] \n");
		} else {
			System.out.println("-------------");
		}
	}

	// adds the new data to the front of the list 
	public void addFront(Wiki data){
		head = new Node(data, head); 
	}

	public void append(Wiki data){
		Node newNode; 

		if(head == null){
			tail = new Node(data, head);
			head = tail; 
		} else {
			newNode = new Node(data, null);
			tail.setNext(newNode);
			tail = newNode;
		}
	}

	
	// search method?
}
