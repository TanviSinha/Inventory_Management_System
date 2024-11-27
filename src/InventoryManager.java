import java.util.*;
import java.util.stream.Collectors;

public class InventoryManager {
    private final Map<String, Item> inventory=new HashMap<>();
    private final Map<String, Integer> thresholds=new HashMap<>();

    public void addItem(String id, String name, String category, int quantity) {
        if (inventory.containsKey(id)) {
            Item existingItem=inventory.get(id);
            if (existingItem.getName().equalsIgnoreCase(name) &&
                    existingItem.getCategory().equalsIgnoreCase(category)) {
                System.out.println("Item with ID "+id+" already exists. Updating the quantity.");
                existingItem.setQuantity(existingItem.getQuantity() + quantity);
            } else {
                System.out.println("Item with ID "+id+" already exists but does not match the name or category.");
            }
        } else {
            inventory.put(id, new Item(id, name, category, quantity));
        }
    }

    public void deleteItem(String id) {
        if (inventory.containsKey(id)) {
            inventory.remove(id);
            System.out.println("Item with ID "+id+" has been removed.");
        } else {
            System.out.println("No item found with ID "+id+".");
        }
    }

    public void searchItemById(String id) {
        if (inventory.containsKey(id)) {
            System.out.println("Found item: "+inventory.get(id));
        } else {
            System.out.println("No item found with ID "+id+".");
        }
    }


    public void setRestockingThreshold(String id, int threshold) {
        thresholds.put(id, threshold);
    }

    public void checkRestocking() {
        System.out.println("Restocking notifications:");
        for (Map.Entry<String, Item> entry:inventory.entrySet()) {
            Item item=entry.getValue();
            int threshold=thresholds.getOrDefault(item.getId(), Integer.MAX_VALUE);
            if (item.getQuantity()<threshold) {
                System.out.println("Item "+item.getName()+" (ID: " +item.getId()+ ") is below the threshold.");
            }
        }
    }

    public void filterByCategory(String category) {
        List<Item> filtered=inventory.values().stream()
                .filter(item->item.getCategory().equalsIgnoreCase(category))
                .sorted((i1, i2)->Integer.compare(i2.getQuantity(), i1.getQuantity()))
                .collect(Collectors.toList());
        System.out.println("Items in category '" +category+"': "+filtered);
    }

    public void mergeInventory(List<Item> newItems) {
        for (Item newItem:newItems) {
            if (inventory.containsKey(newItem.getId())) {
                Item existingItem=inventory.get(newItem.getId());
                if (newItem.getQuantity()>existingItem.getQuantity()) {
                    existingItem.setQuantity(newItem.getQuantity());
                }
            } else {
                inventory.put(newItem.getId(), newItem);
            }
        }
    }

    public void getTopItems(int n) {
        List<Item> sorted = inventory.values().stream()
                .sorted((i1, i2)->Integer.compare(i2.getQuantity(), i1.getQuantity()))
                .limit(n)
                .collect(Collectors.toList());
        System.out.println("Top "+n+" items by quantity: "+sorted);
    }

    public void displayInventory() {
        System.out.println("Current inventory: "+inventory.values());
    }
}
