package com.periscope.assignment;

import com.periscope.assignment.model.RealmModel;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class IntegrationTest {
    public static final String GET_REALM_URI = "/service/user/realm/";
    @Autowired
    TestRestTemplate testRestTemplate;
    @Test
    public void getRealmTest_Success() throws Exception{
        int realmId = 6;
        ResponseEntity<RealmModel> responseEntity = testRestTemplate.getForEntity(GET_REALM_URI+realmId, RealmModel.class);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(responseEntity.getBody().getId()).isEqualTo(6);
        assertThat(responseEntity.getBody().getName()).isEqualTo("realmName");
        assertThat(responseEntity.getBody().getDescription()).isEqualTo("realmDescription");
        assertThat(responseEntity.getBody().getKey()).isEqualTo("realmName | realmDescription");

    }
}
