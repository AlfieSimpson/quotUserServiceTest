package com.quot.user.micro.service.test.users.repository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
public class UserRepositoryConfigTest {

    private UserRepositoryConfig userRepositoryConfig;

    @Mock
    private FileBasedUserRepository fileBasedUserRepository;

    @Before
    public void setup(){
        userRepositoryConfig = new UserRepositoryConfig(fileBasedUserRepository);
    }

    @Test
    public void fileBasedUserRepositoryReturnTest(){
        UserRepository userRepository = userRepositoryConfig.userRepository();
        assertThat(fileBasedUserRepository).isEqualTo(userRepository);
    }
}
