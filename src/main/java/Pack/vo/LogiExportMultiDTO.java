package Pack.vo;

import java.text.SimpleDateFormat;

import Pack.service.AutoIncrese;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString 
@Getter
@Setter
//@NoArgsConstructor
public class LogiExportMultiDTO {
	String instruction_no;
	String status;
	String lot_no;
	String item_code;
	String item_name;
	int order_amount;
	int ex_amount;
	int ex_remain;
	String unit;
	float weight;
	float width;
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
	public LogiExportMultiDTO(String lot_no, String item_code, String item_name, int amount,
			String unit, float weight, float width, float thickness, float height,
			String product_family, String location, String warehouse_code, String customer, String order_date,
			String inst_deadline) {
		super();
		System.out.println("출고 멀티 여기들어옴");
		long curTime = System.currentTimeMillis();
		String makeDate = new SimpleDateFormat("YYMMddHHmmssSSS").format(curTime) + AutoIncrese.getNum();
		this.instruction_no = makeDate;
		this.status = "출고대기";
		this.lot_no = lot_no;
		this.item_code = item_code;
		this.item_name = item_name;
		this.order_amount = amount;
		this.ex_amount = amount;
		this.unit = unit;
		this.weight = weight;
		this.width = width;
		this.thickness = thickness;
		this.height = height;
		this.product_family = product_family;
		this.location = location;
		this.from_warehouse = warehouse_code;
		this.customer = customer;
		this.order_date = order_date;
		this.inst_reg_date = new SimpleDateFormat("YYYY-MM-dd").format(curTime);
		this.inst_deadline = inst_deadline;
	}

	
}
