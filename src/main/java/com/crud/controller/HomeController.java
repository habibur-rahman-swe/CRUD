package com.crud.controller;

import java.io.IOException;
import java.util.List;

import com.crud.dao.StudentDao;
import com.crud.dao.impl.StudentDaoImpl;
import com.crud.entity.Student;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HomeController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	StudentDao studentDao;

	public HomeController() {
		studentDao = new StudentDaoImpl();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String action = request.getParameter("action");
		
		if (action != null && action.equals("UPDATE")) {
			updateEdit(request, response);
		}
		
		if (action != null && action.equals("EDIT")) {
			request.setAttribute("id", request.getParameter("id"));
			doEdit(request, response);
		}
		

		if (action != null && action.equals("DELETE")) {
			doDelete(request, response);
		}

		List<Student> students = studentDao.getStudents();

		request.setAttribute("students", students);

		RequestDispatcher requestDispatcher = request.getRequestDispatcher("/index.jsp");

		requestDispatcher.forward(request, response);
	}

	private void updateEdit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Student student = new Student(Integer.parseInt(request.getParameter("id")), request.getParameter("firstName"), request.getParameter("lastName"));

		studentDao.addStudent(Integer.parseInt(request.getParameter("id")),student);

		request.removeAttribute("action");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Student student = new Student(request.getParameter("firstName"), request.getParameter("lastName"));

		studentDao.addStudent(student);

		request.removeAttribute("firstName");
		request.removeAttribute("lastName");

		List<Student> students = studentDao.getStudents();

		request.setAttribute("students", students);
	}

	protected void doEdit(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		Student student = studentDao.getOne(id);
		
		request.setAttribute("student", student);
		
		request.setAttribute("action", "UPDATE");
	}

	protected void doDelete(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		studentDao.deleteOne(id);
		
		List<Student> students = studentDao.getStudents();
		
		request.removeAttribute("action");
		request.setAttribute("students", students);
	}
}
