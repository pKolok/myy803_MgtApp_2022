package myy803.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderColumn;
import javax.persistence.Table;

@Entity
@Table(name = "courses")
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;

	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "instructor")
	private Instructor instructor;
	
	@Column(name = "description")
	private String description;
	
	@Column(name = "syllabus")
	private String syllabus;
	
	@Column(name = "year")
	private int year;
	
	@Column(name = "semester")
	private int semester;
	
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "course")
	@OrderColumn
	@Column(name = "student_registration")
	private List<StudentRegistration> studentRegistrations;

	@Column(name = "exam_weight")
	private double examWeight;
	
	public Course() {}
	
	public Course(String name, String description, String syllabus, 
			Instructor instructor, int year, int semester) {
		this.name = name;
		this.description = description;
		this.syllabus = syllabus;
		this.instructor = instructor;
		this.year = year;
		this.semester = semester;
	}
	
	public Integer getId() { return id; }

	public void setId(Integer id) { this.id = id; }
	
	public String getName() { return name; }
	
	public void setName(String name) { this.name = name; }
	
	public String getDescription() { return description; }
	
	public void setDescription(String description) {
		this.description = description;
	}

	public String getSyllabus() { return syllabus; }
	
	public void setSyllabus(String syllabus) { this.syllabus = syllabus; }
	
	public Instructor getInstructor() { return instructor; }
	
	public void setInstructor(Instructor instructor) { this.instructor = instructor; }

	public Integer getYear() { return year; }

	public void setYear(Integer year) { this.year = year; }

	public Integer getSemester() { return semester; }

	public void setSemester(Integer semester) { this.semester = semester; }

	public double getExamWeight() {	return examWeight; }

	public void setExamWeight(double examWeight) { this.examWeight = examWeight; }

	public String toString() {
		return "Course {\nId: " + id + "\nName: " + name + "\nDescription: " + 
				description + "\nSyllabus: " + syllabus + "\nInstructor" + 
				instructor + "\nYear: " + year + "\nSemester: " + semester + 
				"\n}";
	}

}
