package com.laptrinhjavaWeb.dao.impl;

import com.laptrinhjavaWeb.dao.IUserDAO;
import com.laptrinhjavaWeb.mapper.UserMapper;
import com.laptrinhjavaWeb.model.UserModel;
import org.springframework.security.crypto.bcrypt.BCrypt;

import java.util.List;

public class UserDAO extends AbstractDAO<UserModel> implements IUserDAO {

    @Override
    public UserModel findUser(String username, int status) {
      StringBuilder sql=new StringBuilder("select users.*, role.code as roleCode from users inner join userroles on users.username=userroles.userCode\n" +
              "inner join role on userroles.roleCode=role.code where username=? and status=?;");
        List<UserModel> users=query(sql.toString(),new UserMapper(),username,status);
        return users.isEmpty()?null:users.get(0);
    }

    @Override
    public Long insert(UserModel userModel) {
        if(findUser(userModel.getUsername(),1)==null){
            userModel.setPassword(BCrypt.hashpw(userModel.getPassword(), BCrypt.gensalt(12)));
            StringBuilder sql= new StringBuilder("insert into users(username,password,status,createBy) values(?,?,?,?);");
            Long id=insert(sql.toString(),userModel.getUsername(),userModel.getPassword(),userModel.getStatus(),userModel.getCreateBy());
            if(id!=-1){
                sql=new StringBuilder("insert into userroles(userCode,roleCode,createBy) values(?,?,?);");
                Long idUserRole=insert(sql.toString(),userModel.getUsername(),userModel.getRoleCode(),userModel.getCreateBy());
                if(idUserRole==-1)return -1L;
            }
            return id;
        }
        return -1L;
    }

    @Override
    public Long update(UserModel userModel) {
        userModel.setPassword(BCrypt.hashpw(userModel.getPassword(), BCrypt.gensalt(12)));
        String sql="update users set password=?,status=?,createDate=?,createBy=? ,modifiedDate=?,modifiedBy=? where username=?";
        return update(sql,userModel.getPassword(),userModel.getStatus(),userModel.getCreateDate(),userModel.getCreateBy(),userModel.getModifiedDate(),userModel.getModifiedBy(),userModel.getUsername());
    }
}
