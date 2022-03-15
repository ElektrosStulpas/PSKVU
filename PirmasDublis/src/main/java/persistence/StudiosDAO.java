package persistence;

import entities.Studio;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class StudiosDAO {

    @Inject
    private EntityManager em;

    public void persist(Studio studio) { this.em.persist(studio); }

    public List<Studio> findAll()
    {
        return em.createQuery("SELECT e FROM Studio as e", Studio.class)
                .getResultList();
    }
}
