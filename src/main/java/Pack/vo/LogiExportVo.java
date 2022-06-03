package Pack.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString 
@Getter
@Setter
public class LogiExportVo {
	String status;
	String location;
	String instruction_no;
	String target;
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
}
