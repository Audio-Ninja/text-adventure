import java.util.Scanner; //Imports scanner for user input.
import java.util.ArrayList;

public class Main {

    static void gameStart() {
        Scanner scan = new Scanner(System.in);
        String response;
        response = scan.nextLine();

        if(response.toLowerCase().equals("yes")) {
            clearScreen();
            System.out.println("Here is how you play:");
            helpScreen();
            goFromHelp();
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
        int endIndex = 8;
        action = scan.nextLine();
        boolean otherAction = false;

        if(action.length() < 8) {
            endIndex = action.length();
        }
        if(action.toLowerCase().equals("end game")) {
            System.out.println("Game ended.");
            scan.close();
            return;
        }
        if(action.toLowerCase().equals("help")) {
            helpScreen();
            otherAction = true;
        }
        if(action.toLowerCase().equals("lol")) {
            if((int) (Math.random() * 2) == 1) {
                lists.inventory.add("a stick");
            } else {
                lists.inventory.add("a sandwich");
            }
            otherAction = true;
        }
        if(action.toLowerCase().equals("bob")) {
            lists.inventory.add("a block of cheese");
            otherAction = true;
        }
        if(action.toLowerCase().equals("i")) {
            if(lists.inventory.size() == 0) {
                System.out.println("You have nothing in your inventory.");
            } else {
                String items = "You have ";
                for(int i = 0; i < lists.inventory.size() - 1; i++) {
                    items += lists.inventory.get(i) + ", ";
                }
                items += "and " + lists.inventory.get(lists.inventory.size() - 1) + ".";
                System.out.println(items);    
            }
            otherAction = true;
        }
        if(otherAction == false) {
            if(action.substring(0, endIndex).toLowerCase().equals("talk to ")) {
                String printText;
                String object = action.substring(8, action.length());
                printText = "Talk to... who?";
                if(object.toLowerCase().equals("chicken")) {
                    printText = "The strange " + mark("chicken") + " turns to you and says, \"Oh! Hello there, traveler from the outer realm!\"";
                }
                System.out.println(printText);
            } else {
                System.out.println("Invalid action. Make sure you spelled it correctly and try again.");
            }    
        }
        
        takeAction(0);
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

    static void goFromHelp() {
        System.out.println("\nType 'ready' when you are ready to proceed.");
        Scanner scan = new Scanner(System.in);
        String response = scan.nextLine();
        if(response.toLowerCase().equals("ready")) {
            clearScreen();
            System.out.println("Darkness.\nYour vision slowly clears, brightening until you can observe your surroundings.\nOver on your right, you see a " + 
            mark("chicken") + "... with four legs? What is this place?");
            takeAction(0);
        } else {
            goFromHelp();
        }
        scan.close();
    }

    public class lists {
        public static ArrayList<String> inventory = new ArrayList<String>();
    }

    public static void main(String[] args) {
        clearScreen();
        System.out.println("Welcome!");
        System.out.println("Do you need to know how to play before you start? (type 'yes' or 'no', then hit enter)");
        gameStart();
    }
}