package usecases;

import services.StreamerCounter;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@SessionScoped
@Named
public class CheckCurrentStreamersForGame implements Serializable {

    @Inject
    StreamerCounter streamerCounter;

    private CompletableFuture<Integer> streamerCountCheckTask = null;

    public String checkCurrentStreamers(int gameId) {

        streamerCountCheckTask = CompletableFuture.supplyAsync(() -> streamerCounter.countStreamersForGame());
        return "index.xhtml?faces-redirect=true";
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
