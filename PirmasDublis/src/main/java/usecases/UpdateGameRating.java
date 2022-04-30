package usecases;

import entities.Game;
import lombok.Getter;
import lombok.Setter;
import persistence.GamesDAO;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.Map;

@ViewScoped
@Named
@Getter @Setter
public class UpdateGameRating implements Serializable {

    private Game game;

    @Inject
    private GamesDAO gamesDAO;

    @PostConstruct
    public void init() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();
        Integer gameId = Integer.parseInt(requestParameters.get("gameId"));
        this.game = gamesDAO.findOne(gameId);
    }

    @Transactional
    public String updateRatingForGame(){
        try{
            gamesDAO.update(this.game);
        } catch (OptimisticLockException e) {
            return "/players?faces-redirect=true&gameId="+this.game.getId()+"&error=optimistic-lock-exception";
        }
        return "/players?gameId="+this.game.getId()+"&faces-redirect=true";
    }
}
