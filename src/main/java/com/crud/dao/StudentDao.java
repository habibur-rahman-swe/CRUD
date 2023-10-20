package com.crud.dao;

import java.util.List;

import com.crud.entity.Student;

public interface StudentDao {

	public List<Student> getStudents();

	public void addStudent(Student student);

	public Student getOne(int parseInt);

	public void addStudent(int id, Student student);

	public void deleteOne(int id);

}
