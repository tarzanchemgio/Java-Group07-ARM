package Models;

import org.bson.Document;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.text.DateFormat;
import java.util.Locale;
import java.util.TimeZone;

public class Bill extends Document {

	/* Để tránh việc đọc lại Menu database để lấy giá tiền từng món,
	danh sách các món đã đặt sẽ gồm t
	 */

	private HashMap<String, Integer> dishesWithNumber = new HashMap<>();
	private HashMap<String, Long> dishesWithPrice = new HashMap<>();
	private String billID;
	private String dateTime;
	private String customerID;
	private Long totalPrice;

	public Bill(HashMap<String, Integer> dishesWithNumber,HashMap<String, Long> dishesWithPrice, Customer c) {
		int price = 0;
		Double VAT = 0.1;
		/* Lấy thời gian hiện tại */
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		LocalDateTime now = LocalDateTime.now();
		dateTime = dtf.format(now);
		billID = Long.toString(now.toEpochSecond(ZoneOffset.of(dateTime)));
		/* Kiểm tra thành viên */
		if(c != null) {
			customerID = c.getCustomerID();
		}
		else customerID = "-1";

		for(String dishName : dishesWithNumber.keySet()) {
			price += (dishesWithNumber.get(dishName) * dishesWithPrice.get(dishName));
			this.dishesWithNumber.put(dishName, dishesWithNumber.get(dishName));
			this.dishesWithPrice.put(dishName, dishesWithPrice.get(dishName));
		}

		totalPrice = price + Math.round(VAT*price);
	}

	public Bill() {

	}

	//These two function will use on getBill()
	public HashMap<String, Integer> getDishesWithNumber(){
		return dishesWithNumber;
	}

	public HashMap<String, Long> getDishesWithPrice(){
		return dishesWithPrice;
	}

	public String getBillID() {
		return billID;
	}

	public String getCustomerID() {
		return this.customerID;
	}

	public Long getTotalPrice(){
		return totalPrice;
	}
}