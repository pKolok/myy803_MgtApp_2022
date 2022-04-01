package myy803.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class StudentRegistration {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "yearOfStudies")
	private int yearOfStudies;
	@Column(name = "semester")
	private int semester;
	@Column(name = "yearOfRegistration")
	private int yearOfRegistration;
	@Column(name = "projectGrade")
	private double projectGrade;
	@Column(name = "examGrade")
	private double examGrade;
	@Column(name = "grade")
	private double grade;

	public StudentRegistration() {}
	
	public StudentRegistration(String name, int yearOfStudies,
			int semester, int yearOfRegistration, double projectGrade,
			double examGrade, double grade) {
		this.name = name;
		this.yearOfStudies = yearOfStudies;
		this.semester = semester;
		this.yearOfRegistration = yearOfRegistration;
		this.projectGrade = projectGrade;
		this.examGrade = examGrade;
		this.grade = grade;
	}
	
	public Integer getId() { return id; }

	public void setId(Integer id) { this.id = id; }

	public String getName() { return name; }

	public void setName(String name) { this.name = name; }

	public Integer getYearOfStudies() { return yearOfStudies; }

	public void setYearOfStudies(Integer yearOfStudies) {
		this.yearOfStudies = yearOfStudies;
	}

	public Integer getSemester() { return semester; }

	public void setSemester(Integer semester) { this.semester = semester; }

	public Integer getYearOfRegistration() { return yearOfRegistration; }

	public void setYearOfRegistration(Integer yearOfRegistration) {
		this.yearOfRegistration = yearOfRegistration;
	}

	public double getProjectGrade() { return projectGrade; }

	public void setProjectGrade(double projectGrade) {
		this.projectGrade = projectGrade;
	}

	public double getExamGrade() { return examGrade; }

	public void setExamGrade(double examGrade) { this.examGrade = examGrade; }

	public double getGrade() { return grade; }

	public void setGrade(double grade) { this.grade = grade; }
	
}
