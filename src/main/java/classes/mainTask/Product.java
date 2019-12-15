package classes.mainTask;

public class Product {
    private int id;
    private ProductName name;
    private String universalProductCode;
    private String manufacturer;
    private Byn price;
    private int shelfLife;
    private int quantity;

    public Product(int id, ProductName name, String manufacturer, Byn price, int quantity) {
        this.id = id;
        this.name = name;
        this.universalProductCode = "000000000000";
        this.manufacturer = manufacturer;
        this.price = price;
        this.shelfLife = 5;
        this.quantity=quantity;
    }

    public Product(int id, ProductName name, int price, int quantity) {
        this.id = id;
        this.name = name;
        this.manufacturer = "-";
        this.price = new Byn(price);
        this.shelfLife = 5;
        this.quantity = quantity;
    }

    public Product(int id, ProductName name, String universalProductCode, String manufacturer, Byn price, int shelfLife, int quantity) {
        this.id = id;
        this.name = name;
        this.universalProductCode = universalProductCode;
        this.manufacturer = manufacturer;
        this.price = price;
        this.shelfLife = shelfLife;
        this.quantity = quantity;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setName(ProductName name) {
        this.name = name;
    }

    public void setUniversalProductCode(String universalProductCode) {
        this.universalProductCode = universalProductCode;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public void setPrice(int price) {
        this.price = new Byn(price);
    }

    public void setShelfLife(int shelfLife) {
        this.shelfLife = shelfLife;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getId() {
        return id;
    }

    public ProductName getName() {
        return name;
    }

    public String getUniversalProductCode() {
        return universalProductCode;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public Byn getPrice() {
        return price;
    }

    public int getShelfLife() {
        return shelfLife;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return String.format("%s, manufacturer=%s, price=%s, shelf life=%d days, quantity=%d",
                name.getTitle(), manufacturer, price, shelfLife, quantity);
    }
}
