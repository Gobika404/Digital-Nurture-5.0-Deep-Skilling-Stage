import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CalculatorTest {

    @Test
    public void testAdd() {

        Calculator c = new Calculator();

        int result = c.add(10,20);

        assertEquals(30,result);
    }

}
