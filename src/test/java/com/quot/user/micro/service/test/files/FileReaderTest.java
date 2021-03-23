package com.quot.user.micro.service.test.files;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;
import org.powermock.modules.junit4.PowerMockRunnerDelegate;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.powermock.api.mockito.PowerMockito.mock;

@RunWith(PowerMockRunner.class)
@PowerMockRunnerDelegate(SpringJUnit4ClassRunner.class)
@PrepareForTest(FileReader.class)
public class FileReaderTest {

    private FileReader fileReader;
    private static final String PATH_STR = "/bob.b";
    private static final Path PATH = Path.of(PATH_STR);

    private static final String LINES_STR = "hello world";
    private static final Stream<String> LINES = List.of(LINES_STR).stream();
    private static final String OUTPUT_STR = LINES_STR+"\n";

    private File mockFile = mock(File.class);

    @Before
    public void setup(){
        fileReader = new FileReader();
    }


    @Test
    public void readFileTest() throws IOException {
        PowerMockito.mockStatic(Files.class);
        PowerMockito.mockStatic(Paths.class);
        PowerMockito.when(mockFile.getPath()).thenReturn(PATH_STR);
        PowerMockito.when(Paths.get(PATH_STR)).thenReturn(PATH);
        PowerMockito.when(Files.lines(PATH)).thenReturn(LINES);

        String output = fileReader.read(mockFile);

        assertThat(output).isEqualTo(OUTPUT_STR);
    }



}
