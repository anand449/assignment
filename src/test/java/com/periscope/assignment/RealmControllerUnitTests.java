package com.periscope.assignment;

import com.periscope.assignment.controller.RealmController;
import com.periscope.assignment.dto.RealmDto;
import com.periscope.assignment.exception.InvalidArgumentException;
import com.periscope.assignment.exception.RealmBadRequestException;
import com.periscope.assignment.exception.RealmNotFoundException;
import com.periscope.assignment.model.RealmModel;
import com.periscope.assignment.service.RealmService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.platform.runner.JUnitPlatform;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.util.Objects;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RunWith(JUnitPlatform.class)
public class RealmControllerUnitTests {

    @InjectMocks
    RealmController realmController;

    @Mock
    RealmService realmService;

    @Test
    public void createRealm() throws RealmBadRequestException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        RealmDto mockRealm = new RealmDto();
        when(realmService.createRealm(any(RealmDto.class))).thenReturn(mockRealm);
        RealmModel newRealm = new RealmModel();
        newRealm.setName("realmNameU1");
        newRealm.setDescription("realmDescriptionU1");
        newRealm.setKey("realmNameU1 | realmDescriptionU1");
        ResponseEntity<Object> responseEntity = realmController.createRealm(newRealm);
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(201);
    }

    @Test
    public void getRealm() throws RealmNotFoundException, InvalidArgumentException {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        long newId = 1;
        RealmDto newRealm = new RealmDto();
        newRealm.setId(newId);
        newRealm.setName("realmNameU2");
        newRealm.setDescription("realmDescriptionU2");
        realmService.createRealm(newRealm);
        when(realmService.getById(newId)).thenReturn(newRealm);
        ResponseEntity<Object> responseEntity = realmController.getRealm(String.valueOf(newId));
        assertThat(responseEntity.getStatusCodeValue()).isEqualTo(200);
        assertThat(((RealmModel) Objects.requireNonNull(responseEntity.getBody())).getKey())
                .isEqualTo(newRealm.getKey());
    }
}