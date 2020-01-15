package com.periscope.assignment.service.impl;

import com.periscope.assignment.dto.RealmDto;
import com.periscope.assignment.entity.RealmEntity;
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

	public RealmServiceImpl(RealmRepository realmRepository) {
		this.realmRepository = realmRepository;
	}

	public RealmServiceImpl() {
	}

	@Override
	public RealmDto getById(long id) {
		RealmDto realmDto = new RealmDto();
		Optional<RealmEntity> realmEntityStream = realmRepository.findById(id);
		if (!realmEntityStream.isPresent()) {
			return null;
		}
		BeanUtils.copyProperties(realmEntityStream.get(), realmDto);
		return realmDto;
	}

	@Override
	public long deleteById(long id) {
		realmRepository.deleteById(id);
		realmRepository.flush();
		return id;
	}

	@Override
	public boolean existByName(String name) {
		return realmRepository.existByName(name);
	}

	@Override
	public RealmDto createRealm(RealmDto realmDto) {
		realmDto.setKey(realmDto.getName() + " | " + realmDto.getDescription());
		RealmEntity realmEntity = new RealmEntity();
		BeanUtils.copyProperties(realmDto, realmEntity);
		realmRepository.save(realmEntity);
		realmDto.setId(realmEntity.getId());
		return realmDto;
	}
}
