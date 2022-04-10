package myy803.service;

import myy803.model.Course;

public abstract class TemplateStatisticStrategy {

	public TemplateStatisticStrategy() {}
	
	public double calculateStatistic(Course course) {
		return 0;
	}
	
	public abstract void doActualCalculation();
	
	private void prepareDataSet() {
		
	}
}
