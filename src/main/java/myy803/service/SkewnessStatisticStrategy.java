package myy803.service;

public class SkewnessStatisticStrategy extends TemplateStatisticStrategy {
	
	@Override
	public Double doActualCalculation() {
		return descriptiveStatistics.getSkewness();
	}

	@Override
	public String getStatisticName() {
		return "Skewness";
	}
}