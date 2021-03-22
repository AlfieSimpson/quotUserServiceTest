package com.quot.user.micro.service.test.files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class FileService {

    private FileReader fileReader;
    private FileOutputter fileOutputter;

    @Autowired
    public FileService(FileReader fileReader, FileOutputter fileOutputter){
        this.fileReader = fileReader;
        this.fileOutputter = fileOutputter;
    }

    public String read(String path) throws IOException {
        return read(new File(path));
    }

    public String read(File file) throws IOException {
        return fileReader.read(file);
    }

    public void write(String contents, String path) throws IOException {
        fileOutputter.write(contents, path);
    }
}
