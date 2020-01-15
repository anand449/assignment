package com.periscope.assignment;

import com.periscope.assignment.model.RealmModel;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(classes = AssignmentApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class RealmControllerIntegrationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	static long newId;


	@Test
	@Order(1)
	public void createRealm() {
		RealmModel realmModel = new RealmModel("realmNameTest", "realmDescTest");
		ResponseEntity<RealmModel> responseEntity = this.restTemplate
				.postForEntity("http://localhost:" + port + "/service/user/realm", realmModel, RealmModel.class);
		newId = Objects.requireNonNull(responseEntity.getBody()).getId();
		assertEquals(201, responseEntity.getStatusCodeValue());
	}

	@Test
	@Order(2)
	public void createRealm_DuplicateRealmName() {
		RealmModel realmModel = new RealmModel("realmNameTest", "realmDescTest");
		realmModel.setDescription("realmDescTest");
		ResponseEntity<RealmModel> responseEntity = this.restTemplate
				.postForEntity("http://localhost:" + port + "/service/user/realm", realmModel, RealmModel.class);
		assertEquals(400, responseEntity.getStatusCodeValue());
	}

	@Test
	@Order(3)
	public void createRealm_InvalidRealmName() {
		RealmModel realmModel = new RealmModel();
		realmModel.setDescription("realmDescTest");
		ResponseEntity<RealmModel> responseEntity = this.restTemplate
				.postForEntity("http://localhost:" + port + "/service/user/realm", realmModel, RealmModel.class);
		assertEquals(400, responseEntity.getStatusCodeValue());
	}

	@Test
	@Order(4)
	public void getRealm() {
		ResponseEntity<String> responseEntity = this.restTemplate
				.getForEntity("http://localhost:" + port + "/service/user/realm/" + newId, String.class);
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@Test
	@Order(5)
	public void getRealm_InvalidArgument() {
		ResponseEntity<String> responseEntity = this.restTemplate
				.getForEntity("http://localhost:" + port + "/service/user/realm/abc", String.class);
		assertEquals(400, responseEntity.getStatusCodeValue());
	}

	@Test
	@Order(6)
	public void deleteRealm() {
		final String uri = "http://localhost:" + port + "/service/user/realm/" + newId;
		this.restTemplate.delete(uri);
		ResponseEntity<String> responseEntity = this.restTemplate
				.getForEntity("http://localhost:" + port + "/service/user/realm/" + newId, String.class);
		assertEquals(404, responseEntity.getStatusCodeValue());
	}

	@Test
	@Order(7)
	public void getRealm_RealmNotFound() {
		ResponseEntity<String> responseEntity = this.restTemplate
				.getForEntity("http://localhost:" + port + "/service/user/realm/" + newId, String.class);
		assertEquals(404, responseEntity.getStatusCodeValue());
	}

}
