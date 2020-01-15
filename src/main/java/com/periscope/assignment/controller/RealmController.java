package com.periscope.assignment.controller;

import com.periscope.assignment.dto.RealmDto;
import com.periscope.assignment.exception.InvalidArgumentException;
import com.periscope.assignment.exception.RealmBadRequestException;
import com.periscope.assignment.exception.RealmNotFoundException;
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

	@PostMapping(value = "/realm", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Object> createRealm(@RequestBody RealmModel realmModel) throws RealmBadRequestException {
		if (realmModel.getName() == null || realmModel.getName().isEmpty()) {
			throw new RealmBadRequestException("InvalidRealmName");
		}
		RealmDto realmDto = new RealmDto();
		if (realmService.existByName(realmModel.getName())) {
			throw new RealmBadRequestException("DuplicateRealmName");
		}
		BeanUtils.copyProperties(realmModel, realmDto);
		realmDto = realmService.createRealm(realmDto);
		RealmModel realmModelResponse = new RealmModel();
		BeanUtils.copyProperties(realmDto, realmModelResponse);
		return ResponseEntity.status(HttpStatus.CREATED).body(realmModelResponse);
	}

	@GetMapping(value = "/realm/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Object> getRealm(@PathVariable(value = "id") String id) throws RealmNotFoundException, InvalidArgumentException {
		Long longId;
		try {
			longId = Long.parseLong(id);
		} catch (NumberFormatException e) {
			throw new InvalidArgumentException("InvalidArgument");
		}
		RealmDto realmDto = realmService.getById(longId);
		if (realmDto == null || realmDto.getName().isEmpty()) {
			throw new RealmNotFoundException("RealmNotFound");
		}
		RealmModel realmModelResponse = new RealmModel();
		BeanUtils.copyProperties(realmDto, realmModelResponse);
		return ResponseEntity.ok().body(realmModelResponse);
	}

	@DeleteMapping(value = "/realm/{id}", produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public ResponseEntity<Long> deleteRealm(@PathVariable(value = "id") String id) throws RealmNotFoundException, InvalidArgumentException {
		Long longId;
		try {
			longId = Long.parseLong(id);
		} catch (NumberFormatException e) {
			throw new InvalidArgumentException("InvalidArgument");
		}
		RealmDto realmDto = realmService.getById(longId);
		if (realmDto == null || realmDto.getName().isEmpty()) {
			throw new RealmNotFoundException("RealmNotFound");
		}
		realmService.deleteById(longId);
		return ResponseEntity.ok().body(longId);
	}

}
