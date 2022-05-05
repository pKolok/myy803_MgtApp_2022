package myy803.service;

public class KurtosisStatisticStrategy extends TemplateStatisticStrategy {

	public KurtosisStatisticStrategy() {}  
	
	@Override
	public Double doActualCalculation() {
		return descriptiveStatistics.getKurtosis();
	}

	@Override
	public String getStatisticName() {
		return "Kurtosis";
	}
}