package Pack.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import Pack.vo.TestVo;
import Pack.vo.LogiExportVo;

@Repository
@Mapper
public interface TestMapper {
    List<TestVo> selectTest();
    List<LogiExportVo> selectAll();
}
