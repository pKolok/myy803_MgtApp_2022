package myy803.service;

public class MedianStatisticStrategy extends TemplateStatisticStrategy {
	
	@Override
	public Double doActualCalculation() {
		return descriptiveStatistics.getPercentile(50);
	}

	@Override
	public String getStatisticName() {
		return "Median";
	}
}