package myy803.service;

public class MaxStatisticStrategy extends TemplateStatisticStrategy {
	
	@Override
	public Double doActualCalculation() {
		return descriptiveStatistics.getMax();
	}

	@Override
	public String getStatisticName() {
		return "Max";
	}
}