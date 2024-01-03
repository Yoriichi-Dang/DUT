package com.laptrinhjavaWeb.service;

import com.laptrinhjavaWeb.model.UserModel;

public interface IUserService {
    UserModel findUser(String username,int status);
    UserModel checkAccount(UserModel userModel);

    Long insert(UserModel userModel);
    Long update(UserModel userModel);

}
