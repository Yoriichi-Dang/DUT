package com.laptrinhjavaWeb.dao.impl;

import com.laptrinhjavaWeb.dao.GenericDAO;
import com.laptrinhjavaWeb.mapper.RowMapper;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class AbstractDAO<T> implements GenericDAO<T> {
    ResourceBundle resourceBundle = ResourceBundle.getBundle("db");

    public Connection getConnection(){
        try{
            Class.forName(resourceBundle.getString("driveName"));
            String url=resourceBundle.getString("url");
            String user=resourceBundle.getString("user");
            String password=resourceBundle.getString("password");

            Connection conn= DriverManager.getConnection(url,user,password);
            System.out.println("connect database successfully!");
            return conn;
        }catch (ClassNotFoundException | SQLException e){
            System.out.println("connect database error");
            return null;
        }
    }

    @Override
    public <T> List<T> query(String sql, RowMapper<T> mapper, Object... parameters) {
        List<T>result=new ArrayList<>();
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        Connection connection=null;
        try{
            connection=getConnection();
            statement=connection.prepareStatement(sql);
            setParameter(statement,parameters);
            resultSet= statement.executeQuery();
            while(resultSet.next()){
                result.add(mapper.mapRow(resultSet));
            }
            return result;
        }catch (SQLException e){
            return null;
        }finally {
            try{
                if(connection!=null)connection.close();
                if(statement!=null) statement.close();
                if(resultSet!=null)resultSet.close();
            }catch (SQLException e){
                return null;
            }
        }
    }

    @Override
    public Long update(String sql, Object... parameters) {
        Connection connection=null;
        PreparedStatement statement=null;
        try{
            connection=getConnection();
            connection.setAutoCommit(false);
            statement=connection.prepareStatement(sql);
            setParameter(statement,parameters);
            statement.executeUpdate();
            connection.commit();
            System.out.println("Update success");
            return 1L;
        }catch (SQLException e){
            e.printStackTrace();
            System.out.println("Update failed");
            if(connection!=null){
                try{
                    connection.rollback();
                }catch (SQLException e1){
                    e1.printStackTrace();
                }
            }
            return -1L;
        }finally {
            try{
                if(connection!=null)connection.close();
                if(statement!=null)statement.close();
            }catch (SQLException e2){
                e2.printStackTrace();
                return -1L;
            }
        }
    }

    @Override
    public Long insert(String sql, Object... parameters) {
        ResultSet resultSet=null;
        Connection connection=getConnection();
        PreparedStatement statement=null;
        try{
            statement=connection.prepareStatement(sql,statement.RETURN_GENERATED_KEYS);
            connection.setAutoCommit(false);
            setParameter(statement,parameters);
            statement.executeUpdate();
            resultSet=statement.getGeneratedKeys();
            Long id=null;
            if(resultSet.next()){
                id= resultSet.getLong(1);
                System.out.println("Insert success");
            }
            connection.commit();
            return id;
        }catch (SQLException e){
            System.out.println("Insert error");
            if(connection!=null){
                try{
                    connection.rollback();
                }catch (SQLException e1){
                    e1.printStackTrace();
                }
            }
            return -1L;
        }finally {
            try{
                if(connection!=null)connection.close();
                if(resultSet!=null)resultSet.close();
                if(statement!=null)statement.close();
            }catch (SQLException e2){
                e2.printStackTrace();
            }
        }

    }

    @Override
    public int getTotalItem(String sql, Object... parameters) {
        return 0;
    }

    @Override
    public T findById(String sql, RowMapper<T> mapper, Long id) {
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        Connection connection=null;
        try{
            connection=getConnection();
            statement=connection.prepareStatement(sql);
            setParameter(statement,id);
            resultSet= statement.executeQuery();
            if(resultSet.next()){
               return mapper.mapRow(resultSet);
            }
        }catch (SQLException e){
            return null;
        }finally {
            try{
                if(connection!=null)connection.close();
                if(statement!=null) statement.close();
                if(resultSet!=null)resultSet.close();
            }catch (SQLException e){
                return null;
            }
        }
        return null;
    }

    @Override
    public T findByCode(String sql, RowMapper<T> mapper, String code) {
        PreparedStatement statement=null;
        ResultSet resultSet=null;
        Connection connection=null;
        try{
            connection=getConnection();
            statement=connection.prepareStatement(sql);
            setParameter(statement,code);
            resultSet= statement.executeQuery();
            if(resultSet.next()){
                return mapper.mapRow(resultSet);
            }
        }catch (SQLException e){
            return null;
        }finally {
            try{
                if(connection!=null)connection.close();
                if(statement!=null) statement.close();
                if(resultSet!=null)resultSet.close();
            }catch (SQLException e){
                return null;
            }
        }
        return null;
    }

    private void setParameter(PreparedStatement statement,Object... parameters){
        try{
            for(int i=0;i<parameters.length;i++){
                Object parameter=parameters[i];
                if(parameter instanceof Long){
                    statement.setLong(i+1,(Long) parameter);
                }
                else if(parameter instanceof String){
                    statement.setString(i+1,(String)parameter);
                }else if(parameter instanceof Integer){
                    statement.setInt(i+1,(Integer)parameter);
                }else if(parameter instanceof Timestamp){
                    statement.setTimestamp(i+1,(Timestamp) parameter);
                }else if(parameter instanceof java.util.Date){
                    java.util.Date utilDate = (java.util.Date) parameter;
                    java.sql.Date sqlDate = new java.sql.Date(utilDate.getTime());
                    System.out.println(sqlDate);
                    statement.setDate(i + 1, sqlDate);
                }
                else if(parameter instanceof Double){
                    statement.setDouble(i+1,(Double) parameter);
                }
                else if(parameter==null){
                    statement.setNull(i+1,Types.NULL);
                }
            }
        }catch (Exception e){

        }
    }
}
