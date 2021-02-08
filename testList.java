import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class testList {

    @Test 
    void testListCreation() {
        List list = new List();
        System.out.println("Testing that hte list was created (not null).");
        assertNotNull(list,"Hello");
    }

}

