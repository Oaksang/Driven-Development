import java.util.ArrayList;
//import java.util.List;

public class ShoppingCartCalculator {

    /**
     * เป็นโปรแกรมที่ช่วยคำนวนราคาของสินค้าภายในตะกร้าและมีการใส่รหัสสินค้าเพื่อเช็คโปรโมชันต่างๆ
     * โดยมีเงื่อนไขครอบคุมต่างๆเช่น
     * - ถ้าตระก้าว่างหรือตะกล้าเป็นnull จะ return 0 0
     * - โดนเริ่มแรกจะมีการเช็คว่าสินค้าใน Cartitem เป็นจำนวนติดลบหรือไม่
     * - ถ้า Cartitem มี price และ qauntity ติดลบจะไม่นับรายการของสินค้าชิ้นนั้น
     * - ถ้าใส่รหัสสินค้า BOGO จะซื้อ 1 แถม 1 โดยที่สร้างตัวแปล p มารับโดยใช้หลักการ % 2 เพื่อเอาเศษ 1 มาบวกในกรณีที่สินค้าเป็นเลขคี่เพราะติดเงื่อนไขแถม 1
     * - ถ้าใส่รหัสสินค้า BULK โปรแกรมจะเช็คว่า จำนวนสินค้ามากกว่า 6 ชิ้นมั้ยหากเข้าเงื่อนไข จะได้การทำราคาสินค้าไปคูณ 0.9 เปรียบเสมือนการลด 10 %
     * - หลังจากคำนวนโปรโมชันโดยใช้ Switch case เส็จแล้วจะทำการนำราคาสินค้ามา+=และเก็บไว้ใน total และเตรียม return เพื่อกลับไปเทส
     * @param items รายการสินค้าทั้งหมดในตะกร้า (อาจมีรหัสโปรโมชั่น BOGO, BULK)
     * @return ราคารวมสุทธิหลังหักส่วนลดคำนวนโปรโมชันต่างๆ
     */
    public static double calculateTotalPrice(ArrayList<CartItem> items) {
        if (items == null || items.isEmpty()) return 0.0;
        double total = 0.0;
         for(int i = 0; i < items.size(); i++) {
            CartItem item = items.get(i);
             double itemTotal = 0.0;
             String sku = item.sku();
             if (item.quantity()>0 && item.price()>0) {

                switch (sku) {
                case "BOGO" : {
                    int p = (item.quantity() / 2) + (item.quantity() % 2);
                    itemTotal = p * item.price();
                    break;
                }
                case "BULK" : {
                    if (item.quantity() >= 6) {
                        itemTotal = item.quantity() * item.price() * 0.9;
                    } else {
                        itemTotal = item.quantity() * item.price();
                    }
                    break;
                }
                default : {
                    itemTotal = item.quantity() * item.price();
                }
            }
             }else{
                continue;
             }
            
            total += itemTotal;
        } 
        return total; 

    }
}
