import java.util.Scanner; //Imports scanner for user input.

public class Main {

    static void gameStart() {
        Scanner scan = new Scanner(System.in);
        String response;

        response = scan.nextLine();
        if(response.toLowerCase().equals("yes")) {
            clearScreen();
            System.out.println("Here is how you play:");
        } else if (response.toLowerCase().equals("no")) {
            clearScreen();
            System.out.println("There is a" + "\u001B[92m" + " rock " + "\u001B[0m" + "NOoOoO WE SHALL DIE!!");
        } else {
            System.out.println("Invalid response. Try again.");
            gameStart();
        }
        scan.close();
    }

    static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void main(String[] args) {
        System.out.println("Welcome!");
        System.out.println("Do you need to know how to play before you start? (type 'yes' or 'no', then hit enter)");
        gameStart();
    }
}