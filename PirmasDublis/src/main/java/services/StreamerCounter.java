package services;

import javax.enterprise.context.ApplicationScoped;
import java.io.Serializable;
import java.util.Random;

@ApplicationScoped
public class StreamerCounter implements Serializable {

    public Integer countStreamersForGame() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
        }
        return new Random().nextInt(10000);
    }
}
