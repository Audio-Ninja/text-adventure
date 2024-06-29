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
            System.out.println("Darkness.\nYour vision slowly clears, brightening until you can observe your surroundings.\nWalking by in front of you, you see a " + 
            mark("chicken") + "... with four legs? What is this place?");
            takeAction(0);
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

    static void takeAction(int place) {
        Scanner scan = new Scanner(System.in);
        String action;
        action = scan.nextLine();

        if(action.toLowerCase().equals("end game")) {
            System.out.println("Game ended.");
            scan.close();
            return;
        }
        if(action.length() > 7 && action.substring(0, 8).toLowerCase().equals("talk to ")) {
            String printText;
            String object = action.substring(8, action.length());
            printText = "Unfortunatly, you don't know how to talk to the " + object;
            if(object.toLowerCase().equals("chicken")) {
                printText = "The weird " + mark("chicken") + " looks at you and says, \"Bawk baaaawk cluck cluck!\"";
            }
            System.out.println(printText);
            takeAction(0);
        } else {
            System.out.println("Invalid action. Make sure you spelled it correctly and try again.");
            takeAction(1);
        }
        scan.close();
    }

    public static String mark(String text) {
        return "\u001B[96m" + text + "\u001B[0m";
    }

    public static void main(String[] args) {
        clearScreen();
        System.out.println("Welcome!");
        System.out.println("Do you need to know how to play before you start? (type 'yes' or 'no', then hit enter)");
        gameStart();
    }
}