public class Main {
    public static void main(String[] args) {
        for(int i=1; i<=5; i++) {
            Student student = new Student("S92300" +i,
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
    }
}