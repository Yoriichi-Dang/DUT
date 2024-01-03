package com.laptrinhjavaWeb.dao;

import com.laptrinhjavaWeb.model.UserModel;

public interface IUserDAO {
    UserModel findUser(String username,int status);
    Long insert(UserModel userModel);
    Long update(UserModel userModel);

}
