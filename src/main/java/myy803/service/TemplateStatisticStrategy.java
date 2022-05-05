package myy803.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math3.stat.descriptive.DescriptiveStatistics;

import myy803.model.StudentRegistration;

public abstract class TemplateStatisticStrategy implements StatisticStrategy{

	protected DescriptiveStatistics descriptiveStatistics;
	
	
	public TemplateStatisticStrategy() {
		descriptiveStatistics = new DescriptiveStatistics();
	}
	
	public double calculateStatistic(List<StudentRegistration> students) {
			
		List<Double> grades = new ArrayList<Double>();
		
		for (StudentRegistration student : students)
			grades.add(student.getGrade());
		
		prepareDataSet(grades);
		
		return doActualCalculation();
	}
	
	public abstract Double doActualCalculation();
	
	public abstract String getStatisticName();
	
	private void prepareDataSet(List<Double> grades) {
		
		for (double grade : grades)
			descriptiveStatistics.addValue(grade);
	}
}