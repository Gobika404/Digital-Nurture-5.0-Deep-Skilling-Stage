Exercise 3 - Builder Pattern
class Computer {

    private String processor;
    private int memory;
    private int storage;
    private boolean graphicsCard;

    private Computer(Builder builder) {
        this.processor = builder.processor;
        this.memory = builder.memory;
        this.storage = builder.storage;
        this.graphicsCard = builder.graphicsCard;
    }

    @Override
    public String toString() {

        return "Processor : " + processor +
               "\nRAM : " + memory + " GB" +
               "\nStorage : " + storage + " GB" +
               "\nGraphics Card : " + graphicsCard;

    }

    static class Builder {

        private String processor;
        private int memory;
        private int storage;
        private boolean graphicsCard;

        Builder(String processor, int memory) {
            this.processor = processor;
            this.memory = memory;
        }

        Builder setStorage(int storage) {
            this.storage = storage;
            return this;
        }

        Builder setGraphicsCard(boolean graphicsCard) {
            this.graphicsCard = graphicsCard;
            return this;
        }

        Computer build() {
            return new Computer(this);
        }

    }
}

public class BuilderPatternExample {

    public static void main(String[] args) {

        Computer gamingPC = new Computer.Builder("Intel i7", 16)
                .setStorage(512)
                .setGraphicsCard(true)
                .build();

        Computer officePC = new Computer.Builder("Intel i5", 8)
                .setStorage(256)
                .setGraphicsCard(false)
                .build();

        System.out.println("Gaming Computer");
        System.out.println(gamingPC);

        System.out.println();

        System.out.println("Office Computer");
        System.out.println(officePC);

    }
}
