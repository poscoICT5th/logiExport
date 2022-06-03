package Pack.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import Pack.vo.LogiExportDTO;
import Pack.vo.LogiExportSearchDTO;
import Pack.vo.LogiExportVo;

@Repository
@Mapper
public interface ExportMapper {
    List<LogiExportVo> selectAll();

    List<LogiExportVo> selectSome(LogiExportSearchDTO logiExportSearchDTO);

    int insert(LogiExportDTO data);

	int delete(String instructionNo);
	
	int confirm(String instructionNo);
}
