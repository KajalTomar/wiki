import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class testList {

    @Test 
    public void testListCreation() {
        List list = new List();
        
        // System.out.println("Testing that the list was created (not null).");
        assertNotNull(list);
    }

    @Test
    public void testAdd() {
        List list = new List();
        User bob = new User("bob",1);

        list.add(bob);

        // System.out.println("Testing adding an item to an empty list.");
        assertEquals(bob, list.getFirstItem());
    }

    @Test
    public void testGetTotalEmpty() {
        List list = new List();
        int total = list.getTotal();

        // System.out.println("Testing the getTotal method when the list is empty.");
        assertEquals(total, 0);        
    }

    @Test
    public void testGetTotalSingle() {
        List list = new List();
        User bob = new User("bob",1);

        int beforeTotal, afterTotal;

        beforeTotal = list.getTotal();
        list.add(bob);
        afterTotal = list.getTotal();

        // System.out.println("Testing getTotal when there is a single item on the list");
        assertEquals(beforeTotal+1, afterTotal;
        assertEquals(1, afterTotal);
    }

    @Test
    public void testGetTotal() {
        List list = new List();
        User bob = new User("bob", 1);
        Document doc1 = new Document("doc1",bob,2);
        Document doc2 = new Document("doc2",bob,3);
        Document doc3 = new Document("doc3",bob,4);

        int total;

        list.add(doc1);
        list.add(doc2);
        list.add(doc3);

        total = list.getTotal();

        // System.out.println("Testing getTotal when there is are three items on the list");
        assertEquals(total, 3);
    }
  
    @Test
    public void testGetFirstItem() {
        List list = new List();
        User user1 = new User("user1",1);
        User user2 = new User("user2",2);

        // System.out.println("Testing getFirstItem (when the list is empty).");
        assertNull(list.getFirstItem());

        list.add(user1);

        // System.out.println("Testing getFirstItem (when there is only one item in the list).");
        assertEquals(list.getFirstItem(),user1);

        list.add(user2);

        // System.out.println("Testing getFirstItem.");
        assertEquals(list.getFirstItem(),user1);
    }

    @Test
    public void testGetLastItem() {
        List list = new List();
        User user1 = new User("user1",1);
        User user2 = new User("user2",2);

        // System.out.println("Testing getLastItem (when the list is empty).");
        assertNull(list.getLastItem());

        list.add(user1);

        // System.out.println("Testing getLastItem (when there is only one item in the list).");
        assertEquals(list.getLastItem(),user1);

        list.add(user2);

        // System.out.println("Testing getLastItem.");
        assertEquals(list.getLastItem(),user2);
    }

    @Test
    public void testGetNextItem() {
        List list = new List();
        User user1 = new User("user1",1);
        User user2 = new User("user2",2);

        // System.out.println("Testing getNextItem (when the list is empty).");
        assertNull(list.getNextItem(user1));

        list.add(user1);

        // System.out.println("Testing getNextItem (when there is only one item in the list).");
        assertNull(list.getNextItem(user1));

        list.add(user2);

        // System.out.println("Testing getNextItem when passing the last item on the list.");
        assertNull(list.getNextItem(user2));

        // System.out.println("Testing getNextItem.");
        assertEquals(list.getNextItem(user1),user2);
    }

    @Test
    public void testDeleteOnlyItem() {
        List list = new List();
        User bob = new User("bob",1);
        
        list.add(bob);
        list.delete(bob);

        // System.out.println("Testing deleting an item (when there is only one item in the list).");
        assertNull(list.getFirstItem());
    }

    @Test
    public void testDeleteFirstItem() {
        List list = new List();
        User user1 = new User("user1",1);
        User user2 = new User("user2",2);

        list.add(user1);
        list.add(user2);
        list.delete(user1);

        // System.out.println("Testing deleting the first item on the list.");
        assertEquals(user2, list.getFirstItem());

    }

    @Test
    public void testDeleteLastItem() {
        List list = new List();
        User user1 = new User("user1",1);
        User user2 = new User("user2",2);

        list.add(user1);
        list.add(user2);
        list.delete(user2);

        // // System.out.println("Testing deleting the last item on the list.");
        assertEquals(user1, list.getLastItem());
    }

    @Test
    public void testDeleteInvalidItem() {
        List list = new List();
        User bob = new User("bob",1);
        User notOnTheList = new User("betty", 2);

        int beforeTotal, afterTotal;

        list.add(bob);

        beforeTotal = list.getTotal();
        list.delete(notOnTheList);
        afterTotal = list.getTotal();

        // // System.out.println("Testing deleting an item that is not on the list.");
        assertEquals(beforeTotal, afterTotal);
    }

}

