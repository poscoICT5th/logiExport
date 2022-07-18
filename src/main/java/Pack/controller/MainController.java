package Pack.controller;

import java.util.List;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import Pack.service.AutoIncrese;
import Pack.service.ExportService;
import Pack.service.TestService;
import Pack.vo.TestVo;
import Pack.vo.LogiExportDTO;
import Pack.vo.LogiExportList;
import Pack.vo.LogiExportMulti;
import Pack.vo.LogiExportMultiDTO;
import Pack.vo.LogiExportSearchDTO;
import Pack.vo.LogiExportVo;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/")
public class MainController {
	@Autowired
	TestService testService;
	@Autowired
	ExportService exportService;
	@Autowired
	RabbitTemplate rabbitTemplate;
	
	@GetMapping("/test")
	public List<TestVo> test() {
		System.out.println(111);
	    List<TestVo> testList = testService.selectTest();
	    System.out.println(testList);
	    return testList;
	}
	
	@GetMapping("/export")
	public List<LogiExportVo> exportAll() {
		System.out.println("export all");
	    List<LogiExportVo> exportAll = exportService.selectAll();
	    System.out.println(exportAll);
	    return exportAll;
	}
	
	@GetMapping("/search")
	public List<LogiExportVo> exportSearch(LogiExportSearchDTO logiExportSearchDTO) {
		System.out.println("export search");
		System.out.println(logiExportSearchDTO);
	    List<LogiExportVo> exportSearch = exportService.selectSome(logiExportSearchDTO);
	    System.out.println("result export");
	    System.out.println(exportSearch);
		return exportSearch;
	}
	
	@PostMapping("/export")
	public boolean exportAdd(@RequestBody LogiExportDTO data) {
		System.out.println("post 들어감");
		System.out.println(data); 
		int result = exportService.insert(data);
		if (result > 0) {
			rabbitTemplate.convertAndSend("posco", "export.Inventory.process", data);
		}
		return result==1?true:false;
	}
	
	@PostMapping("/export/multi")
	public boolean exportAdds(@RequestBody LogiExportMulti data) {
		System.out.println("post 들어감");
		System.out.println(data);
		int result = exportService.inserts(data);
		AutoIncrese.setNum();
		RestTemplate restTemplate = new RestTemplate();
		if (result > 0) {
			for (LogiExportMultiDTO logiExportMultiDTO : data.getLogiExportList()) {
				rabbitTemplate.convertAndSend("posco", "export.Inventory.process", logiExportMultiDTO);
				restTemplate.getForEntity("35.77.54.132:8080/hotline/send/type/"+"출고"+"/topic/" + logiExportMultiDTO.getFrom_warehouse(), String.class);
			}
		}
		return result==1?true:false;
	}
	
	@DeleteMapping("/export")
	public boolean exportDeletes(@RequestBody LogiExportList logiExportList) {
		System.out.println("delete List");

		System.out.println(logiExportList);
		int result = exportService.cancels(logiExportList);
		System.out.println(result);
		if (result > 0) {
			for (String instructionNo : logiExportList.getLogiExportList()) {
				System.out.println(instructionNo);
				LogiExportVo deleteExportDTO = exportService.selectByInstNo(instructionNo);
				System.out.println(deleteExportDTO);
				rabbitTemplate.convertAndSend("posco", "export.Inventory.done", deleteExportDTO);
			}
		}
		return result==1?true:false;
	}
	
	@PutMapping("/export/rollback")
	public boolean exportRollbacks(@RequestBody LogiExportList logiExportList) {
		System.out.println("rollback List");
		System.out.println(logiExportList);
		int result = exportService.rollback(logiExportList);
		System.out.println(result);
		if (result > 0) {
			for (String instructionNo : logiExportList.getLogiExportList()) {
				System.out.println(instructionNo);
				LogiExportVo rollbackExportDTO = exportService.selectByInstNo(instructionNo);
				System.out.println(rollbackExportDTO);
				rabbitTemplate.convertAndSend("posco", "export.Inventory.process", rollbackExportDTO);
			}
		}
		return result==1?true:false;
	}
	
	@DeleteMapping("export/{instructionNo}")
	public boolean exportDelete(@PathVariable String instructionNo) {
		System.out.println("출고 삭제");
		System.out.println(instructionNo);
		LogiExportVo exportDeleteData = exportService.selectByInstNo(instructionNo);
		int result = exportService.delete(instructionNo);
		if (result > 0 ) {
			rabbitTemplate.convertAndSend("posco", "export.Inventory.done", exportDeleteData);
		}
		return result==1?true:false;
	}
	
	@PutMapping("export/{instructionNo}")
	public boolean exportConfirm(@PathVariable String instructionNo) {
		System.out.println("출고 확정");
		System.out.println(instructionNo);
		int result = exportService.confirm(instructionNo);
		if (result > 0) {
			LogiExportVo exportConfirmData = exportService.selectByInstNo(instructionNo);
			System.out.println(exportConfirmData);
			rabbitTemplate.convertAndSend("posco", "export.Inventory.reduce", exportConfirmData);
			rabbitTemplate.convertAndSend("posco", "export.Inventory.done", exportConfirmData);
			return true;			
		} else {
			return false;
		}
	}
	
	@GetMapping("/lotno/{lotNo}")
	public LogiExportVo selectByLotNo(@PathVariable String lotNo) {
		System.out.println(lotNo);
		LogiExportVo result = exportService.selectByLotNo(lotNo);
		return result;
	}
}
