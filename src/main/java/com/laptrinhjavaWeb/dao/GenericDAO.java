package com.laptrinhjavaWeb.dao;

import com.laptrinhjavaWeb.mapper.RowMapper;

import java.util.List;

public interface GenericDAO <T>{
    <T> List<T> query(String sql, RowMapper<T> mapper, Object... parameters);
    Long update(String sql,Object... parameters);
    Long insert(String sql,Object... parameters);
    int getTotalItem(String sql,Object... parameters);
    T findById(String sql,RowMapper<T>mapper,Long id);
    T findByCode(String sql,RowMapper<T>mapper,String code);

}
