package usecases;

import entities.Game;
import entities.Player;
import lombok.Getter;
import lombok.Setter;
import persistence.GamesDAO;
import persistence.PlayersDAO;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class PlayersForGame {

    @Inject
    private GamesDAO gamesDAO;

    @Inject
    private PlayersDAO playersDAO;

    @Getter @Setter
    private Game game;

    @Getter @Setter
    private Player playerToCreate = new Player();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer gameId = Integer.parseInt(requestParameters.get("gameId"));
        this.game = gamesDAO.findOne(gameId);
    }

    @Transactional
    public void addPlayer(String playerHandle)
    {
        Player tempPlayer = playersDAO.findOneByName(playerHandle);
        if (tempPlayer == null) {
            playerToCreate.setHandle(playerHandle);
            playersDAO.persist(playerToCreate);
            tempPlayer = playerToCreate;
        }
        game.addPlayer(tempPlayer);
    }
}
