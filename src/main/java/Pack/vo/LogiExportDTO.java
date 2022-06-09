package Pack.vo;

import java.text.SimpleDateFormat;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString 
@Getter
@Setter
public class LogiExportDTO {
	String status;
	String location;
	String instruction_no;
	String customer;
	String lot_no;
	String item_no;
	String item_name;
	float width;
	float weight;
	float thickness;
	float height;
	int order_amount;
	int ex_amount;
	int ex_remain;
	String order_date;
	String inst_reg_date;
	String inst_deadline;
	String done_date;
	public LogiExportDTO(String location, String customer, String lot_no, String item_no, String item_name, float width, float weight,
			float thickness, float height, int order_amount, int ex_amount, int ex_remain, String order_date,
			String inst_deadline, String done_date) {
		super();
		this.location = location;
		this.customer = customer;
		this.lot_no = lot_no;
		this.item_no = item_no;
		this.item_name = item_name;
		this.width = width;
		this.weight = weight;
		this.thickness = thickness;
		this.height = height;
		this.order_amount = order_amount;
		this.ex_amount = ex_amount;
		this.ex_remain = ex_remain;
		this.order_date = order_date;
		this.inst_deadline = inst_deadline;
		this.done_date = done_date;
		this.status = "출고대기";
		System.out.println("출고 여기들어옴");
		long curTime = System.currentTimeMillis();
		String makeDate = new SimpleDateFormat("YYMMddHHmmssms").format(curTime);
		this.inst_reg_date = new SimpleDateFormat("YYYY-MM-dd").format(curTime);
		this.instruction_no = makeDate;
	}
	
}
