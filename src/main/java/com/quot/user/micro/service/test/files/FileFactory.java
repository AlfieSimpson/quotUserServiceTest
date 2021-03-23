package com.quot.user.micro.service.test.files;

import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class FileFactory {

    public File newFile(String path){
        return new File(path);
    }

    public File newFile(String parent, String child){
        return new File(parent, child);
    }


}
