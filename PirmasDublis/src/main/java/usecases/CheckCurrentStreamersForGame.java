package usecases;

import interceptors.LoggerAnnotation;
import lombok.Getter;
import lombok.Setter;
import services.StreamerCounter;

import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@LoggerAnnotation
@SessionScoped
@Named
public class CheckCurrentStreamersForGame implements Serializable {

    @Inject
    StreamerCounter streamerCounter;

    private CompletableFuture<Integer> streamerCountCheckTask = null;

    public String checkCurrentStreamers() {
        Map<String, String> requestParameters =
                FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap();

        streamerCountCheckTask = CompletableFuture.supplyAsync(() -> streamerCounter.countStreamersForGame());
        return "players.xhtml?faces-redirect=true&gameId="+requestParameters.get("gameId");
    }

    private boolean isStreamerCounterRunning() {
        return streamerCountCheckTask != null && !streamerCountCheckTask.isDone();
    }

    public String getCurrentStreamerCountStatus() throws InterruptedException, ExecutionException {
        if (streamerCountCheckTask == null) {
            return null;
        } else if (isStreamerCounterRunning()) {
            return "Counting current streamers for the game";
        }
        return "Current streamers playing this game: " + streamerCountCheckTask.get();
    }
}
