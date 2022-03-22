package persistence;

import entities.Game;
import entities.Studio;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class GamesDAO {

    @Inject
    private EntityManager em;

    public void persist(Game game){
        this.em.persist(game);
    }

    public Game findOne(Integer id){
        return em.find(Game.class, id);
    }

    public Game update(Game game){
        return em.merge(game);
    }

    public List<Game> findAll()
    {
        return em.createQuery("SELECT g FROM Game as g", Game.class)
                .getResultList();
    }
}
