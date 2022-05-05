package rest;

import entities.Game;
import entities.Studio;
import lombok.Getter;
import lombok.Setter;
import persistence.GamesDAO;
import persistence.StudiosDAO;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.OptimisticLockException;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/games")
public class RESTGamesController {
    @Inject
    @Setter @Getter
    private GamesDAO gamesDAO;

    @Inject
    @Setter @Getter
    private StudiosDAO studiosDAO;

    @Path("/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") final Integer id) {
        Game game = gamesDAO.findOne(id);
        if (game == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        GameDTO dto = new GameDTO();
        dto.setTitle(game.getTitle());
        dto.setRating(game.getRating());
        dto.setStudio(game.getStudio().getName());

        return Response.ok(dto).build();
    }

    @Path("/")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response createNew(GameDTO dto) {
        Game game = new Game();

        game.setTitle(dto.getTitle());
        game.setRating(dto.getRating());
        Studio dtoStudio = studiosDAO.findOneByName(dto.getStudio());
        if (dtoStudio == null)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        game.setStudio(dtoStudio);
        gamesDAO.persist(game);
        return Response.ok().build();
    }

    @Path("/{id}")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    @Transactional
    public Response updateById(@PathParam("id") final Integer id, GameDTO dto) {
        Game game = gamesDAO.findOne(id);
        if (game == null) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        game.setTitle(dto.getTitle());
        game.setRating(dto.getRating());
        Studio dtoStudio = studiosDAO.findOneByName(dto.getStudio());
        if (dtoStudio == null)
        {
            return Response.status(Response.Status.NOT_FOUND).build();
        }
        game.setStudio(dtoStudio);
        try {
            gamesDAO.update(game);
            return Response.ok().build();
        }catch (OptimisticLockException e) {
            return Response.status(Response.Status.CONFLICT).build();
        }
    }
}
