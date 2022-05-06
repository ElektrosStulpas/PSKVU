package services;

import javax.decorator.Decorator;
import javax.decorator.Delegate;
import javax.enterprise.inject.Any;
import javax.inject.Inject;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@Decorator
public abstract class TopStreamerLogger implements StreamerCounter {

    @Inject @Delegate @Any StreamerCounter streamerCounter;

    @Override
    public Integer countStreamersForGame() {

        System.out.println("decorator method called");
        Integer streamerCount = streamerCounter.countStreamersForGame();
        if (streamerCount > 5000)
        {
            System.out.println("big streamer, count: "+streamerCount);
        } else {
            System.out.println("small streamer, count: "+streamerCount);
        }
        return streamerCount;
    }
}
