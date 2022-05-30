package Pack.mapper;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Pack.vo.TestVo;
import Pack.vo.LogiExportVo;

@Service
public class TestService {
    @Autowired
    public TestMapper mapper;

    public List<TestVo> selectTest() {
        return mapper.selectTest();
    }
    
    public List<LogiExportVo> selectAll() {
        return mapper.selectAll();
    }
}
