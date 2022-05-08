package myy803.service;

public class StandardDeviationStatisticStrategy extends TemplateStatisticStrategy {
	
	@Override
	public Double doActualCalculation() {
		return descriptiveStatistics.getStandardDeviation();
	}

	@Override
	public String getStatisticName() {
		return "Std. Deviation";
	}
}