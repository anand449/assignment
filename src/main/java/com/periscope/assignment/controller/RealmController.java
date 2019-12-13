package com.periscope.assignment.controller;

import com.periscope.assignment.model.ErrorModel;
import com.periscope.assignment.model.RealmModel;
import com.periscope.assignment.service.RealmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/service/user")
public class RealmController {
    @Autowired
    RealmService realmService;
    @PostMapping(value = "/realm",produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
    public ResponseEntity<Object> createRealm(@RequestBody RealmModel realmModel){
        if(realmModel.getName()==null || realmModel.getName().isEmpty()){
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("InvalidRealmName");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModel);
        }
        if(!realmService.existByName(realmModel.getName())){
            realmModel = realmService.createStore(realmModel);
        }else{
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("DuplicateRealmName");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModel);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(realmModel);
    }

    @GetMapping(value = "/realm/{id}")
    public ResponseEntity<Object> getRealm(@PathVariable(value = "id") String id){
        long longId = 0;
        try {
            longId = Long.parseLong(id);
        }catch (Exception e){
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("InvalidArgument");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModel);
        }
        RealmModel realmModel = realmService.getById(longId);
        if(realmModel == null){
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("RealmNotFound");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorModel);
        }
        return ResponseEntity.ok().body(realmModel);
    }

}
