import java.util.Arrays;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    record Person(String name, String dob, Person[] kids) {

        @Override
        public String toString() {
            return "Person{" +
                    "name='" + name + '\'' +
                    ", kids=" + Arrays.toString(kids) +
                    '}';
        }
    }
    public static void main(String[] args) {

        Person joe = new Person("Joe", "01/01/1961", null);
        Person jim = new Person("Jim", "02/02/1962", null);
        Person jack = new Person("Jack", "03/03/1963", new Person[]{joe, jim});
        Person jane = new Person("Jane", "04/04/1964", null);
        Person jill = new Person("Jill", "05/05/1965", new Person[]{joe, jim});

        Person[] persons = {joe, jim, jack, jane, jill};

        //Shallow Copy
        Person[] personsCopy = Arrays.copyOf(persons, persons.length);

        var jillsKids = personsCopy[4].kids();
        jillsKids[1] = jane;

        System.out.println("Shallow copy modifies the original array");
        for(int i=0; i<5; i++) {
            if(persons[i] == personsCopy[i]) {
                System.out.println("Equal References: " + persons[i]);
            }
        }

        //Deep Copy
        Person[] personsDeepCopy = new Person[5];
        for(int i=0; i< 5; i++) {
            Person current = persons[i];
            var kids = current.kids();

            personsDeepCopy[i] = new Person(current.name(), current.dob(), kids);
        }

        System.out.println("-".repeat(20));
        for(int i=0; i<5; i++) {
            if(persons[i] == personsDeepCopy[i]) {
                System.out.println("Equal References: " + persons[i]);
            } else {
                System.out.println("Nothing printed as it is a Deep copy");
            }
        }

    }
}