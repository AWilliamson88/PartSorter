package binarytreewordfinder;

import java.util.Scanner;

public class BinaryTreeWordFinder {
    static AVLTree avlTree = new AVLTree();
    
    
    public static void main(String[] args) {
       prefillName();
       boolean running = true;
       String input;
       Scanner sc = new Scanner(System.in);
       System.out.println("Welcome to binary tree mechanical part finder!");
       while (running) {
           System.out.print("What would you like to do(Display, Search, Add, Delete, Close):> ");
           input = sc.next();
           if (input.equalsIgnoreCase("Display")) {
               avlTree.display();
           } else if (input.equalsIgnoreCase("Search")) {
               System.out.print("What are we searching for?:> ");
               input = sc.next();
               avlTree.find(input);
           } else if (input.equalsIgnoreCase("Add")) {
               System.out.print("What are we adding?:> ");
               input = sc.next();
               avlTree.add(input);
               avlTree.display();
           } else if (input.equalsIgnoreCase("Delete")) {
               System.out.print("What are we deleting?:> ");
               input = sc.next();
               avlTree.delete(input);
           } else if (input.equalsIgnoreCase("Close")) {
               System.out.println("Thanks for using the word finder!");
               running = false;
           }
       }
    }
    
    private static void prefillName() {
        avlTree.add("Screws");
        avlTree.add("Bolts");
        avlTree.add("Nuts");
        avlTree.add("Washers");
        avlTree.add("Rivets");
        avlTree.add("Inserts");
        avlTree.add("Standoffs");
        avlTree.add("Hinges");
        avlTree.add("Latches");
        avlTree.add("Retainers");
    }
}
