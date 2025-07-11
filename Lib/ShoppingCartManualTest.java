import java.util.ArrayList;

public class ShoppingCartManualTest {

    public static void run() {
        System.out.println("--- Starting Shopping Cart Calculator Tests ---");
        System.out.println(); // for spacing

        int passedCount = 0;
        int failedCount = 0;

        // Test 1: ตะกร้าเป็น null
        try {
            double total1 = ShoppingCartCalculator.calculateTotalPrice(null);
            if (total1 == 0.0) {
                System.out.println("PASSED: Null cart should return 0.0");
                passedCount++;
            } else {
                System.out.println("FAILED: Null cart expected 0.0 but got " + total1);
                failedCount++;
            }
        } catch (Exception e) {
            System.out.println("FAILED: Null cart caused an exception: " + e.getMessage());
            failedCount++;
        }

        // Test 2: ตะกร้าว่าง
        ArrayList<CartItem> emptyCart = new ArrayList<>();
        double total2 = ShoppingCartCalculator.calculateTotalPrice(emptyCart);
        if (total2 == 0.0) { //0
            System.out.println("PASSED: Empty cart should return 0.0");
            passedCount++;
        } else {
            System.out.println("FAILED: Empty cart expected 0.0 but got " + total2);
            failedCount++;
        }

        // Test 3: คำนวณปกติ ไม่มีส่วนลด
        ArrayList<CartItem> simpleCart = new ArrayList<>();
        simpleCart.add(new CartItem("NORMAL", "Bread", 25.0, 2));     // 50
        simpleCart.add(new CartItem("NORMAL", "Milk", 15.0, 1));      // 15
        double total3 = ShoppingCartCalculator.calculateTotalPrice(simpleCart);
        if (total3 == 65.0) { //65
            System.out.println("PASSED: Simple cart total is correct (65.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 65.0 but got " + total3);
            failedCount++;
        }


        //test 4 ลด 10% อย่าง
        ArrayList<CartItem> dicoundCart1 = new ArrayList<>();
        dicoundCart1.add(new CartItem("BULK", "Shirt", 100.0, 10));   //900
        dicoundCart1.add(new CartItem("NORMAL", "Milk", 15.0, 1));    //15   
        double total4 = ShoppingCartCalculator.calculateTotalPrice(dicoundCart1);
        if (total4 == 915.0) { //915
            System.out.println("PASSED: Simple cart total is correct (915.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 915.0 but got " + total4);
            failedCount++;
        }
        //test 5 ลด 1 แถม 1 
        ArrayList<CartItem> dicoundCart2 = new ArrayList<>();
        dicoundCart2.add(new CartItem("BOGO", "Shirt", 100.0, 8));  //800
        dicoundCart2.add(new CartItem("NORMAL", "Milk", 15.0, 1));  //15    
        double total5 = ShoppingCartCalculator.calculateTotalPrice(dicoundCart2);
        if (total5 == 415.0) { //415
            System.out.println("PASSED: Simple cart total is correct (415.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 415.0 but got " + total5);
            failedCount++;
        }
        //test 6 ใช้ Code คุ้ม
        ArrayList<CartItem> dicoundCart3 = new ArrayList<>();
        dicoundCart3.add(new CartItem("BOGO", "Shirt", 100.0, 8));  //400
        dicoundCart3.add(new CartItem("BULK", "Skirt", 100.0,10));  //900    
        double total6 = ShoppingCartCalculator.calculateTotalPrice(dicoundCart3);
        if (total6 == 1300.0) { //1300
            System.out.println("PASSED: Simple cart total is correct (1300.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 1300.0 but got " + total6);
            failedCount++;
        }
        // Test Fail #1
        // test 7 ใช้โค้ดถูกและมีจำนวนติดลบ
        ArrayList<CartItem> dicoundCart4 = new ArrayList<>();
        dicoundCart4.add(new CartItem("BOGO", "Shirt", 100.0, -2));          //0
        dicoundCart4.add(new CartItem("BULK", "Skirt", 100.0,10));  //900    
        double total7 = ShoppingCartCalculator.calculateTotalPrice(dicoundCart4);
        if (total7 == 1200.0) { //900
            System.out.println("PASSED: Simple cart total is correct (900.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 1200.0 but got " + total7);
            failedCount++;
        }
        // Test Fail #2
        //test 8 ใช้โค้ดทั้งคู่แต่ตัวนึงสินค้าติดลบ ตัวนึงจำนวนติดลบ คำตอบ ต้อง = 0
        ArrayList<CartItem> dicoundCart5 = new ArrayList<>();
        dicoundCart5.add(new CartItem("BOGO", "Shirt", 100.0, -22));  //0
        dicoundCart5.add(new CartItem("BULK", "Skirt", 100.0,-50));   //0  
        double total8 = ShoppingCartCalculator.calculateTotalPrice(dicoundCart5);
        if (total8 == 1200.0) { //0
            System.out.println("PASSED: Simple cart total is correct (0.0)");
            passedCount++;
        } else {
            System.out.println("FAILED: Simple cart total expected 0.0 but got " + total8);
            failedCount++;
        }
        // --- Test Summary ---
        System.out.println("\n--------------------");
        System.out.println("--- Test Summary ---");
        System.out.println("Passed: " + passedCount + ", Failed: " + failedCount);
        if (failedCount == 0) {
            System.out.println("Excellent! All tests passed!");
        } else {
            System.out.println("Some tests failed.");
        }
    
}
}