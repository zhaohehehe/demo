package com.zhaohe.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.jdbc.core.ArgumentPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import com.zhaohe.dao.StudentDAO;
import com.zhaohe.map.StudentMapper;
import com.zhaohe.po.Student;

public class StudentJDBCTemplate implements StudentDAO {
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplateObject;

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplateObject = new JdbcTemplate(dataSource);
    }

    public List<Student> listStudents() {
        String SQL = "select * from Student";
        List<Student> students = jdbcTemplateObject.query(SQL, new StudentMapper());
        return students;
    }

    public void create(String name, Integer age) {
        // TODO Auto-generated method stub
        String insertQuery = "insert into student (name, age) values (?, ?)";

        jdbcTemplateObject.update(insertQuery, name, age);
        System.out.println("Created Record Name = " + name + " Age = " + age);
        return;
    }

    public Student getStudent(final Integer id) {
        final String SQL = "select * from Student where id = ?";
        List<Student> students = jdbcTemplateObject.query(SQL, new PreparedStatementSetter() {
            public void setValues(PreparedStatement preparedStatement) throws SQLException {
                preparedStatement.setInt(1, id);
            }
        }, new StudentMapper());
        return students.get(0);
    }
    /**
     * 返回主键，只要设置了主键，1或者2都可以
     */
	@Override
	public int insertSql(final Object[] args) {
		//1.
		final String insertSql = "insert into student(id, name, age) values(?, ?, ?)";
		//2.
		//final String insertSql = "insert into student (name, age) values (?, ?)";
		KeyHolder generatedKeyHolder = new GeneratedKeyHolder();
		jdbcTemplateObject.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
				// TODO Auto-generated method stub
				new ArgumentPreparedStatementSetter(args);
				PreparedStatement ps = conn.prepareStatement(insertSql, Statement.RETURN_GENERATED_KEYS);
				int len=args.length;
				for(int i=0;i<len;i++){
					ps.setObject(i+1, args[i]);
				}
				return ps;
			}
		}, generatedKeyHolder);
		int key = generatedKeyHolder.getKey().intValue();
		return key;
	}
}