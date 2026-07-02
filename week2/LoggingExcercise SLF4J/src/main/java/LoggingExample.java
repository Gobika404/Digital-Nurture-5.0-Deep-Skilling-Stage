import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LoggingExample {

    private static final Logger log = LoggerFactory.getLogger(LoggingExample.class);

    public static void main(String[] args) {

        log.warn("Application execution has started.");

        try {

            int num1 = 20;
            int num2 = 0;

            int result = num1 / num2;

            log.info("Result : {}", result);

        } catch (ArithmeticException ex) {

            log.error("Cannot divide a number by zero.", ex);

        }

        log.warn("Application execution completed.");

    }
}
