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

import Pack.service.ExportService;
import Pack.service.TestService;
import Pack.vo.TestVo;
import Pack.vo.LogiExportDTO;
import Pack.vo.LogiExportDeleteList;
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
	public List test() {
		System.out.println(111);
	    List<TestVo> testList = testService.selectTest();
	    System.out.println(testList);
	    return testList;
	}
	
	@GetMapping("/export")
	public List exportAll() {
		System.out.println("export all");
	    List<LogiExportVo> exportAll = exportService.selectAll();
	    System.out.println(exportAll);
	    return exportAll;
	}
	
	@GetMapping("/search")
	public List exportSearch(LogiExportSearchDTO logiExportSearchDTO) {
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
		return result==1?true:false;
	}
	
	@DeleteMapping("/export")
	public boolean exportDeletes(@RequestBody LogiExportDeleteList logiExportDeleteList) {
		System.out.println("delete List");
		System.out.println(logiExportDeleteList);
		int result = exportService.deletes(logiExportDeleteList);
		return result==1?true:false;
	}
	
	@DeleteMapping("export/{instructionNo}")
	public boolean exportDelete(@PathVariable String instructionNo) {
		System.out.println("출고 삭제");
		System.out.println(instructionNo);
		int result = exportService.delete(instructionNo);
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
			return true;			
		} else {
			return false;
		}
	}
	
	@GetMapping("/inst/{lotNo}")
	public LogiExportVo selectByLotNo(@PathVariable String lotNo) {
		System.out.println(lotNo);
		LogiExportVo result = exportService.selectByLotNo(lotNo);
		return result;
	}
}
