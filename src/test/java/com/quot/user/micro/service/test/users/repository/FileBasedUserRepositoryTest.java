package com.quot.user.micro.service.test.users.repository;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.quot.user.micro.service.test.files.FileFactory;
import com.quot.user.micro.service.test.files.FileProperties;
import com.quot.user.micro.service.test.files.FileService;
import com.quot.user.micro.service.test.users.model.User;
import com.quot.user.micro.service.test.users.model.UserId;
import com.quot.user.micro.service.test.users.model.UserMetadata;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FileBasedUserRepositoryTest {

    private FileBasedUserRepository fileBasedUserRepository;

    @Mock
    private FileProperties fileProperties;

    @Mock
    private FileService fileService;

    @Mock
    private File userStoringDirectory;

    @Mock
    private File userDataFile;

    @Mock
    private FileFactory fileFactory;

    private static final String USER_DIRECTORY = "/mnt/documents";
    private static final String EXAMPLE_STRING = " . ";
    private static final UserId EXAMPLE_USER_ID = new UserId(EXAMPLE_STRING, EXAMPLE_STRING);
    private static final UserMetadata EXAMPLE_USER_METADATA = new UserMetadata(EXAMPLE_STRING, EXAMPLE_STRING, EXAMPLE_STRING);
    private static final User EXAMPLE_USER = new User(EXAMPLE_USER_ID, EXAMPLE_USER_METADATA);
    private static final String FILENAME = EXAMPLE_STRING+"."+EXAMPLE_STRING;


    @Before
    public void setup() throws Exception {
        when(fileProperties.getUserDirectory()).thenReturn(USER_DIRECTORY);
        when(fileFactory.newFile(USER_DIRECTORY)).thenReturn(userStoringDirectory);
        fileBasedUserRepository = new FileBasedUserRepository(fileProperties, fileService, fileFactory);
    }

    @Test
    public void testFileGet() throws Exception {
        when(userStoringDirectory.getPath()).thenReturn(USER_DIRECTORY);
        when(fileFactory.newFile(any(), any())).thenReturn(userDataFile);
        when(userDataFile.exists()).thenReturn(true);
        String jsonUser = new ObjectMapper().writeValueAsString(EXAMPLE_USER);

        when(fileService.read(userDataFile)).thenReturn(jsonUser);

        User user = fileBasedUserRepository.get(EXAMPLE_USER_ID);

        assertThat(EXAMPLE_USER).isEqualTo(user);
    }
    @Test
    public void testFileDelete() throws Exception {
        when(userStoringDirectory.getPath()).thenReturn(USER_DIRECTORY);
        when(fileFactory.newFile(any(), any())).thenReturn(userDataFile);
        when(userDataFile.exists()).thenReturn(true);
        when(userDataFile.delete()).thenReturn(true);

        Boolean deleted = fileBasedUserRepository.delete(EXAMPLE_USER_ID);

        assertThat(deleted).isTrue();
    }

    @Test
    public void testFileSave() throws Exception {
        when(userStoringDirectory.getPath()).thenReturn(USER_DIRECTORY);
        when(fileFactory.newFile(any(), any())).thenReturn(userDataFile);
        when(userDataFile.exists()).thenReturn(true);
        when(userDataFile.delete()).thenReturn(true);

        String jsonUser = new ObjectMapper().writeValueAsString(EXAMPLE_USER);

        when(fileService.read(userDataFile)).thenReturn(jsonUser);
        User user = fileBasedUserRepository.save(EXAMPLE_USER);

        assertThat(EXAMPLE_USER).isEqualTo(user);
    }

    @Test
    public void testFileUpdate() throws Exception {
        when(userStoringDirectory.getPath()).thenReturn(USER_DIRECTORY);
        when(fileFactory.newFile(any(), any())).thenReturn(userDataFile);
        when(userDataFile.exists()).thenReturn(true);
        when(userDataFile.delete()).thenReturn(true);

        String jsonUser = new ObjectMapper().writeValueAsString(EXAMPLE_USER);

        when(fileService.read(userDataFile)).thenReturn(jsonUser);
        User user = fileBasedUserRepository.save(EXAMPLE_USER);

        assertThat(EXAMPLE_USER).isEqualTo(user);
    }




}
