import java.util.*;

public class Main {
    public static void main(String[] args) {
        InventoryManager manager=new InventoryManager();
        Scanner scanner=new Scanner(System.in);

        System.out.println("Welcome to the Inventory Management System!");

        while(true) {
            System.out.println("\nSelect an operation:");
            System.out.println("1. Add Item to Inventory");
            System.out.println("2. Set Restocking Threshold of an Item");
            System.out.println("3. Check Restocking of Items");
            System.out.println("4. Filter Items by Category");
            System.out.println("5. Merge Inventories");
            System.out.println("6. Search Item by ID");
            System.out.println("7. Delete Item from Inventory");
            System.out.println("8. Get Top Items in Inventory");
            System.out.println("9. Display Inventory");
            System.out.println("10. Exit");
            System.out.print(">> ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch(choice) {
                case 1:
                    System.out.print("Enter item ID: ");
                    String id=scanner.nextLine();
                    System.out.print("Enter item name: ");
                    String name=scanner.nextLine();
                    System.out.print("Enter item category: ");
                    String category=scanner.nextLine();
                    System.out.print("Enter item quantity: ");
                    int quantity=scanner.nextInt();
                    manager.addItem(id, name, category, quantity);
                    break;

                case 2:
                    System.out.print("Enter item ID: ");
                    String thresholdId=scanner.nextLine();
                    System.out.print("Enter restocking threshold of the item: ");
                    int threshold=scanner.nextInt();
                    manager.setRestockingThreshold(thresholdId, threshold);
                    break;

                case 3:
                    manager.checkRestocking();
                    break;

                case 4:
                    System.out.print("Enter category to filter by: ");
                    String filterCategory=scanner.nextLine();
                    manager.filterByCategory(filterCategory);
                    break;

                case 5:
                    System.out.print("Enter the number of new items to merge: ");
                    int numMergeItems=scanner.nextInt();
                    scanner.nextLine();
                    List<Item> newItems=new ArrayList<>();
                    for (int i=0; i<numMergeItems; i++) {
                        System.out.print("Enter item ID: ");
                        String mergeId=scanner.nextLine();
                        System.out.print("Enter item name: ");
                        String mergeName=scanner.nextLine();
                        System.out.print("Enter item category: ");
                        String mergeCategory=scanner.nextLine();
                        System.out.print("Enter item quantity: ");
                        int mergeQuantity=scanner.nextInt();
                        scanner.nextLine();
                        newItems.add(new Item(mergeId, mergeName, mergeCategory, mergeQuantity));
                    }
                    manager.mergeInventory(newItems);
                    break;

                case 6:
                    System.out.print("Enter item ID to search: ");
                    String searchId=scanner.nextLine();
                    manager.searchItemById(searchId);
                    break;

                case 7:
                    System.out.print("Enter item ID to delete: ");
                    String deleteId=scanner.nextLine();
                    manager.deleteItem(deleteId);
                    break;

                case 8:
                    System.out.print("Enter number of top items to display: ");
                    int topN=scanner.nextInt();
                    manager.getTopItems(topN);
                    break;

                case 9:
                    manager.displayInventory();
                    break;

                case 10:
                    System.out.println("Exiting the Inventory Management System.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice! Please Enter a Valid Input.");
            }
        }
    }
}
