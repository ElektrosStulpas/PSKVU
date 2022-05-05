package usecases.mybatis;

import lombok.Getter;
import lombok.Setter;
import mybatis.dao.GameMapper;
import mybatis.dao.StudioMapper;
import mybatis.model.Game;
import mybatis.model.Studio;

import javax.enterprise.inject.Model;
import javax.inject.Inject;
import javax.transaction.Transactional;

@Model
public class GamesMyBatisController {
    @Inject
    private GameMapper gameMapper;

    @Inject
    private StudioMapper studioMapper;

    @Getter @Setter
    private Game gameToRegister = new Game();

    @Transactional
    public void registerGame(String studioName) {
        Studio studio = this.studioMapper.selectOneByName(studioName);
        gameToRegister.setStudio(studio.getId());
        this.gameMapper.insert(gameToRegister);
    }
}
