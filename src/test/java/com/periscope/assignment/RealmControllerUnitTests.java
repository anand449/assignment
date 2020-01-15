package com.periscope.assignment;

import com.periscope.assignment.dto.RealmDto;
import com.periscope.assignment.entity.RealmEntity;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import com.periscope.assignment.repository.RealmRepository;
import com.periscope.assignment.service.RealmService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Optional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration( classes = TestConfiguration.class )
public class RealmControllerUnitTests {
    @Mock
    RealmRepository realmRepository;
    @Autowired
    @InjectMocks
    RealmService realmService;
    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void createRealm() {
        RealmEntity realmEntity = new RealmEntity();
        realmEntity.setName("realmNameU1");
        realmEntity.setDescription("realmDescU1");
        realmEntity.setKey("realmNameU1 | realmDescU1");
        when( realmRepository.save(any(RealmEntity.class) ) ).thenReturn(realmEntity );
        RealmDto realmDto = new RealmDto();
        realmDto.setName("realmNameU1");
        realmDto.setDescription("realmDescU1");
        realmDto.setKey("realmNameU1 | realmDescU1");
        RealmDto result = realmService.createRealm(realmDto);
        // Assert expected results
        Assert.assertNotNull( result );
        Assert.assertEquals( realmEntity.getName() , result.getName() );
        Assert.assertEquals( realmEntity.getDescription() , result.getDescription() );
        Assert.assertEquals( realmEntity.getKey() , result.getKey() );
    }

    @Test
    public void getRealm() {
        RealmEntity realmEntity = new RealmEntity();
        realmEntity.setName("realmNameU1");
        realmEntity.setDescription("realmDescU1");
        realmEntity.setKey("realmNameU1 | realmDescU1");
        when( realmRepository.findById(any(Long.class) ) ).thenReturn(Optional.of(realmEntity) );
        RealmDto result = realmService.getById(5);
        // Assert expected results
        Assert.assertNotNull( result );
        Assert.assertEquals( realmEntity.getName() , result.getName() );
        Assert.assertEquals( realmEntity.getDescription() , result.getDescription() );
        Assert.assertEquals( realmEntity.getKey() , result.getKey() );
    }



}
