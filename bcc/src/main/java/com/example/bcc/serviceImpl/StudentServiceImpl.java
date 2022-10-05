package com.example.bcc.serviceImpl;

import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import com.example.bcc.model.Score;
import com.example.bcc.model.Student;
import com.example.bcc.repository.ScoreRepository;
import com.example.bcc.repository.StudentRepository;
import com.example.bcc.service.StudentService;

import dto.ScoreDTO;
import dto.StudentDTO;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

@Service
public class StudentServiceImpl implements StudentService {
	
	@Autowired
	StudentRepository studentRepo;
	
	@Autowired
	ScoreRepository scoreRepo;
	


	@Override
	public List<Student> getAllStudent() {
		return studentRepo.findAll();
	}

	@Override
	public String regisStudent(Student student) {
		String result = "Register Student Failed";

		if (student != null) {
			studentRepo.save(student);
			result = "Register Student Success";
		}
		return result;
	}

	@Override
	public String updateStudent(Student student) {
		String result = "Update Student Failed";

		Optional<Student> extStudent = studentRepo.findById(student.getNim());

		if (extStudent.isPresent()) {
		
			studentRepo.save(student);
			result = "Update Student Success";
		}
		

		return result;
	}

	@Override
	public String expelStudent(String nim) {
		String result = "Delete Student Failed";

		Optional<Student> extStudent = studentRepo.findById(nim);

		if (extStudent.isPresent()) {
			studentRepo.deleteById(nim);
			result = "Delete Student Success";
		}

		return result;
	}

	@Override
	public byte[] exportReport() throws JRException, IOException {
	InputStream filePath = new ClassPathResource("/template/Mahasiswa.jrxml").getInputStream();	
		
		List<Student> dsStudent = getAllStudent();
		
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dsStudent);
		
		JasperReport report = JasperCompileManager.compileReport(filePath);
		
		JasperPrint print = JasperFillManager.fillReport(report, null,ds);
		
		byte[]  byteArr = JasperExportManager.exportReportToPdf(print);
		
		return byteArr;
		
	}

	@Override
	public byte[] createLetter() throws JRException, IOException {
		InputStream filePath = new ClassPathResource("/template/surat.jrxml").getInputStream();	
		
		List<Student> dsStudent = getAllStudent();
		
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dsStudent);
		
		JasperReport report = JasperCompileManager.compileReport(filePath);
		
		JasperPrint print = JasperFillManager.fillReport(report, null,ds);
		
		byte[]  byteArr = JasperExportManager.exportReportToPdf(print);
		
		return byteArr;
	}

	@Override
	public String kodeUnik(String nim) {
		String result=nim.substring(0,3);
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy");
		String strDate = formatter.format(date);
		result += "/"+strDate;
		
		int nilai = (int)(Math.random()*1001);
		String inRandom = String.format ("%04d",nilai);
		

		 result += "/"+inRandom;
		return result;
	}

	@Override
	public String addPass(String pass) {
		String result = "Add Pass Failed";
		if(pass != null) {
			BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
			result = passEncoder.encode(pass);
		}
		return result;
	}

	@Override
	public List<Student> findByNameContainingIgnoreCase(String name) {
		return studentRepo.findByNameContainingIgnoreCase(name);
	
	}

	

	@Override
	public byte[] createLamaran() throws JRException, IOException {
		InputStream filePath = new ClassPathResource("/template/SuratLamaran.jrxml").getInputStream();	
		
		List<Student> dsStudent = getAllStudent();
		
		JRBeanCollectionDataSource ds = new JRBeanCollectionDataSource(dsStudent);
		
		JasperReport report = JasperCompileManager.compileReport(filePath);
		
		JasperPrint print = JasperFillManager.fillReport(report, null,ds);
		
		byte[]  byteArr = JasperExportManager.exportReportToPdf(print);
		
		return byteArr;
	}

	@Override
	public String changePass(Student student) {
		String result="Change Password Failed";
		String pass = student.getPassword();
		String charPass = pass.substring(0,1);
		Pattern pat = Pattern.compile(".*[A-Z].*");
		Matcher mat = pat.matcher(charPass);
		boolean matchFound = mat.find();
		 if(matchFound) {
			 pat = Pattern.compile("[0-9]");
			 mat = pat.matcher(pass);
			 matchFound = mat.find();
			 if(matchFound) {
				BCryptPasswordEncoder passEncoder = new BCryptPasswordEncoder();
				pass = student.getPassword();
				student.setPassword(passEncoder.encode(pass));
				studentRepo.save(student);
				result = "update pass success";
			 }else {
				 result="Password Harus Mengandung Angka";
			 } 
		 } else {
		     result="Pada huruf pertama password tidak mengadung huruf kapital";
		 }
		
		return result;
		
	}

	private static int latestId = 0;
	@Override
	public String noSurat(String jabatan) {
		String result="Nomor surat gagal dibuat";
		
		++latestId;
		String inRandom = String.format ("%04d",latestId);
		result =inRandom;
		
		if(jabatan.equals("Mahasiswa")) {
			result +="/MHS";
		}else if (jabatan.equals("Dosen")) {
			result +="/LEC";
		}
		
		Date date = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("M");
		String strDate = formatter.format(date);
		int bulan = Integer.parseInt(strDate);
		
		String roman ="";
		
	     while(bulan>=10){
             if (bulan>=40){
                 roman="XL";
                 bulan = bulan - 40;
             }
             else{
                 roman="X";
                 bulan = bulan - 10;
             }
         }
         if (bulan >=5){
             if (bulan == 9){
                 roman="IX";
                 bulan = bulan - 9;
             }
             else
                 roman="V";
                 bulan = bulan - 5;
         }
     
         while(bulan>=1){
         if (bulan == 4){
             roman="IV";
             bulan = bulan - 4;
         }
         else
             roman="I";
             bulan = bulan - 1;
         }
		result +="/"+roman;
		
		SimpleDateFormat formatter2 = new SimpleDateFormat("yy");
		String strDate2 = formatter2.format(date);
		
		result +="/"+strDate2;
				
		return result;
	}

	@Override
	public StudentDTO studentDetail(String nim) {
		Student student = studentRepo.findByNim(nim);
		List<Score> score = scoreRepo.findByNim(nim);
		
		List<ScoreDTO> liScoreDTO = new ArrayList<>();
		StudentDTO studentDto = new StudentDTO();
		
		studentDto.setNim(student.getNim());
		studentDto.setName(student.getName());
		studentDto.setDob(student.getDob());
		studentDto.setAddress(student.getAddress());
		studentDto.setGender(student.getGender());
		studentDto.setStudyProgramCode(student.getStudyProgramCode());
		studentDto.setPassword(student.getPassword());
		
		for(Score scores : score) {
			ScoreDTO scoreDto = new ScoreDTO();
			scoreDto.setScoreCode(scores.getScoreCode());
			scoreDto.setNim(scores.getNim());
			scoreDto.setSubjectCode(scores.getSubjectCode());
			scoreDto.setScore(scores.getScore());
			liScoreDTO.add(scoreDto);
		}
		studentDto.setScoreDto(liScoreDTO);		
		return studentDto;
	}


	

}
