package myy803.service;

import java.util.List;

import myy803.model.StudentRegistration;

public interface StatisticStrategy {

	public double calculateStatistic(List<StudentRegistration> students);
	
	public String getStatisticName();
}