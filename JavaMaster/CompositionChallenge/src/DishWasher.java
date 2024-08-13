public class DishWasher {
    private boolean hasWorkToDo;

    public void setHasWorkToDo(boolean hasWorkToDo) {
        this.hasWorkToDo = hasWorkToDo;
    }

    public void diDishes() {
        if(hasWorkToDo) {
            System.out.println("Washing dishes....");
            hasWorkToDo = false;
        }
    }
}