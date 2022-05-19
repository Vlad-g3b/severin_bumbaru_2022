package com.antii.IntershipREST.models;

import java.sql.Date;

public class Assessment {
	private int id;
	private Internship internship;
	private TutorDetails tutor;
	private StudentDetails student;
	private Double evNote;
	private Double progress;
	private Date date;
	
	
	public Assessment() {
		
	}


	public Internship getInternship() {
		return internship;
	}


	public void setInternship(Internship internship) {
		this.internship = internship;
	}


	public TutorDetails getTutor() {
		return tutor;
	}


	public void setTutor(TutorDetails tutor) {
		this.tutor = tutor;
	}


	public StudentDetails getStudent() {
		return student;
	}


	public void setStudent(StudentDetails student) {
		this.student = student;
	}


	public Double getEvNote() {
		return evNote;
	}


	public void setEvNote(Double evNote) {
		this.evNote = evNote;
	}


	public Double getProgress() {
		return progress;
	}


	public void setProgress(Double progress) {
		this.progress = progress;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public Assessment(Internship internship, TutorDetails tutor, StudentDetails student, Double evNote, Double progress,
			Date date) {
		super();
		this.internship = internship;
		this.tutor = tutor;
		this.student = student;
		this.evNote = evNote;
		this.progress = progress;
		this.date = date;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}
	
	
}
