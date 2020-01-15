package com.periscope.assignment.service;

import com.periscope.assignment.dto.RealmDto;
import com.periscope.assignment.exception.RealmNotFoundException;

public interface RealmService {
    RealmDto getById(long id);
    RealmDto createRealm(RealmDto realmDto);
    long deleteById(long id);
    boolean existByName(String name);
}
