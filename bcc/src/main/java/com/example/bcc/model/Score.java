package com.example.bcc.model;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import javax.persistence.Table;
import javax.transaction.Transactional;

import lombok.Data;
import lombok.NoArgsConstructor;



@Entity
@Table(name = "nilai", schema = "hr")
public class Score {

	@Id
	@Column(name="kode_nilai")
	private String scoreCode;
	

	@Column(name="kode_matkul")
	private String subjectCode;
	
	@Column(name="nilai")
	private Integer score;
	
	@Column(name = "nim")
	private String nim;
	
	
	

	public String getNim() {
		return nim;
	}

	public void setNim(String nim) {
		this.nim = nim;
	}

	public String getScoreCode() {
		return scoreCode;
	}

	public void setScoreCode(String scoreCode) {
		this.scoreCode = scoreCode;
	}

	

	public String getSubjectCode() {
		return subjectCode;
	}

	public void setSubjectCode(String subjectCode) {
		this.subjectCode = subjectCode;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}




	
	
	
	
}
