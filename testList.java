import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class testList {

    @Test 
    public void testListCreation() {
        List list = new List();
        
        System.out.println("Testing that the list was created (not null).");
        assertNotNull(list, "should not be null.");
    }

    @Test
    public void testAdd() {
        List list = new List();
        User bob = new User("bob",1);

        list.add(bob);

        System.out.println("Testing adding an item to an empty list.");
        assertEquals(bob, list.getFirstItem(),"user should equal the first item on the list.");
    }

    @Test
    public void testGetTotalEmpty() {
        List list = new List();
        int total = list.getTotal();

        System.out.println("Testing the getTotal method when the list is empty.");
        assertEquals(total, 0,"getTotal should return 0 when the list is empty.");        
    }

    @Test
    public void testGetTotalSingle() {
        List list = new List();
        User bob = new User("bob",1);

        int beforeTotal, afterTotal;

        beforeTotal = list.getTotal();
        list.add(bob);
        afterTotal = list.getTotal();

        System.out.println("Testing getTotal when there is a single item on the list");
        assertEquals(beforeTotal+1, afterTotal,"The total number of items on the list should be increase by exactly 1.");
        assertEquals(1, afterTotal,"The total number of items on the list should be exactly 1.");
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

        System.out.println("Testing getTotal when there is are three items on the list");
        assertEquals(total, 3,"The total number of items on the list should be exactly 3.");
    }
  
    @Test
    public void testGetFirstItem() {
        List list = new List();
        User user1 = new User("user1",1);
        User user2 = new User("user2",2);

        System.out.println("Testing getFirstItem (when the list is empty).");
        assertNull(list.getFirstItem(),"first item should equal null.");

        list.add(user1);

        System.out.println("Testing getFirstItem (when there is only one item in the list).");
        assertEquals(list.getFirstItem(),user1,"first item should equal user1.");

        list.add(user2);

        System.out.println("Testing getFirstItem.");
        assertEquals(list.getFirstItem(),user1,"first item should still equal user1.");
    }

    @Test
    public void testGetLastItem() {
        List list = new List();
        User user1 = new User("user1",1);
        User user2 = new User("user2",2);

        System.out.println("Testing getLastItem (when the list is empty).");
        assertNull(list.getLastItem(),"last item should equal null.");

        list.add(user1);

        System.out.println("Testing getLastItem (when there is only one item in the list).");
        assertEquals(list.getLastItem(),user1,"last item should equal user1.");

        list.add(user2);

        System.out.println("Testing getLastItem.");
        assertEquals(list.getLastItem(),user2,"last item should still equal user2.");
    }

    @Test
    public void testGetNextItem() {
        List list = new List();
        User user1 = new User("user1",1);
        User user2 = new User("user2",2);

        System.out.println("Testing getNextItem (when the list is empty).");
        assertNull(list.getNextItem(user1),"next item should equal null.");

        list.add(user1);

        System.out.println("Testing getNextItem (when there is only one item in the list).");
        assertNull(list.getNextItem(user1),"next item should equal null.");

        list.add(user2);

        System.out.println("Testing getNextItem when passing the last item on the list.");
        assertNull(list.getNextItem(user2),"next item should equal null.");

        System.out.println("Testing getNextItem.");
        assertEquals(list.getNextItem(user1),user2,"next item should still equal user2.");
    }

    @Test
    public void testDeleteOnlyItem() {
        List list = new List();
        User bob = new User("bob",1);
        
        list.add(bob);
        list.delete(bob);

        System.out.println("Testing deleting an item (when there is only one item in the list).");
        assertNull(list.getFirstItem(), "should be null.");
    }

    @Test
    public void testDeleteFirstItem() {
        List list = new List();
        User user1 = new User("user1",1);
        User user2 = new User("user2",2);

        list.add(user1);
        list.add(user2);
        list.delete(user1);

        System.out.println("Testing deleting the first item on the list.");
        assertEquals(user2, list.getFirstItem(),"user2 should equal the first item on the list.");

    }

    @Test
    public void testDeleteLastItem() {
        List list = new List();
        User user1 = new User("user1",1);
        User user2 = new User("user2",2);

        list.add(user1);
        list.add(user2);
        list.delete(user2);

        System.out.println("Testing deleting the last item on the list.");
        assertEquals(user1, list.getLastItem(),"user1 should equal the last item on the list.");
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

        System.out.println("Testing deleting an item that is not on the list.");
        assertEquals(beforeTotal, afterTotal,"The total number of items on the list should be unchanged.");
    }

}

