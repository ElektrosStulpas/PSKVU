package persistence;

import entities.Game;
import entities.Player;
import entities.Studio;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import java.util.List;

@ApplicationScoped
public class PlayersDAO {

    @Inject
    private EntityManager em;

    public void persist(Player player){
        this.em.persist(player);
    }

    public Player findOne(Integer id){
        return em.find(Player.class, id);
    }

    public Player update(Player player){
        return em.merge(player);
    }

    public Player findOneByName(String playerHandle)
    {

        return em.createQuery("SELECT p FROM Player as p WHERE p.handle = :playerHandle", Player.class)
                .setParameter("playerHandle", playerHandle)
                .getResultList().stream().findFirst().orElse(null);
    }
}
