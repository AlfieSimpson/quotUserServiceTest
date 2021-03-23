package com.quot.user.micro.service.test.files;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
public class FilePropertiesTest {

    private FileProperties fileProperties;

    private static final String ENDING_CORRECT = "b"+ File.separator;
    private static final String ENDING_INCORRECT = "b";

    @Before
    public void setup(){
        fileProperties = new FileProperties();
    }

    @Test
    public void endingChangedOnlyWhenNeededTest(){

        fileProperties.setUserDirectory(ENDING_INCORRECT);
        String userDir = fileProperties.getUserDirectory();
        assertThat(userDir).isEqualTo(ENDING_CORRECT);

        fileProperties.setUserDirectory(ENDING_CORRECT);
        userDir = fileProperties.getUserDirectory();
        assertThat(userDir).isEqualTo(ENDING_CORRECT);
    }
}
