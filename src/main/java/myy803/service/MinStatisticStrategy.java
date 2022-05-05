package myy803.service;

public class MinStatisticStrategy extends TemplateStatisticStrategy {

	public MinStatisticStrategy() {}  
	
	@Override
	public Double doActualCalculation() {
		return descriptiveStatistics.getMin();
	}

	@Override
	public String getStatisticName() {
		return "Min";
	}
}