package myy803.service;

public class KurtosisStatisticStrategy extends TemplateStatisticStrategy {
	
	@Override
	public Double doActualCalculation() {
		return descriptiveStatistics.getKurtosis();
	}

	@Override
	public String getStatisticName() {
		return "Kurtosis";
	}
}