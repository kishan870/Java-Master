public class Item {
    private String name;
    private String type;
    private String size = "MEDIUM";
    private double price;

    public Item(String type, String name, double price) {
        this.type = type.toUpperCase();
        this.name = name.toUpperCase();
        this.price = price;
    }

    public double getBasePrice() {
        return price;
    }

    public String getName() {
        if(type.equals("SIDE") || type.equals("DRINK"))
            return size + " " + name;
        return name;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public double getAdjustedPrice() {
        return switch (type) {
            case "SMALL" -> getBasePrice() - 0.5;
            case "BIG" -> getBasePrice() + 1.0;
            default -> getBasePrice();
        };
    }

    public static void printItem(String name, double price) {
        System.out.printf("%20s:%6.2f%n", name, price);
    }

    public void printItem() {
        printItem(getName(), getAdjustedPrice());
    }

}
