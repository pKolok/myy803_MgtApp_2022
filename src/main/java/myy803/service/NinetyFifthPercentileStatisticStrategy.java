package myy803.service;

public class NinetyFifthPercentileStatisticStrategy extends TemplateStatisticStrategy {   
	
	@Override
	public Double doActualCalculation() {
		return descriptiveStatistics.getPercentile(95);
	}

	@Override
	public String getStatisticName() {
		return "95th Percentile";
	}
}