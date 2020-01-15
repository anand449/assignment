package com.periscope.assignment;
import static org.junit.jupiter.api.Assertions.assertEquals;
import com.periscope.assignment.model.RealmModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest(classes = AssignmentApplication.class,
		webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RealmControllerIntegrationTests {

	@LocalServerPort
	private int port;

	@Autowired
	private TestRestTemplate restTemplate;
	static long newId;


@Test
	public void createRealm() {
		RealmModel realmModel = new RealmModel("realmNameTest9", "realmDescTest");
		ResponseEntity<RealmModel> responseEntity = this.restTemplate
				.postForEntity("http://localhost:" + port + "/service/user/realm", realmModel, RealmModel.class);
		newId = responseEntity.getBody().getId();
		assertEquals(201, responseEntity.getStatusCodeValue());
	}

	@Test
	public void createRealm_DuplicateRealmName() {
		RealmModel realmModel = new RealmModel("realmNameTest9", "realmDescTest");
		realmModel.setDescription("realmDescTest");
		ResponseEntity<RealmModel> responseEntity = this.restTemplate
				.postForEntity("http://localhost:" + port + "/service/user/realm", realmModel, RealmModel.class);
		assertEquals(400, responseEntity.getStatusCodeValue());
	}

	@Test
	public void createRealm_InvalidRealmName() {
		RealmModel realmModel = new RealmModel();
		realmModel.setDescription("realmDescTest");
		ResponseEntity<RealmModel> responseEntity = this.restTemplate
				.postForEntity("http://localhost:" + port + "/service/user/realm", realmModel, RealmModel.class);
		newId = responseEntity.getBody().getId();
		assertEquals(400, responseEntity.getStatusCodeValue());
	}

	@Test
	public void getRealm() {
		ResponseEntity<String> responseEntity = this.restTemplate
				.getForEntity("http://localhost:" + port + "/service/user/realm/"+newId, String.class);
		assertEquals(200, responseEntity.getStatusCodeValue());
	}

	@Test
	public void getRealm_InvalidArgument() {
		ResponseEntity<String> responseEntity = this.restTemplate
				.getForEntity("http://localhost:" + port + "/service/user/realm/abc", String.class);
		assertEquals(400, responseEntity.getStatusCodeValue());
	}

	@Test
	public void deleteRealm() {
		final String uri = "http://localhost:" + port + "/service/user/realm/{id}";
		Map<String, Long> params = new HashMap<>();
		params.put("id", newId);
		this.restTemplate.delete(uri,params);
		ResponseEntity<String> responseEntity = this.restTemplate
				.getForEntity("http://localhost:" + port + "/service/user/realm/"+newId, String.class);
		assertEquals(404,responseEntity.getStatusCodeValue());
	}

	@Test
	public void getRealm_RealmNotFound() {
		ResponseEntity<String> responseEntity = this.restTemplate
				.getForEntity("http://localhost:" + port + "/service/user/realm/"+newId, String.class);
		assertEquals(404, responseEntity.getStatusCodeValue());
	}

}
