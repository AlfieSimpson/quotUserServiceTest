package com.quot.user.micro.service.test.users;

import com.quot.user.micro.service.test.files.FileProperties;
import com.quot.user.micro.service.test.users.model.User;
import com.quot.user.micro.service.test.users.repository.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.event.annotation.AfterTestMethod;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.io.File;
import java.io.IOException;
import java.net.URI;

import static com.quot.user.micro.service.test.testutils.JsonStringify.asJsonString;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment= SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FileProperties fileProperties;

    @LocalServerPort
    int randomServerPort;

    private static final String EXAMPLE_STRING = "EXAMPLE_STRING";

    private static final User VALID_USER = new User(EXAMPLE_STRING, EXAMPLE_STRING, EXAMPLE_STRING, EXAMPLE_STRING, EXAMPLE_STRING);
    private static final User INVALID_USER = new User(EXAMPLE_STRING, "", EXAMPLE_STRING, EXAMPLE_STRING, EXAMPLE_STRING);
    private static final User CHANGED_USER = new User(EXAMPLE_STRING, EXAMPLE_STRING, EXAMPLE_STRING, "bob", EXAMPLE_STRING);

    @Test
    public void intialSaveSuccessfulTest() throws Exception {

        final String baseUrl = "http://localhost:"+randomServerPort+"/user/";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<User> request = new HttpEntity<>(VALID_USER, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(asJsonString(VALID_USER));

        User user = userRepository.get(VALID_USER.getId());
        assertThat(user).isNotNull();
        assertEquals(asJsonString(user), asJsonString(VALID_USER));

    }

    @Test
    public void invalidUserSaveUnsuccessfulTest() throws Exception {

        final String baseUrl = "http://localhost:"+randomServerPort+"/user/";
        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<User> request = new HttpEntity<>(INVALID_USER, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, request, String.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
    }

    @Test
    public void saveAndGetSuccessfulTest() throws Exception {

        final String baseUrl = "http://localhost:"+randomServerPort+"/user";

        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<User> request = new HttpEntity<>(VALID_USER, headers);

        this.restTemplate.postForEntity(uri, request, String.class);

        uri = new URI(baseUrl.concat("/").concat(VALID_USER.getId().getUserId()).concat("/").concat(VALID_USER.getId().getClientId()));

        ResponseEntity<String> result = this.restTemplate.getForEntity(uri, String.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(asJsonString(VALID_USER));

        User user = userRepository.get(VALID_USER.getId());
        assertThat(user).isNotNull();
        assertEquals(asJsonString(user), asJsonString(VALID_USER));

    }

    @Test
    public void saveAndUpdateSuccessfulTest() throws Exception {

        final String baseUrl = "http://localhost:"+randomServerPort+"/user/";

        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<User> request = new HttpEntity<>(VALID_USER, headers);

        this.restTemplate.postForEntity(uri, request, String.class);

        request = new HttpEntity<>(CHANGED_USER, headers);

        ResponseEntity<String> result = this.restTemplate.postForEntity(uri, CHANGED_USER,String.class);
        assertThat(result.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(result.getBody()).isEqualTo(asJsonString(CHANGED_USER));

        User user = userRepository.get(CHANGED_USER.getId());
        assertThat(user).isNotNull();
        assertEquals(asJsonString(user), asJsonString(CHANGED_USER));

    }

    @Test
    public void saveAndDeleteSuccessfulTest() throws Exception {

        final String baseUrl = "http://localhost:"+randomServerPort+"/user";

        URI uri = new URI(baseUrl);
        HttpHeaders headers = new HttpHeaders();

        HttpEntity<User> request = new HttpEntity<>(VALID_USER, headers);

        this.restTemplate.postForEntity(uri, request, String.class);

        uri = new URI(baseUrl.concat("/").concat(VALID_USER.getId().getUserId()).concat("/").concat(VALID_USER.getId().getClientId()));

        request = new HttpEntity<>(CHANGED_USER, headers);

        this.restTemplate.delete(uri);

        try {
            User user = userRepository.get(CHANGED_USER.getId());
            fail();
        } catch(IOException e){

        }

    }


    @After
    public void cleanStorage(){

        String path = fileProperties.getUserDirectory();
        File storagePath = new File(path);
        storagePath.delete();
        storagePath.mkdir();
    }


}
