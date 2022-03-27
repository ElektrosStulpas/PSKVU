package usecases;

import lombok.Getter;
import lombok.Setter;
import mybatis.dao.GameMapper;
import mybatis.dao.GamePlayerMapper;
import mybatis.dao.PlayerMapper;
import mybatis.model.Game;
import mybatis.model.GamePlayer;
import mybatis.model.Player;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Model;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Map;

@Model
public class PlayersForGameMyBatis {
    @Inject
    private GameMapper gameMapper;

    @Inject
    private PlayerMapper playerMapper;

    @Inject
    private GamePlayerMapper gamePlayerMapper;

    @Getter @Setter
    private Game game;

    @Getter @Setter
    private Player playerToCreate = new Player();

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer gameId = Integer.parseInt(requestParameters.get("gameId"));
        this.game = gameMapper.selectByPrimaryKey(gameId);
    }

    @Transactional
    public void addPlayer(String playerHandle)
    {
        Player tempPlayer = playerMapper.findOneByName(playerHandle);
        if (tempPlayer == null) {
            playerToCreate.setHandle(playerHandle);
            playerMapper.insert(playerToCreate);
            tempPlayer = playerToCreate;
        }

        GamePlayer gamePlayer = new GamePlayer();
        gamePlayer.setPlayersId(tempPlayer.getId());
        gamePlayer.setGamesId(this.game.getId());
        gamePlayerMapper.insert(gamePlayer);
    }
}
