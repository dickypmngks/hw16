package com.example.bcc.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import com.example.bcc.model.Student;

import dto.StudentDTO;
import net.sf.jasperreports.engine.JRException;

public interface StudentService {
	public List<Student> getAllStudent();
	
	public String regisStudent(Student student);
	
	public String updateStudent(Student student);
	
	public String expelStudent(String nim);
	
	public byte[] exportReport()  throws JRException, IOException ;
	
	public byte[] createLetter()  throws JRException, IOException ;
	
	public byte[] createLamaran()  throws JRException, IOException ;
	
	public String noSurat(String jabatan);
	
	
	public String kodeUnik(String nim);
	
	public String addPass(String pass);
	
	public List<Student> findByNameContainingIgnoreCase(String name);

	
	public String changePass(Student student) ;
	
	
	public StudentDTO studentDetail(String nim);
	
	

}
