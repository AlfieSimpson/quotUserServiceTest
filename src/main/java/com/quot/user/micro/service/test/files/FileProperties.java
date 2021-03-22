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

    public FileProperties(String userpath) {
        if (!userpath.endsWith("/") && !userpath.endsWith("\\") ) userpath.concat(File.separator);
        this.userDirectory = userpath;
    }

    public String getUserDirectory() {
        return userDirectory;
    }

    public void setUserDirectory(String userDirectory) {
        this.userDirectory = userDirectory;
    }
}
