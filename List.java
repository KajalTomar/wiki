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
		
	public Entity getFirstItem(){
		Entity firstItem = null;

		if(head != null){
			firstItem = head.getData();
		}

		return firstItem;
	}

	public Entity getLastItem(){
		Entity lastItem = null;

		if(tail != null){
			lastItem = tail.getData();
		}

		return lastItem;
	}
	

	public Entity getNextItem(Entity item){
		Node foundAt = null;
		Entity entity = null;

		foundAt = search(item);

		if(foundAt.getNext() != null){
			entity = foundAt.getNext().getData();
		}

		return entity;
	}

	private Node search(Entity item){
		Node curr = head;
		Node foundNode = null;
		boolean found = false;

		while(curr != null && !found){

			if(curr.getData() == item){
				foundNode = curr;
				found = true;
			}
				curr = curr.getNext();
		}

		return foundNode;
	}


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

	public void delete(Entity deleteEntity){
		Node deleteMe = search(deleteEntity);
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
