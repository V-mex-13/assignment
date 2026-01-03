package com.inventory;

import java.util.ArrayList;
import java.util.Scanner;


class Product {
    int productId;
    String productName;
    double productPrice;
    int productQuantity;
    String productCompany;

    public Product(int productId, String productName, double productPrice, int productQuantity, String productCompany){
        this.productId = productId;
        this.productName = productName;
        this.productPrice = productPrice;
        this.productQuantity = productQuantity;
        this.productCompany = productCompany;
    }

    public void displayProduct(){
        System.out.println("--------------------------------------------------");
        System.out.println("Product ID       : " + productId);
        System.out.println("Product Name     : " + productName);
        System.out.println("Product Price    : " + productPrice);
        System.out.println("Product Quantity : " + productQuantity);
        System.out.println("Product Company  : " + productCompany);
        System.out.println("--------------------------------------------------");
    }
}


public class InventoryManagement {

    static ArrayList<Product> product_list = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);


    public static void addProduct() {
        System.out.println("\n=== Add New Product ===");

        System.out.print("Enter Product ID: ");
        int id = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Product Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Product Price: ");
        double price = sc.nextDouble();

        System.out.print("Enter Product Quantity: ");
        int qty = sc.nextInt();
        sc.nextLine();

        System.out.print("Enter Product Company: ");
        String company = sc.nextLine();

        product_list.add(new Product(id, name, price, qty, company));
        System.out.println("✅ Product Added Successfully!");
    }


    public static void viewProducts() {
        System.out.println("\n=== List of Products ===");
        if (product_list.isEmpty()) {
            System.out.println("No products available!");
            return;
        }
        for (Product p : product_list) {
            p.displayProduct();
        }
    }


    public static void searchProduct() {
        System.out.print("\nEnter Product ID to Search: ");
        int id = sc.nextInt();

        for (Product p : product_list) {
            if (p.productId == id) {
                System.out.println("✅ Product Found!");
                p.displayProduct();
                return;
            }
        }
        System.out.println("❌ Product Not Found!");
    }


    public static void editProduct() {
        System.out.print("\nEnter Product ID to Edit: ");
        int id = sc.nextInt();
        sc.nextLine();

        for (Product p : product_list) {
            if (p.productId == id) {
                System.out.println("Enter New Product Name: ");
                p.productName = sc.nextLine();
                System.out.println("Enter New Product Price: ");
                p.productPrice = sc.nextDouble();
                System.out.println("Enter New Product Quantity: ");
                p.productQuantity = sc.nextInt();
                sc.nextLine();
                System.out.println("Enter New Product Company: ");
                p.productCompany = sc.nextLine();

                System.out.println("✅ Product Updated Successfully!");
                return;
            }
        }
        System.out.println("Product Not Found");
    }


    public static void deleteProduct() {
        System.out.print("\nEnter Product ID to Delete: ");
        int id = sc.nextInt();

        for (Product p : product_list) {
            if (p.productId == id) {
                product_list.remove(p);
                System.out.println("Product Deleted Successfully");
                return;
            }
        }
        System.out.println("Product Not Found");
    }


    public static void main(String[] args) {
        while (true) {
            System.out.println("\n===== Inventory Management Menu =====");
            System.out.println("1. Add Stock");
            System.out.println("2. View Stock");
            System.out.println("3. Search Stock");
            System.out.println("4. Edit Stock");
            System.out.println("5. Delete Stock");
            System.out.println("6. Exit");
            System.out.print("Enter Your Choice: ");
            int choice = sc.nextInt();

            switch (choice) {
                case 1: addProduct(); break;
                case 2: viewProducts(); break;
                case 3: searchProduct(); break;
                case 4: editProduct(); break;
                case 5: deleteProduct(); break;
                case 6: System.out.println("Thank You"); System.exit(0);
                default: System.out.println("Invalid Input Try Again");
            }
        }
    }
}

