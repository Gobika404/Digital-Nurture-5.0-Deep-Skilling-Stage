# Exercise 1 - Singleton Pattern

class Logger {

    private static Logger loggerObject;

    private Logger() {
        System.out.println("Logger Initialized");
    }

    public static Logger getLogger() {

        if (loggerObject == null) {
            loggerObject = new Logger();
        }

        return loggerObject;
    }

    public void writeLog(String message) {
        System.out.println("Log : " + message);
    }
}

public class SingletonPatternExample {

    public static void main(String[] args) {

        Logger firstLogger = Logger.getLogger();
        Logger secondLogger = Logger.getLogger();

        firstLogger.writeLog("Application Started");

        if (firstLogger == secondLogger) {
            System.out.println("Only one Logger object exists.");
        } else {
            System.out.println("Multiple Logger objects created.");
        }

    }
}

