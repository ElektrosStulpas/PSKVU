package services;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Alternative;
import java.io.Serializable;
import java.util.Random;

@Alternative
@ApplicationScoped
public class IndieStreamerCounter implements Serializable, StreamerCounter {

    public Integer countStreamersForGame() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
        }
        return new Random().nextInt(2000);
    }
}
