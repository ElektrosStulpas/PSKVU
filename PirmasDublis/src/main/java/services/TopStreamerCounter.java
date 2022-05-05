package services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;
import java.util.Random;

@Alternative
@ApplicationScoped
public class TopStreamerCounter implements Serializable, StreamerCounter {

    public Integer countStreamersForGame() {
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
        }
        return (new Random().nextInt(2000)) + 5000;
    }
}
