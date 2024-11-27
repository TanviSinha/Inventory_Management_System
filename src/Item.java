public class Item {
    private String id;
    private String name;
    private String category;
    private int quantity;

    public Item(String id, String name, String category, int quantity) {
        this.id=id;
        this.name=name;
        this.category=category;
        this.quantity=quantity;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getCategory() {
        return category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity=quantity;
    }

    @Override
    public String toString() {
        return "Item{id='"+id+ "', name='"+name+"', category='"+category+"', quantity="+quantity+"}";
    }
}
