import java.util.Scanner; //Imports scanner for user input.
import java.util.ArrayList;

public class Main {

    static void gameStart() {
        Scanner scan = new Scanner(System.in);
        String response;
        response = scan.nextLine();

        if(response.toLowerCase().equals("yes")) {
            clearScreen();
            helpScreen();
            goFromHelp();
        } else if (response.toLowerCase().equals("no")) {
            clearScreen();
            System.out.println("Darkness.\nYour vision slowly clears, brightening until you can observe your surroundings. You find yourself " + 
            "on a dirt path amidst a grassy plain. Ahead of you, the path forks away in multiple directions. \nOver on your right, you see a " + 
            mark("rooster") + "... with four legs? What is this place?");
            takeAction();
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

    static void takeAction() {
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
            System.out.println();
            helpScreen();
            otherAction = true;
        }
        if(action.toLowerCase().equals("chez")) {
            player.inventory.add("a block of " + mark("cheese"));
            otherAction = true;
        }
        if(action.toLowerCase().equals("i")) {
            if(player.inventory.size() == 0) {
                System.out.println("You have nothing in your inventory.");
            } else {
                String items = "You have ";
                for(int i = 0; i < player.inventory.size() - 1; i++) {
                    items += player.inventory.get(i) + ", ";
                }
                items += "and " + player.inventory.get(player.inventory.size() - 1) + ".";
                System.out.println(items);    
            }
            otherAction = true;
        }
        if(action.toLowerCase().equals("quests")) {
            if(player.quests.size() == 0) {
                System.out.println("You have no current quests.");
            } else {
                String quests = "Your current quests: ";
                for(int i = 0; i < player.quests.size() - 1; i++) {
                    quests += player.quests.get(i) + ", ";
                }
                quests += "and " + player.quests.get(player.quests.size() - 1) + ".";
                System.out.println(quests);    
            }
            if(player.completedQuests.size() == 0) {
                System.out.println("You have not completed any quests.");
            } else {
                String quests = "Your completed quests: ";
                for(int i = 0; i < player.completedQuests.size() - 1; i++) {
                    quests += player.completedQuests.get(i) + ", ";
                }
                quests += "and " + player.completedQuests.get(player.completedQuests.size() - 1) + ".";
                System.out.println(quests);    
            }
            otherAction = true;
        }
        if(otherAction == false) {
            if(action.substring(0, endIndex).toLowerCase().equals("talk to ")) {
                String printText;
                String object = action.substring(8, action.length());
                printText = "Talk to... who?";
                if(object.toLowerCase().equals("rooster")) {
                    printText = "The strange " + mark("rooster") + " turns to you and cocks his head. \"Oh! Hello there, can I help you?\"\n" +
                    "\"Where am I?\" You ask. \"How do I get back home?\"\nThe rooster's expression shifts to what you think is confusion. \"Uh... " + 
                    "I didn't expect that question already... but the sorcerer should be able to send you back to your realm.\" He points a wing to where\n" + 
                    "the path branches off toward some " + mark("mountains") + " to your left.\n\"He lives up there. And don't worry, I've heard he's a good fellow.\"";
                }
                System.out.println(printText);
            } else {
                System.out.println("Invalid action. Make sure you spelled it correctly and try again.");
            }    
        }
        
        takeAction();
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
        mark("i") + " to view your inventory, " + mark("end game") + " to end the game, and " + mark("help") + " to view this how to play section again.\n");
    }

    static void goFromHelp() {
        System.out.println("Type 'ready' when you are ready to proceed.");
        Scanner scan = new Scanner(System.in);
        String response = scan.nextLine();
        if(response.toLowerCase().equals("ready")) {
            clearScreen();
            System.out.println("Darkness.\nYour vision slowly clears, brightening until you can observe your surroundings. You find yourself " + 
            "on a dirt path amidst a grassy plain. Ahead of you, the path forks away in multiple directions. \nOver on your right, you see a " + 
            mark("rooster") + "... with four legs? What is this place?");
            takeAction();
        } else {
            goFromHelp();
        }
        scan.close();
    }

    public class player {
        public static String place = "Grassy Plain";
        public static ArrayList<String> inventory = new ArrayList<String>();
        public static ArrayList<String> quests = new ArrayList<String>();
        public static ArrayList<String> completedQuests = new ArrayList<String>();
    }

    public static void main(String[] args) {
        clearScreen();
        System.out.println("Welcome!");
        System.out.println("Do you need to know how to play before you start? (type 'yes' or 'no', then hit enter)");
        gameStart();
    }
}