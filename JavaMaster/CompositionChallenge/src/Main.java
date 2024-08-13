public class Main {
    public static void main(String[] args) {
        SmartKitchen kitchen = new SmartKitchen();

//        kitchen.getIceBox().setHasWorkToDo(true);
//        kitchen.getBrewMaster().setHasWorkToDo(true);
//        kitchen.getDishWasher().setHasWorkToDo(true);
//
//        kitchen.getDishWasher().diDishes();
//        kitchen.getBrewMaster().brewCoffee();
//        kitchen.getIceBox().orderFood();

        kitchen.setKitchenState(true, false, true);
        kitchen.doKitchenWork();

        kitchen.setKitchenState(false, true, false);
        kitchen.doKitchenWork();
    }
}