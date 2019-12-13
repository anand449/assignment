package com.periscope.assignment.repository;

import com.periscope.assignment.entity.RealmEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository("realmRepository")
public interface RealmRepository extends  JpaRepository<RealmEntity, Long>{
    @Query("SELECT CASE WHEN COUNT(r) > 0 THEN true ELSE false END FROM RealmEntity r WHERE r.name = :name")
    boolean existByName(@Param("name") String name);
}
