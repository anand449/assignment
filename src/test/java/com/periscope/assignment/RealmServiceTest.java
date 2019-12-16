package com.periscope.assignment;

import com.periscope.assignment.dto.RealmDto;
import com.periscope.assignment.entity.RealmEntity;
import com.periscope.assignment.exception.RealmNotFoundException;
import com.periscope.assignment.repository.RealmRepository;
import com.periscope.assignment.service.RealmService;
import com.periscope.assignment.service.impl.RealmServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.BeanUtils;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(MockitoJUnitRunner.class)
public class RealmServiceTest {
    @Mock
    private RealmRepository realmRepository;
    private RealmService realmService;

    @Before
    public void setUp() throws Exception{
        realmService = new RealmServiceImpl(realmRepository);
    }

    @Test
    public void getRealm_Success() throws Exception{
        long realmId = 6L;
        Optional<RealmEntity> realmEntityStream = realmRepository.findById(realmId);
        System.out.println("realmRepository.findById(anyLong()" + realmEntityStream);
        if(realmEntityStream.isPresent()){
            RealmDto realmDtoRepo = new RealmDto();
            BeanUtils.copyProperties(realmEntityStream.get(), realmDtoRepo);
            given(realmDtoRepo).willReturn(new RealmDto(realmId,"realmName","realmDescription","realmName | realmDescription"));
        }
        RealmDto realmDto = realmService.getById(realmId);
        assertThat(realmDto.getId()).isEqualTo(6);
        assertThat(realmDto.getName()).isEqualTo("realmName");
        assertThat(realmDto.getDescription()).isEqualTo("realmDescription");
        assertThat(realmDto.getKey()).isEqualTo("realmName | realmDescription");
    }

    @Test(expected = RealmNotFoundException.class)
    public void getRealm_notFound() throws Exception{
        //int realmId = 6;
        given(realmService.getById(anyLong())).willThrow(new RealmNotFoundException());
    }
}
