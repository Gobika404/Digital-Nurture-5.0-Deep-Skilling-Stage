import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class CalculatorAAATest {

    Calculator c;

    @Before
    public void setUp() {

        c = new Calculator();

        System.out.println("Before Test");

    }

    @After
    public void tearDown() {

        System.out.println("After Test");

    }

    @Test
    public void testMultiply() {

        // Arrange
        int a = 5;
        int b = 4;

        // Act
        int result = c.multiply(a,b);

        // Assert
        assertEquals(20,result);

    }

}
