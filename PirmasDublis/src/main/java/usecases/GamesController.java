package usecases;

import entities.Game;
import entities.Studio;
import lombok.Getter;
import lombok.Setter;
import persistence.GamesDAO;
import persistence.StudiosDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.List;

@Model
public class GamesController {

    @Inject
    private GamesDAO gamesDAO;

    @Inject
    private StudiosDAO studiosDAO;

    @Getter @Setter
    private Game gameToRegister = new Game();

//    @Getter
//    private List<Game> allGames;
//
//    @PostConstruct
//    public void init()
//    {
//        this.allGames = gamesDAO.findAll();
//    }

    @Transactional
    public void registerGame(String studioName)
    {
        Studio studio = this.studiosDAO.findOneByName(studioName);
        gameToRegister.setStudio(studio);
        this.gamesDAO.persist(gameToRegister);
    }
}
