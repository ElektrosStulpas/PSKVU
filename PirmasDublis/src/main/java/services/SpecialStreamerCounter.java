package services;

import javax.enterprise.inject.Specializes;

//@Specializes
public class SpecialStreamerCounter extends TopStreamerCounter{

    public Integer countStreamersForGame(){
        return -1;
    }
}
