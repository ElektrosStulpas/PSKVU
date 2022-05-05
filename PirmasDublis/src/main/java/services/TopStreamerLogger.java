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
        CompletableFuture<Integer> streamerCounterTask = CompletableFuture.supplyAsync(() -> streamerCounter.countStreamersForGame());
//        while(!streamerCounterTask.isDone())
//        {
//            //calculating
//        }
        Integer streamerCount = null;
        try {
            streamerCount = streamerCounterTask.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        if (streamerCount > 5000)
        {
            System.out.println("big streamer");
        }
        return null;
    }
}
