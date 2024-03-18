package model.services;

public class TaxaBrasilService {

	public double taxa(double quantia) {
		if (quantia <= 100.0) {
			return quantia * 0.20;
		} else {
			return quantia * 0.15;
		}
	}
}
