package com.periscope.assignment.service.impl;

import com.periscope.assignment.entity.RealmEntity;
import com.periscope.assignment.model.RealmModel;
import com.periscope.assignment.repository.RealmRepository;
import com.periscope.assignment.service.RealmService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("realmService")
public class RealmServiceImpl implements RealmService {
    @Autowired
    RealmRepository realmRepository;
    @Override
    public RealmModel getById(long id){
        RealmModel realmModel = new RealmModel();
        Optional<RealmEntity> realmEntityStream = realmRepository.findById(id);
        if(!realmEntityStream.isPresent()){
            return null;
        }
        BeanUtils.copyProperties(realmEntityStream.get(), realmModel);
        return realmModel;
    }

    @Override
    public boolean existByName(String name){
        return realmRepository.existByName(name);
    }

    @Override
    public RealmModel createStore(RealmModel realmModel) {
        realmModel.setKey(realmModel.getName() +" | "+ realmModel.getDescription());
        RealmEntity realmEntity = new RealmEntity();
        BeanUtils.copyProperties(realmModel,realmEntity);
        realmRepository.save(realmEntity);
        realmModel.setId(realmEntity.getId());
        return realmModel;
    }
}
