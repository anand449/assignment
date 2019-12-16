package com.periscope.assignment;

import com.periscope.assignment.controller.RealmController;
import com.periscope.assignment.dto.RealmDto;
import com.periscope.assignment.exception.RealmNotFoundException;
import com.periscope.assignment.model.RealmModel;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.mockito.BDDMockito.given;

import com.periscope.assignment.service.RealmService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

@RunWith(SpringRunner.class)
@WebMvcTest(RealmController.class)
public class RealmControllerTest {
    public static final String GET_REALM_URI = "/service/user/realm/";
    @Autowired
    MockMvc mockMvc;
    @MockBean
    RealmService realmService;

    @Test
    public void getRealm_Success() throws Exception{
        int realmId = 6;
        given(realmService.getById(realmId)).willReturn(new RealmDto(realmId,"realmName","realmDescription","realmName | realmDescription"));
       mockMvc.perform(MockMvcRequestBuilders.get(GET_REALM_URI + realmId))
               .andExpect(status().isOk())
               .andExpect(jsonPath("id").value("6"))
               .andExpect(jsonPath("name").value("realmName"))
               .andExpect(jsonPath("description").value("realmDescription"))
               .andExpect(jsonPath("key").value("realmName | realmDescription"));
    }

    @Test
    public void getRealm_notFound() throws Exception{
        int realmId = 6;
        given(realmService.getById(realmId)).willThrow(new RealmNotFoundException());
        mockMvc.perform(MockMvcRequestBuilders.get(GET_REALM_URI + realmId))
                .andExpect(status().isNotFound());
    }
}
