package persistence;

import entities.Studio;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;

@ApplicationScoped
public class GamesDAO {

    @Inject
    private EntityManager em;

    public void persist(Studio studio){
        this.em.persist(studio);
    }

    public Studio findOne(Integer id){
        return em.find(Studio.class, id);
    }

    public Studio update(Studio studio){
        return em.merge(studio);
    }
}
