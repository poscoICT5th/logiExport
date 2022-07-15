package Pack.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Pack.vo.LogiExportDTO;
import Pack.vo.LogiExportList;
import Pack.vo.LogiExportMulti;
import Pack.vo.LogiExportMultiDTO;
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
    
    public int inserts(LogiExportMulti data) {
    	return exportMapper.inserts(data);
    }

    public int confirm(String instructionNo) {
    	return exportMapper.confirm(instructionNo);
	}

	public int delete(String instructionNo) {
		return exportMapper.delete(instructionNo);
	}

	public int deletes(LogiExportList logiExportList) {
		return exportMapper.deletes(logiExportList.getLogiExportList());
	}

	public LogiExportVo selectByLotNo(String lotNo) {
		return exportMapper.selectByLotNo(lotNo);
	}

	public int cancels(LogiExportList logiExportList) {
		return exportMapper.cancels(logiExportList);
	}

	public int rollback(LogiExportList logiExportList) {
		return exportMapper.rollback(logiExportList);
	}
}
