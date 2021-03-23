package com.quot.user.micro.service.test.files;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.File;

@Configuration
public class FileConfiguration {

    private static Logger LOGGER = LoggerFactory.getLogger(FileConfiguration.class);

    private static final String NEW_FOLDER_CREATED = "The configured directory (%s) to store Users seems to be missing. Will try to create";


    @Autowired
    public FileConfiguration(FileProperties fileProperties, FileFactory fileFactory){
        String pathToUserFiles = fileProperties.getUserDirectory();

        File userFileDirectory = fileFactory.newFile(pathToUserFiles);
        if (!userFileDirectory.exists()) {
            LOGGER.info(String.format(NEW_FOLDER_CREATED, pathToUserFiles));
            userFileDirectory.mkdir();
        }
    }
}
