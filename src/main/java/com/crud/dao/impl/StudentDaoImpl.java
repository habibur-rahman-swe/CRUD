package com.crud.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.crud.dao.StudentDao;
import com.crud.entity.Student;
import com.crud.util.DBConnection;

public class StudentDaoImpl implements StudentDao{
	private Connection connection;
	private Statement statement;
	private PreparedStatement preparedStatement;
	
	private String sql;
	
	public StudentDaoImpl() {
		connection = DBConnection.openConnection();
	}
	
	@Override
	public List<Student> getStudents() {
		
		List<Student> students = new ArrayList<>();
		
		sql = "select * from students;";
		
		try {
			statement = connection.createStatement();

			ResultSet resultSet = statement.executeQuery(sql);
			
			while (resultSet.next()) {
				students.add(new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3)));
			}
			
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return students;
	}

	@Override
	public void addStudent(Student student) {
		sql = "insert into students(firstName, lastName) values(?, ?)";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getFirstName());
			preparedStatement.setString(2, student.getLastName());
			
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public Student getOne(int id) {
		Student student = new Student();
		
		sql = "select * from students where id=?;";
		
		try {
		preparedStatement = connection.prepareStatement(sql);
		preparedStatement.setString(1, String.valueOf(id));
		
		ResultSet resultSet = preparedStatement.executeQuery();
		
		while (resultSet.next()) {
			student = new Student(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3));
		}
		
		} catch (Exception e) {
			e.printStackTrace();
		}
		return student;
	}

	@Override
	public void addStudent(int id, Student student) {
		sql = "update students set firstName=?, lastName=? where id=?;";
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, student.getFirstName());
			preparedStatement.setString(2, student.getLastName());
			preparedStatement.setInt(3, student.getId());
			
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void deleteOne(int id) {
		sql = "delete from students where id=" + String.valueOf(id) + ";";
		try {
			preparedStatement = connection.prepareStatement(sql);
			
			preparedStatement.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
