package Pack.vo;

import java.text.SimpleDateFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString 
@Getter
@Setter
//@AllArgsConstructor
//@NoArgsConstructor
public class LogiExportSearchDTO {
	String status;
	String location;
	String product_family;
	String lot_no;
	String item_code;
	String item_name;
	String from_warehouse;
	String unit;
	float min_weight;
	float max_weight;
	float min_thickness;
	float max_thickness;
	float min_height;
	float max_height;
	int min_order_amount;
	int max_order_amount;
	int min_ex_amount;
	int max_ex_amount;
	double min_width;
	double max_width;
	String customer;
	String order_date;
	String inst_reg_date;
	String inst_deadline;
	String done_date;
}
