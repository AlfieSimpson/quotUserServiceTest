package com.quot.user.micro.service.test.users;

import com.quot.user.micro.service.test.users.model.User;
import com.quot.user.micro.service.test.users.model.UserId;
import com.quot.user.micro.service.test.users.model.UserMetadata;
import com.quot.user.micro.service.test.users.repository.UserRepository;
import com.quot.user.micro.service.test.users.validators.UserIdValidator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@RunWith(SpringRunner.class)
public class UserServiceTest {

    private UserService userService;

    @Mock
    private UserRepository userRepository;
    @Mock
    private UserIdValidator userIdValidator;

    private static final String EXAMPLE_STRING = " . ";
    private static final UserId EXAMPLE_USER_ID = new UserId(EXAMPLE_STRING, EXAMPLE_STRING);
    private static final UserMetadata EXAMPLE_USER_METADATA = new UserMetadata(EXAMPLE_STRING, EXAMPLE_STRING, EXAMPLE_STRING);
    private static final User EXAMPLE_USER = new User(EXAMPLE_USER_ID, EXAMPLE_USER_METADATA);

    private static final UserId INVALID_USER_ID = new UserId("","");
    private static final UserMetadata INVALID_USER_METADATA = new UserMetadata("","", "");
    private static final User INVALID_USER = new User(INVALID_USER_ID, INVALID_USER_METADATA);

    @Before
    public void setup(){
        userService = new UserService(userRepository, userIdValidator);

        when(userIdValidator.isValid(EXAMPLE_USER_ID)).thenReturn(true);
        when(userIdValidator.isValid(INVALID_USER_ID)).thenReturn(false);
    }

    @Test
    public void updateTest(){
        try {
            userService.update(EXAMPLE_USER);
            verify(userRepository).update(eq(EXAMPLE_USER));
            verify(userIdValidator).isValid(eq(EXAMPLE_USER_ID));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void updateExceptionTest(){
        try {
            userService.update(INVALID_USER);
            verify(userRepository, never()).update(any());
            verify(userIdValidator).isValid(eq(INVALID_USER_ID));
            fail();
        } catch (Exception e) {
        }
    }

    @Test
    public void readTest(){
        try {
            userService.get(EXAMPLE_USER_ID);
            verify(userRepository).get(eq(EXAMPLE_USER_ID));
            verify(userIdValidator).isValid(eq(EXAMPLE_USER_ID));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void readExceptionTest(){
        try {
            userService.get(INVALID_USER_ID);
            verify(userRepository, never()).get(eq(EXAMPLE_USER_ID));
            verify(userIdValidator).isValid(eq(INVALID_USER_ID));
            fail();
        } catch (Exception e) {
        }
    }

    @Test
    public void saveTest() throws Exception {
        userService.save(EXAMPLE_USER);
        verify(userRepository).save(eq(EXAMPLE_USER));
        verify(userIdValidator).isValid(eq(EXAMPLE_USER_ID));
    }

    @Test
    public void saveExceptionTest(){
        try {
            userService.save(INVALID_USER);
            verify(userRepository, never()).save(any());
            verify(userIdValidator).isValid(eq(INVALID_USER_ID));
            fail();
        } catch (Exception e) {
        }
    }

    @Test
    public void deleteTest(){
        try {
            userService.delete(EXAMPLE_USER_ID);
            verify(userRepository).delete(eq(EXAMPLE_USER_ID));
            verify(userIdValidator).isValid(eq(EXAMPLE_USER_ID));
        } catch (Exception e) {
            fail();
        }
    }

    @Test
    public void deleteExceptionTest(){
        try {
            userService.delete(INVALID_USER_ID);
            verify(userRepository, never()).delete(eq(EXAMPLE_USER_ID));
            verify(userIdValidator).isValid(eq(INVALID_USER_ID));
            fail();
        } catch (Exception e) {
        }
    }


}
