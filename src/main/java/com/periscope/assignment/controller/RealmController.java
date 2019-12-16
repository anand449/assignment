package com.periscope.assignment.controller;

import com.periscope.assignment.dto.RealmDto;
import com.periscope.assignment.exception.RealmNotFoundException;
import com.periscope.assignment.model.ErrorModel;
import com.periscope.assignment.model.RealmModel;
import com.periscope.assignment.service.RealmService;
import org.springframework.beans.BeanUtils;
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
        RealmDto realmDto = new RealmDto();
        if(!realmService.existByName(realmModel.getName())){
            BeanUtils.copyProperties(realmModel,realmDto);
            realmDto = realmService.createRealm(realmDto);
        }else{
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("DuplicateRealmName");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorModel);
        }
        RealmModel realmModelResponse = new RealmModel();
        BeanUtils.copyProperties(realmDto,realmModelResponse);
        return ResponseEntity.status(HttpStatus.CREATED).body(realmModelResponse);
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
        RealmDto realmDto = realmService.getById(longId);
        if(realmDto == null){
            ErrorModel errorModel = new ErrorModel();
            errorModel.setCode("RealmNotFound");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorModel);
        }
        RealmModel realmModelResponse = new RealmModel();
        BeanUtils.copyProperties(realmDto,realmModelResponse);
        return ResponseEntity.ok().body(realmModelResponse);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    private void realmNotFoundHandler(RealmNotFoundException e){

    }

}
