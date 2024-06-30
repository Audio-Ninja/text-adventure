import java.util.Scanner; //Imports scanner for user input.

public class Main {

    static void gameStart() {
        Scanner scan = new Scanner(System.in);
        String response;
        response = scan.nextLine();

        if(response.toLowerCase().equals("yes")) {
            clearScreen();
            System.out.println("Here is how you play:");
            helpScreen();
        } else if (response.toLowerCase().equals("no")) {
            clearScreen();
            System.out.println("Darkness.\nYour vision slowly clears, brightening until you can observe your surroundings.\nOver on your right, you see a " + 
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
        if(action.toLowerCase().equals("help")) {
            helpScreen();
            scan.close();
            return;
        }
        if(action.length() > 7 && action.substring(0, 8).toLowerCase().equals("talk to ")) {
            String printText;
            String object = action.substring(8, action.length());
            printText = "Unfortunatly, you don't know how to talk to the " + object;
            if(object.toLowerCase().equals("chicken")) {
                printText = "The strange " + mark("chicken") + " turns to you and says, \"Oh! Hello there, traveler from the outer realm!\"";
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

    static void helpScreen() {
        System.out.println("You play the game by entering actions based on your situation. The actions you can take are:");
        System.out.println("talk to, look at, walk to, pick up, push, give, use, and use item on.\nObjects or entities you can interact with are " +
        mark("highlighted") + ". \nFor example, you are in a place with a homeless " + mark("man") + " sitting by a big " + mark("stick") +
        ". There is also a " + mark("path") + " leading off from your left. You have an " + mark("axe") + " and a loaf of " +
        mark("bread") + " in your inventory.\nIn this situation, here are some actions you could type:\ntalk to man\nwalk to path\npush man" +
        "\ngive bread to man\nuse bread\nuse axe on stick\n\nYou can also type " + mark("quests") + " to see your current and completed quests, " +
        mark("i") + " to view your inventory, " + mark("end game") + " to end the game, and " + mark("help") + " to view this how to play section again.");
    }

    public static void main(String[] args) {
        clearScreen();
        System.out.println("Welcome!");
        System.out.println("Do you need to know how to play before you start? (type 'yes' or 'no', then hit enter)");
        gameStart();
    }
}