package com.quot.user.micro.service.test.users.repository;


import com.quot.user.micro.service.test.users.model.User;
import com.quot.user.micro.service.test.users.model.UserId;

import java.io.IOException;

public interface UserRepository {

    public User get(UserId userId) throws Exception;

    public boolean delete(UserId userId) throws Exception;

    public User save(User user) throws Exception;

    public User update(User user) throws Exception;

}
