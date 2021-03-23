package com.quot.user.micro.service.test.files;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@ConfigurationProperties("users.files")
@Configuration()
public class FileProperties {

    private String userDirectory;

    public FileProperties() {
    }

    public FileProperties(String userPath) {
        if (!userPath.endsWith(File.separator)) userPath = userPath.concat(File.separator);
        this.userDirectory = userPath;
    }

    public String getUserDirectory() {
        return userDirectory;
    }

    public void setUserDirectory(String userPath) {
        if (!userPath.endsWith(File.separator)) userPath = userPath.concat(File.separator);
        this.userDirectory = userPath;
    }
}
