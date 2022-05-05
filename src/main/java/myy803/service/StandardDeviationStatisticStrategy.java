package myy803.service;

public class StandardDeviationStatisticStrategy extends TemplateStatisticStrategy {

	public StandardDeviationStatisticStrategy() {}  
	
	@Override
	public Double doActualCalculation() {
		return descriptiveStatistics.getStandardDeviation();
	}

	@Override
	public String getStatisticName() {
		return "Std. Deviation";
	}
}