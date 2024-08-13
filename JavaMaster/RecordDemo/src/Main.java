public class Main {
    public static void main(String[] args) {
        for(int i=1; i<=5; i++) {
            LPAStudent student = new LPAStudent("S92300" +i,
                    switch(i) {
                        case 1 -> "Mary";
                        case 2 -> "Carol";
                        case 3 -> "Tim";
                        case 4 -> "Harry";
                        case 5 -> "Lisa";
                        default -> "Anonymous";
                    },
                    "03/11/1996",
                    "Java.Masterclass");

            System.out.println(student);
        }

        LPAStudent student = new LPAStudent("S92306", "Bill", "09/11/1997", "Java MasterClass");
        System.out.println(student);

        //Java records use the same name as fields in place of getter methods
        //Ex: student.id() == student.getId()
        System.out.println(student.id() + " " + student.name() + " " + student.dateOfBirth() + " " + student.classList());
    }
}