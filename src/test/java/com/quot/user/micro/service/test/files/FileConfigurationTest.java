package com.quot.user.micro.service.test.files;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(SpringRunner.class)
public class FileConfigurationTest {

    private FileConfiguration fileConfiguration;

    @Mock
    private FileProperties fileProperties;
    @Mock
    private FileFactory fileFactory;
    @Mock
    private File storageDir;

    private static final String FILE_DIRECTORY = "/example/";

    @Before
    public void setup(){
        when(fileProperties.getUserDirectory()).thenReturn(FILE_DIRECTORY);
        when(fileFactory.newFile(FILE_DIRECTORY)).thenReturn(storageDir);
    }

    @Test
    public void storageMadeOnConstructionTest(){
        when(storageDir.exists()).thenReturn(false);
        fileConfiguration = new FileConfiguration(fileProperties, fileFactory);
        verify(storageDir).mkdir();

    }

}
