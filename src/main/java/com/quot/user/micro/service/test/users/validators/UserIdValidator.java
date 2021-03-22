package com.quot.user.micro.service.test.users.validators;

import com.quot.user.micro.service.test.users.model.UserId;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

@Component
public class UserIdValidator {

    public boolean isValid(UserId userId){
        return !(userId.getUserId().isEmpty() || userId.getUserId() == null
            || userId.getClientId().isEmpty() || userId.getClientId() == null);
    }
}
