package com.quot.user.micro.service.test.users.repository;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.quot.user.micro.service.test.files.FileProperties;
import com.quot.user.micro.service.test.files.FileService;
import com.quot.user.micro.service.test.users.model.User;
import com.quot.user.micro.service.test.users.model.UserId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

@Repository
public class FileBasedUserRepository implements UserRepository{

    private static final Logger LOGGER = LoggerFactory.getLogger(FileBasedUserRepository.class);

    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    private FileProperties fileProperties;
    private FileService fileService;

    private File userStoringDirectory;

    private static final String UPSERT_ISSUE = "There seems to be an issue upserting the file %s";
    private static final String DELETE_ISSUE = "There seems to be an issue deleting the file %s";
    private static final String READ_ISSUE = "There seems to be an issue reading the file %s";

    @Autowired
    public FileBasedUserRepository(FileProperties fileProperties,
                                   FileService fileService){
        this.fileProperties = fileProperties;
        this.userStoringDirectory = new File(fileProperties.getUserDirectory());
        this.fileService = fileService;
    }


    @Override
    public User get(UserId userId) throws IOException {

        File userFile = new File(userStoringDirectory.getPath(), userIdToFilename(userId));

        if (!userFile.exists()) throw new FileNotFoundException();
        String userJson;

        try {

            userJson = fileService.read(userFile);

        } catch (IOException e) {

            LOGGER.info(String.format(READ_ISSUE, userFile.getPath()));
            e.printStackTrace();
            throw e;
        }

        User user;

        try {

            user = OBJECT_MAPPER.readValue(userJson, User.class);

        } catch (JsonProcessingException e) {

            LOGGER.info(String.format(READ_ISSUE, userFile.getPath()));
            e.printStackTrace();
            throw e;
        }
        return user;
    }

    @Override
    public boolean delete(UserId userId) {

        String fileToDelete = userIdToFilename(userId);
        File userFile = new File(userStoringDirectory.getPath(), fileToDelete);

        boolean alreadyExists = userFile.exists();
        boolean successfulDeletion = userFile.delete();

        return successfulDeletion || !alreadyExists;
    }

    @Override
    public User save(User user) throws IOException {
        return upsert(user);
    }

    @Override
    public User update(User user) throws IOException {
        return upsert(user);
    }

    private User upsert(User user) throws IOException {

        File userFile = new File(userStoringDirectory.getPath(), userToFilename(user));

        try {
            userFile.createNewFile();

            String userJson = OBJECT_MAPPER.writeValueAsString(user);

            fileService.write(userJson, userFile.getPath());
        }
        catch (IOException e){

            LOGGER.info(String.format(UPSERT_ISSUE, userFile.getPath()));
            e.printStackTrace();
            throw e;
        }

        return user;
    }


    private static String userToFilename(User user){

        return userIdToFilename(user.getId());
    }
    private static String userIdToFilename(UserId userId){

        return userId.getUserId().concat(userId.getClientId());
    }
}
