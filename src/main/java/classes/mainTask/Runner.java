package classes.mainTask;

public class Runner {

    public static void main(String[] args) {
        Product[] products = {
                new Product(1, ProductName.BREAD, "GomelBreadProm", new Byn(45), 16),
                new Product(2, ProductName.MILK, 30, 6),
                new Product(3, ProductName.MILK, "036000291452",
                        "Belact", new Byn(45), 90, 14),
                new Product(4, ProductName.MILK, "034600870451",
                        "Belact", new Byn(120), 60, 50),
                new Product(5, ProductName.BUTTER, 125, 17)};
        System.out.println("Point 1");
        printProductByName(products, ProductName.MILK);
        System.out.println("Point 2 (price<46)");
        printProductsByNameNotExceedingPrice(products, ProductName.MILK, new Byn(46));
        System.out.println("Point 3(shelfLife>10)");
        printProductsByNameExceedingShelfLife(products, ProductName.MILK, 10);
    }

    private static void printProductByName(Product[] products, ProductName name) {
        for (Product product : products) {
            if (product.getName().equals(name)) {
                System.out.println(product);
            }
        }
    }

    private static void printProductsByNameNotExceedingPrice(Product[] products, ProductName name, Byn price) {
        for (Product product : products) {
            if ((product.getName().equals(name))
                    && (product.getPrice().compareTo(price) <= 0)) {
                System.out.println(product);
            }
        }
    }

    private static void printProductsByNameExceedingShelfLife(Product[] products, ProductName name, int shelfLife) {
        for (Product product : products) {
            if ((product.getName().equals(name))
                    && (product.getShelfLife() > shelfLife)) {
                System.out.println(product);
            }
        }
    }
}
