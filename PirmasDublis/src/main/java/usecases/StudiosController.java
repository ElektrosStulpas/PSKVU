package usecases;

import entities.Studio;
import lombok.Getter;
import lombok.Setter;
import persistence.StudiosDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class StudiosController {

    @Inject
    private StudiosDAO studiosDAO;

    @Getter @Setter
    private Studio studioToRegister = new Studio();

    @Getter
    private List<Studio> allStudios;

    @PostConstruct
    public void init()
    {
        this.allStudios = studiosDAO.findAll();
    }

    @Transactional
    public void registerStudio()
    {
        this.studiosDAO.persist(studioToRegister);
    }
}
