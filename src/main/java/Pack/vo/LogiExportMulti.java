package Pack.vo;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class LogiExportMulti {
	List<LogiExportMultiDTO> logiExportList;
	String order_date;
	String inst_deadline;
}
