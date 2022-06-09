package Pack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Pack.vo.LogiExportDTO;
import Pack.vo.LogiExportDeleteList;
import Pack.vo.LogiExportSearchDTO;
import Pack.vo.LogiExportVo;
import Pack.vo.TestVo;
import Pack.mapper.ExportMapper;
import Pack.mapper.TestMapper;

@Service
public class ExportService {
    @Autowired
    public ExportMapper exportMapper;

    public List<LogiExportVo> selectAll() {
        return exportMapper.selectAll();
    }
    
    public List<LogiExportVo> selectSome(LogiExportSearchDTO logiExportSearchDTO) {
    	return exportMapper.selectSome(logiExportSearchDTO);
    }

    public LogiExportVo selectByInstNo(String instructionNo) {
    	return exportMapper.selectByInstNo(instructionNo);
    }
    
    public int insert(LogiExportDTO data) {
    	return exportMapper.insert(data);
    }

    public int confirm(String instructionNo) {
    	return exportMapper.confirm(instructionNo);
	}

	public int delete(String instructionNo) {
		return exportMapper.delete(instructionNo);
	}

	public int deletes(LogiExportDeleteList logiExportDeleteList) {
		return exportMapper.deletes(logiExportDeleteList.getLogiExportDeleteList());
	}
}
