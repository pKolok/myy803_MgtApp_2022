package myy803.service;

public class VarianceStatisticStrategy extends TemplateStatisticStrategy {
	
	@Override
	public Double doActualCalculation() {
		return descriptiveStatistics.getPopulationVariance();
	}

	@Override
	public String getStatisticName() {
		return "Variance";
	}
}