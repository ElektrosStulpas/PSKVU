package usecases.mybatis;

import lombok.Getter;
import lombok.Setter;
import mybatis.dao.StudioMapper;
import mybatis.model.Studio;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class StudiosMyBatisController {
    @Inject
    private StudioMapper studioMapper;

    @Getter @Setter
    private Studio studioToRegister = new Studio();

    @Getter
    private List<Studio> allStudios;

    @PostConstruct
    public void init() {this.allStudios = studioMapper.selectAll();}

    @Transactional
    public void registerStudio() {
        studioMapper.insert(studioToRegister);
    }
}
