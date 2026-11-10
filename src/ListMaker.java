import java.util.ArrayList;
import java.util.Scanner;

public class ListMaker {

    private static ArrayList<String> list = new ArrayList<>();
    private static Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        boolean done = false;

        do {
            displayList();
            displayMenu();
            String cmd = SafeInput.getRegExString(in, "Enter a command", "[AaDdIiPpQq]").toUpperCase();

            switch (cmd) {
                case "A":
                    addItem();
                    break;
                case "D":
                    deleteItem();
                    break;
                case "I":
                    insertItem();
                    break;
                case "P":
                    printList();
                    break;
                case "Q":
                    done = quit();
                    break;
            }
        } while (!done);

        System.out.println("Goodbye!");
    }

    // ---------- menu commands ----------

    // adds an item to the end of the list
    private static void addItem() {
        String item = SafeInput.getNonZeroLenString(in, "Enter the item to add");
        list.add(item);
        System.out.println("Added \"" + item + "\" to the end of the list.");
    }

    // deletes the item the user picks by number
    private static void deleteItem() {
        if (list.isEmpty()) {
            System.out.println("The list is empty. Nothing to delete.");
            return;
        }
        printList();
        int num = SafeInput.getRangedInt(in, "Enter the item number to delete", 1, list.size());
        String removed = list.remove(num - 1);
        System.out.println("Deleted \"" + removed + "\".");
    }

    // inserts an item at the location the user picks
    private static void insertItem() {
        int num = SafeInput.getRangedInt(in, "Enter the location number to insert at", 1, list.size() + 1);
        String item = SafeInput.getNonZeroLenString(in, "Enter the item to insert");
        list.add(num - 1, item);
        System.out.println("Inserted \"" + item + "\" at location " + num + ".");
    }

    // prints the numbered list
    private static void printList() {
        System.out.println();
        System.out.println("------ Current List ------");
        if (list.isEmpty()) {
            System.out.println("(the list is empty)");
        } else {
            for (int i = 0; i < list.size(); i++) {
                System.out.println((i + 1) + ". " + list.get(i));
            }
        }
        System.out.println("--------------------------");
    }

    // asks the user to confirm quitting
    private static boolean quit() {
        return SafeInput.getYNConfirm(in, "Are you sure you want to quit?");
    }

    // ---------- utility methods ----------

    private static void displayList() {
        printList();
    }

    private static void displayMenu() {
        System.out.println("A - Add an item to the list");
        System.out.println("D - Delete an item from the list");
        System.out.println("I - Insert an item into the list");
        System.out.println("P - Print the list");
        System.out.println("Q - Quit the program");
        System.out.println();
    }
}