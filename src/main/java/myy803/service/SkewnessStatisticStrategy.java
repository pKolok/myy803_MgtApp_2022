package myy803.service;

public class SkewnessStatisticStrategy extends TemplateStatisticStrategy {

	public SkewnessStatisticStrategy() {}  
	
	@Override
	public Double doActualCalculation() {
		return descriptiveStatistics.getSkewness();
	}

	@Override
	public String getStatisticName() {
		return "Skewness";
	}
}