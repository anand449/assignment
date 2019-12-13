package com.periscope.assignment.service;

import com.periscope.assignment.model.RealmModel;

public interface RealmService {
    RealmModel getById(long id);
    RealmModel createStore(RealmModel realmModel);
    boolean existByName(String name);
}
