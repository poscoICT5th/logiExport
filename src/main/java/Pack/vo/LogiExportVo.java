package Pack.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString 
@Getter
@Setter
public class LogiExportVo {
	String instruction_no;
	String status;
	String lot_no;
	String item_code;
	String item_name;
	int order_amount;
	int ex_amount;
	int ex_remain;
	String unit;
	float width;
	float weight;
	float thickness;
	float height;
	String product_family;
	String location;
	String from_warehouse;
	String customer;
	String order_date;
	String inst_reg_date;
	String inst_deadline;
	String done_date;
}
