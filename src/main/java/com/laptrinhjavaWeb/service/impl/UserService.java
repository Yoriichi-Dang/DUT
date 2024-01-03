package com.laptrinhjavaWeb.service.impl;

import com.laptrinhjavaWeb.constant.SystemConstant;
import com.laptrinhjavaWeb.dao.IStudentDAO;
import com.laptrinhjavaWeb.dao.ITeacherDAO;
import com.laptrinhjavaWeb.dao.IUserDAO;
import com.laptrinhjavaWeb.model.RoomModel;
import com.laptrinhjavaWeb.model.UserModel;
import com.laptrinhjavaWeb.service.ITeacherService;
import com.laptrinhjavaWeb.service.IUserService;
import org.springframework.security.crypto.bcrypt.BCrypt;

import javax.inject.Inject;
import java.sql.Timestamp;

public class UserService implements IUserService {
    @Inject
    private IUserDAO userDAO;
    @Inject
    private IStudentDAO studentDAO;
    @Inject
    private ITeacherDAO teacherDAO;
    @Override
    public UserModel findUser(String username,int status) {
        return userDAO.findUser(username,status);
    }

    @Override
    public UserModel checkAccount(UserModel userModel) {
        UserModel model= userDAO.findUser(userModel.getUsername(),1);
        if(model!=null){
            if(userModel.getPassword().equals(model.getPassword())||BCrypt.checkpw(userModel.getPassword(),model.getPassword())){
                if(model.getRoleCode().equals(SystemConstant.STUDENT.toUpperCase())){
                    model.setImage(studentDAO.findStudentByCode(model.getUsername()).getImage());
                }else if(model.getRoleCode().equals(SystemConstant.TEACHER.toUpperCase())){
                    model.setImage(teacherDAO.findByCode(model.getUsername()).getImage());
                }
                return model;
            }
        }
        return null;
    }

    @Override
    public Long insert(UserModel userModel) {
      return userDAO.insert(userModel);
    }

    @Override
    public Long update(UserModel userModel) {
        UserModel oldUser=userDAO.findUser(userModel.getUsername(),userModel.getStatus());
        if(oldUser.getCreateBy()==null||oldUser.getCreateBy().isEmpty())oldUser.setCreateBy(userModel.getModifiedBy());
        if(oldUser.getCreateDate()==null)oldUser.setCreateDate(new Timestamp(System.currentTimeMillis()));
        userModel.setCreateDate(oldUser.getCreateDate());
        userModel.setCreateBy(oldUser.getCreateBy());
        userModel.setModifiedDate(new Timestamp(System.currentTimeMillis()));
         return userDAO.update(userModel);
    }
}
