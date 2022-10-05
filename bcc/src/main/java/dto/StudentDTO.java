package dto;

import java.sql.Date;
import java.util.List;


public class StudentDTO {

	private String nim;
	
	private String name;
	
	private Date dob;
	
	private String address;
	
	private String gender;
	
	private Integer studyProgramCode;
	
	private String password;
	
	private List<ScoreDTO> scoreDto;


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public List<ScoreDTO> getScoreDto() {
		return scoreDto;
	}


	public void setScoreDto(List<ScoreDTO> scoreDto) {
		this.scoreDto = scoreDto;
	}


	public String getNim() {
		return nim;
	}


	public void setNim(String nim) {
		this.nim = nim;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public Date getDob() {
		return dob;
	}


	public void setDob(Date dob) {
		this.dob = dob;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
	}


	public Integer getStudyProgramCode() {
		return studyProgramCode;
	}


	public void setStudyProgramCode(Integer studyProgramCode) {
		this.studyProgramCode = studyProgramCode;
	}
	
	


}
