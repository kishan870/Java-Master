
//This is part of composition challenge
public class Refrigerator {
    private boolean hasWorkToDo;

    public Refrigerator(boolean hasWorkToDo) {
        this.hasWorkToDo = hasWorkToDo;
    }

    public void OrderFood() {
        if(hasWorkToDo) {
            System.out.println("Order Food is being done");
        }

        else {
            System.out.println("Refrigerator is idle");
        }
    }
}
