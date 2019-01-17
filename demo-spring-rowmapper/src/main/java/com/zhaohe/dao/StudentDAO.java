package com.zhaohe.dao;

import java.util.List;

import javax.sql.DataSource;

import com.zhaohe.po.Student;

public interface StudentDAO {
    /**
     * This is the method to be used to initialize database resources ie.
     * connection.
     */
    public void setDataSource(DataSource ds);

    /**
     * This is the method to be used to list down all the records from the
     * Student table.
     */
    public List<Student> listStudents();
    public Student getStudent(Integer id);
    public void create(String name, Integer age);
    public int insertSql(final Object[] args);
}