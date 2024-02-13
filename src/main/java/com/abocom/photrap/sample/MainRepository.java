package com.abocom.photrap.sample;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class MainRepository {
    public List getList(){
        return new ArrayList();
    };

    public User getById(String id){
        return new User("yarm", "wizard", null);
    };
}
