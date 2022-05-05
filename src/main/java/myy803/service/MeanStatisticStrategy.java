package myy803.service;

public class MeanStatisticStrategy extends TemplateStatisticStrategy {

	public MeanStatisticStrategy() {}  
	
	@Override
	public Double doActualCalculation() {
		return descriptiveStatistics.getMean();
	}

	@Override
	public String getStatisticName() {
		return "Mean";
	}	
}