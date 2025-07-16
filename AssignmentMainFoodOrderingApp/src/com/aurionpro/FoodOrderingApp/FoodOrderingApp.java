package com.aurionpro.FoodOrderingApp;

import java.util.Scanner;

import com.aurionpro.Interfaces.IDiscountCalculator;
import com.aurionpro.Interfaces.IFoodItem;
import com.aurionpro.Interfaces.IPayment;
import com.aurionpro.exceptions.EmptyOrderException;
import com.aurionpro.exceptions.InvalidPriceException;
import com.aurionpro.exceptions.InvalidQuantityException;
import com.aurionpro.exceptions.ItemNotFoundException;
import com.aurionpro.model.*;

public class FoodOrderingApp {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Menu menu = new Menu();
        menu.initialize();

        DeliveryManager deliveryManager = new DeliveryManager();
        deliveryManager.initialize();

        boolean exit = false;
        Admin admin = new Admin();
        IDiscountCalculator discountCalc = new FlatDiscount(500);


        while (!exit) {
            System.out.println("\n====== WELCOME TO FOOD ORDERING APP ======");
            System.out.println("1. Admin");
            System.out.println("2. Customer");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");

            int loginChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (loginChoice) {
            case 1 -> showAdminPanel(scanner, menu, deliveryManager, admin,discountCalc);

                case 2 -> handleCustomerFlow(scanner, menu, deliveryManager,admin,discountCalc);
                case 3 -> {
                    exit = true;
                    System.out.println("Thank you! Visit again.");
                }
                
                default -> System.out.println("Invalid option.");
            }
        }

        scanner.close();
    }

    // ---------------------------- ADMIN PANEL ----------------------------
    private static void showAdminPanel(Scanner scanner, Menu menu, DeliveryManager deliveryManager, Admin admin,IDiscountCalculator discountCalc)
 {
        boolean back = false;

        while (!back) {
            System.out.println("\n====== ADMIN PANEL ======");
            System.out.println("1. Add Food Item");
            System.out.println("2. Remove Food Item (by ID)");
            System.out.println("3. Add Delivery Partner");
            System.out.println("4. Remove Delivery Partner (by ID)");
            System.out.println("5. View Menu");
            System.out.println("6. View Delivery Partners");
            System.out.println("7. View Menu by Cuisine");
            System.out.println("8. View All Orders");
            System.out.println("9. Back to Main Menu");

            System.out.print("Choose an option: ");

            int adminChoice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            switch (adminChoice) {
                case 1 -> handleAddFoodItem(menu, scanner);
                case 2 -> handleRemoveFoodItem(menu, scanner);
                case 3 -> handleAddDeliveryPartner(deliveryManager, scanner);
                case 4 -> handleRemoveDeliveryPartner(deliveryManager, scanner);
                case 5 -> menu.displayAllItems();
                case 6 -> deliveryManager.displayPartners();
                case 7 -> {
                    System.out.print("Enter cuisine type (e.g., Indian, Japanese, Italian): ");
                    String cuisine = scanner.nextLine();
                    menu.displayByCuisine(cuisine);
                }
                case 8 ->  admin.viewAllOrders(discountCalc);
                case 9 ->back = true;

                default -> System.out.println("Invalid option.");
            }
        }
    }

    private static void handleAddFoodItem(Menu menu, Scanner scanner) {
        int id = menu.getNextId();
        System.out.print("Enter food name: ");
        String name = scanner.nextLine();
        System.out.print("Enter cuisine type: ");
        String cuisine = scanner.nextLine();
        System.out.print("Enter price: ");

        try {
            int price = scanner.nextInt();
            scanner.nextLine();

            if (price <= 0) {
                throw new InvalidPriceException("Price must be greater than 0");
            }

            menu.addItem(new FoodItem(id, name, cuisine, price));
            System.out.println("Food item added with ID: " + id);
        } catch (InvalidPriceException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine(); // clear buffer
        }
    }


    private static void handleRemoveFoodItem(Menu menu, Scanner scanner) {
        System.out.print("Enter food ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        boolean removed = menu.removeItemById(id);

        if (removed) {
            System.out.println("Food item with ID " + id + " removed.");
        } else {
            System.out.println("Food item not found.");
        }
    }

    private static void handleAddDeliveryPartner(DeliveryManager manager, Scanner scanner) {
        int id = manager.getNextId();
        System.out.print("Enter delivery partner name: ");
        String name = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phoneNo = scanner.nextLine();

        manager.addPartner(new DeliveryPartner(id, name, phoneNo));
        System.out.println("Delivery partner added with ID: " + id);
    }

    private static void handleRemoveDeliveryPartner(DeliveryManager manager, Scanner scanner) {
        System.out.print("Enter delivery partner ID to remove: ");
        int id = scanner.nextInt();
        scanner.nextLine();
        boolean removed = manager.removePartnerById(id);

        if (removed) {
            System.out.println("Delivery partner with ID " + id + " removed.");
        } else {
            System.out.println("Delivery partner not found.");
        }
    }

    // ---------------------------- CUSTOMER FLOW ----------------------------
    private static void handleCustomerFlow(Scanner scanner, Menu menu, DeliveryManager deliveryManager, Admin admin,IDiscountCalculator discountCalc)
 {
      

        Order order = new Order();
        boolean exit = false;

        while (!exit) {
            displayCustomerOptions();
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {	
                case 1 -> menu.displayAllItems();
                case 2 -> {
                    System.out.print("Enter cuisine type (e.g., Indian, Japanese, Italian): ");
                    String cuisine = scanner.nextLine();
                    menu.displayByCuisine(cuisine);
                }
                case 3 -> handleAddToOrder(menu, order, scanner);
                case 4 -> handleCheckout(order, scanner, deliveryManager, admin,discountCalc);

                case 5 -> displayCurrentCart(order);
                case 6 -> exit = true;
                default -> System.out.println("Invalid option.");
            }
        }
    }
    
    private static boolean isValidEmail(String email) {
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        return email.matches(emailRegex);
    }



    private static void displayCustomerOptions() {
        System.out.println("\n====== CUSTOMER MENU ======");
        System.out.println("1. Display Full Menu");
        System.out.println("2. Display by Cuisine");
        System.out.println("3. Place Order");
        System.out.println("4. Checkout");
        System.out.println("5. Display cart");
        System.out.println("6. Back to Main Menu");
        System.out.print("Choose an option: ");
    }

    private static void handleAddToOrder(Menu menu, Order order, Scanner scanner) {
        menu.displayAllItems();
        try {
            System.out.print("Enter food ID to add: ");
            int foodId = scanner.nextInt();
            System.out.print("Enter quantity: ");
            int qty = scanner.nextInt();
            scanner.nextLine();

            IFoodItem selectedItem = menu.getItemById(foodId);
            if (selectedItem == null) {
                throw new ItemNotFoundException("Invalid food ID: " + foodId);
            }

            if (qty <= 0) {
                throw new InvalidQuantityException("Quantity must be greater than 0");
            }

            order.addItem(new OrderItem(selectedItem, qty));
            System.out.println("Item added to order.");
        } catch (ItemNotFoundException | InvalidQuantityException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Invalid input. Please try again.");
            scanner.nextLine(); // clear buffer
        }
    }


    private static void handleCheckout(Order order, Scanner scanner, DeliveryManager deliveryManager, Admin admin,IDiscountCalculator discountCalc) {
        try {
            if (order.getItems().isEmpty()) {
                throw new EmptyOrderException("Your order is empty. Please add items first.");
            }

            
            int discount = discountCalc.calculateDiscount(order);

            System.out.println("Select payment method:");
            System.out.println("1. UPI");
            System.out.println("2. Cash");
            System.out.print("Your choice: ");

            int paymentChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            IPayment paymentProcessor;
            String email = "";

            switch (paymentChoice) {
                case 1 -> {
                  
                    while (true) {
                        System.out.print("Enter your UPI-linked email: ");
                        email = scanner.nextLine();
                        if (isValidEmail(email)) {
                            break;
                        } else {
                            System.out.println("Invalid email. Please try again.");
                        }
                    }
                    paymentProcessor = new UPI_Payment();
                }
                case 2 -> paymentProcessor = new CashPayment();
                default -> {
                    System.out.println("Invalid choice. Defaulting to UPI.");
                    paymentProcessor = new UPI_Payment();
                }
            }

            String paymentStatus = paymentProcessor.getPayment(order.getTotalAmount() - discount);
            DeliveryPartner partner = deliveryManager.getRandomDeliveryPartner();

            InvoicePrinter printer = new InvoicePrinter();
            printer.print(order, discount, paymentStatus, partner);
            
          //  admin.recordOrder(order);
            admin.recordOrder(order);
            order=new Order();

        } catch (EmptyOrderException e) {
            System.out.println("Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("Something went wrong during checkout. Please try again.");
            scanner.nextLine(); // clear buffer
        }
    }



    private static void displayCurrentCart(Order order) {
        if (order.getItems().isEmpty()) {
            System.out.println("\nYour order is currently empty.");
        } else {
            System.out.println("\n------ CURRENT ORDER ------");
            for (OrderItem item : order.getItems()) {
                IFoodItem food = item.getItem();
                System.out.printf("- %s (%s) x %d @ ₹%d = ₹%d%n",
                        food.getName(),
                        food.getCuisineType(),
                        item.getQuantity(),
                        food.getPrice(),
                        item.getTotalPrice());
            }
            System.out.println("----------------------------");
            System.out.println("Total (before discount): ₹" + order.getTotalAmount());
        }
    }
}
