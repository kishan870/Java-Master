import dev.lpa.AdventureGame;

import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        AdventureGame game = new AdventureGame();
        game.play("road");

        Scanner scanner = new Scanner(System.in);

        while (true) {

            String direction = scanner.nextLine().trim().toUpperCase().substring(0, 1);
            if(direction.equals("Q")) {
                System.out.println("Thanks!! Play again!");
                break;
            }
            game.move(direction);
        }
    }
}