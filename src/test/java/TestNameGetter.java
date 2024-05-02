import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.example.NameGetter;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestNameGetter {


    @DisplayName("Test Greeting")
    @Test
    void testReturnGreeting(){
        NameGetter nameGetter = new NameGetter();

        nameGetter.setName("Sean");

        assertEquals("Hello Sean!", nameGetter.getGreeting());

    }
}
