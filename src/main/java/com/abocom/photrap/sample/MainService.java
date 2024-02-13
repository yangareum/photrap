package com.abocom.photrap.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MainService {

    @Autowired
    private MainRepository mainRepository;


    public List getList(){
        return mainRepository.getList();
    }

    public User get(String id){
        return mainRepository.getById(id);
    }
}
