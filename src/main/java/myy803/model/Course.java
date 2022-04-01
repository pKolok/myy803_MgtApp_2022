package myy803.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Course {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "name")
	private String name;
	@Column(name = "description")
	private String description;
	@Column(name = "syllabus")
	private String syllabus;
	@Column(name = "instructor")
	private String instructor;
	@Column(name = "year")
	private int year;
	@Column(name = "semester")
	private int semester;
	private StudentRegistration studentRegistrations[];

	public Course() {}
	
	public Course(String name, String description, String syllabus, 
			String instructor, int year, int semester) {
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
	
	public String getInstructor() { return instructor; }
	
	public void setInstructor(String instructor) { this.instructor = instructor; }

	public Integer getYear() { return year; }

	public void setYear(Integer year) { this.year = year; }

	public Integer getSemester() { return semester; }

	public void setSemester(Integer semester) { this.semester = semester; }

	public String toString() {
		return "Course {\nId: " + id + "\nName: " + name + "\nDescription: " + 
				description + "\nSyllabus: " + syllabus + "\nInstructor" + 
				instructor + "\nYear: " + year + "\nSemester: " + semester + 
				"\n}";
	}
}
