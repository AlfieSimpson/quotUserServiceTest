package com.quot.user.micro.service.test.files;

import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Component
public class FileReader {

    public String read(File file) throws IOException {

        StringBuilder contentBuilder = new StringBuilder();

        Files.lines( Paths.get(file.getPath()))
                .forEach(s -> contentBuilder.append(s).append("\n"));

        return contentBuilder.toString();
    }
}
