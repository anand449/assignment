package com.periscope.assignment;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import com.periscope.assignment.entity.RealmEntity;
import com.periscope.assignment.model.RealmModel;
import com.periscope.assignment.repository.RealmRepository;
import com.periscope.assignment.service.RealmService;
import org.junit.jupiter.api.Test;

import org.junit.runner.RunWith;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class AssignmentApplicationTests {

	@Autowired
	RealmService realmService;

	@MockBean
	RealmRepository realmRepository;

	/*@Test
	public void getRealmTest_Success(){
		long realmId = 6L;
		RealmModel realmModel = new RealmModel();
		realmModel.setId(6L);
		realmModel.setName("realmName");
		realmModel.setDescription("realmDescription");
		realmModel.setKey("realmName | realmDescription");
		RealmEntity realmEntity = realmRepository.findById(realmId).get();
		System.out.println("Anand - " + realmEntity.getId());
		RealmModel repoRealmModel = new RealmModel();
		BeanUtils.copyProperties(realmEntity, repoRealmModel);
		when(repoRealmModel).thenReturn(realmModel);
		assertEquals(realmModel,realmService.getById(realmId));
	}*/

	/*@Test
	public void createRealmTest_Success(){
		RealmModel realmModel = new RealmModel();
		realmModel.setId(21L);
		realmModel.setName("createRealm - Success Test");
		realmModel.setDescription("createRealm - Success Description");
		realmModel.setKey("createRealm - Success Test | Success Description");

		RealmEntity realmEntity = new RealmEntity();
		realmEntity.setName("createRealm - Success Test");
		realmEntity.setDescription("createRealm - Success Description");
		realmEntity.setKey("createRealm - Success Test | Success Description");
		realmRepository.save(realmEntity);

		System.out.println("Anand - " + realmEntity.getId());
		RealmModel repoRealmModel = new RealmModel();
		BeanUtils.copyProperties(realmRepository.findById(realmEntity.getId()).get(), repoRealmModel);
		when(repoRealmModel).thenReturn(realmModel);
		assertEquals(realmModel,realmService.createRealm(realmModel));
	}*/
	/*@Test
	void contextLoads() {
	}*/

}
