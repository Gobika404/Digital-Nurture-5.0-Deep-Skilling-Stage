# Exercise 2 - E-Commerce Platform Search Function

class Product {

    int id;
    String name;
    String category;

    Product(int id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    void showProduct() {
        System.out.println("Product ID : " + id);
        System.out.println("Product Name : " + name);
        System.out.println("Category : " + category);
    }
}

public class EcommerceSearch {

    public static Product linearSearch(Product[] items, int searchId) {

        for (int i = 0; i < items.length; i++) {
            if (items[i].id == searchId) {
                return items[i];
            }
        }

        return null;
    }

    public static Product binarySearch(Product[] items, int searchId) {

        int low = 0;
        int high = items.length - 1;

        while (low <= high) {

            int middle = (low + high) / 2;

            if (items[middle].id == searchId) {
                return items[middle];
            }

            if (searchId > items[middle].id) {
                low = middle + 1;
            } else {
                high = middle - 1;
            }

        }

        return null;
    }

    public static void main(String[] args) {

        Product[] products = {
                new Product(101, "Laptop", "Electronics"),
                new Product(102, "Keyboard", "Accessories"),
                new Product(103, "Shoes", "Fashion"),
                new Product(104, "Mobile", "Electronics"),
                new Product(105, "Bag", "Travel")
        };

        int searchKey = 104;

        System.out.println("Using Linear Search");

        Product result = linearSearch(products, searchKey);

        if (result != null) {
            result.showProduct();
        } else {
            System.out.println("Product Not Found");
        }

        System.out.println();

        System.out.println("Using Binary Search");

        result = binarySearch(products, searchKey);

        if (result != null) {
            result.showProduct();
        } else {
            System.out.println("Product Not Found");
        }

    }
}
```

#

