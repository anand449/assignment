package com.periscope.assignment.service;

import com.periscope.assignment.dto.RealmDto;

public interface RealmService {
    RealmDto getById(long id);
    RealmDto createRealm(RealmDto realmDto);
    boolean existByName(String name);
}
