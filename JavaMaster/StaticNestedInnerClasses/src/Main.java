import domain.Employee;
import domain.EmployeeComparator;
import domain.StoreEmployee;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List<Employee> employees = new ArrayList<>(List.of(
                new Employee(10001, "Ralph", 2015),
                new Employee(10005, "Carol", 2021),
                new Employee(10022, "Jane", 2013),
                new Employee(13551, "Laura", 2020),
                new Employee(10050, "Jim", 2018)
        ));

//        var comparator = new EmployeeComparator<>();
//        employees.sort(comparator);

//        printEmployees("Before soring", employees);
//
//        employees.sort(new Employee.EmployeeComparator<>());
//
//        printEmployees("Sorting based on name", employees);
//
//        employees.sort(new Employee.EmployeeComparator<>("yearStarted"));
//
//        printEmployees("Sorting based on year started", employees);
//
//        employees.sort(new Employee.EmployeeComparator<>("yearstarted")
//                .reversed());
//
//        printEmployees("Reverse Sorting based on year started", employees);

        System.out.println("Store Employees");

        List<StoreEmployee> storeEmployees = new ArrayList<>(List.of(
                new StoreEmployee(10015, "Meg", 2019, "Target"),
                new StoreEmployee(10515, "Joe", 2021, "Walmart"),
                new StoreEmployee(10105, "Tom", 2020, "Macys"),
                new StoreEmployee(10215, "Marty", 2018, "Walmart"),
                new StoreEmployee(10322, "Bud", 2016, "Target")
        ));

        printEmployees("Store employees unsorted", storeEmployees);

        storeEmployees.sort(new StoreEmployee.EmployeeComparator<>("yearStarted"));

        printEmployees("Store employees after sorting", storeEmployees);

//        var genericEmployee = new StoreEmployee();
//        storeEmployees.sort(genericEmployee.new StoreComparator<>());

        storeEmployees.sort(new StoreEmployee().new StoreComparator<>());

        printEmployees("Using Store Comparator", storeEmployees);

        System.out.println("With pig latin names");
        addPigLatinName(storeEmployees);

    }

    public static <T extends Employee> void printEmployees(String message, List<T> employees) {

        System.out.println(message);

        for(Employee e: employees) {
            System.out.println(e);
        }

        System.out.println();
    }

    public static void addPigLatinName(List<? extends StoreEmployee> list) {

        String lastName = "Piggy";

        class DecoratedEmployee extends StoreEmployee implements
            Comparable<DecoratedEmployee> {

            private String pigLatinName;
            private Employee originalInstance;

            public DecoratedEmployee(String pigLatinName, Employee originalInstance) {
                this.pigLatinName = pigLatinName + " " + lastName;
                this.originalInstance = originalInstance;
            }

            @Override
            public String toString() {
                return originalInstance.toString() + " " + pigLatinName;
            }

            @Override
            public int compareTo(DecoratedEmployee o) {
                return this.pigLatinName.compareTo(o.pigLatinName);
            }
        }// End of DecoratedEmployee class

        List<DecoratedEmployee> newList = new ArrayList<>(list.size());

        for(var employee: list) {
            String name = employee.getName();
            String pigLatin = name.substring(1) + name.charAt(0) + "ay";

            newList.add(new DecoratedEmployee(pigLatin, employee));
        }

        newList.sort(null);
        for(var dEmployee: newList) {
            System.out.println(dEmployee.originalInstance.getName() + " " +
                    dEmployee.pigLatinName);
        }
    }
}