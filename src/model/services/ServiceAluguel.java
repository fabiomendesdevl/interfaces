package model.services;

import java.time.Duration;

import model.entities.AluguelCarro;
import model.entities.Fatura;

public class ServiceAluguel {

	private Double precoPorHora;
	private Double precoPorDia;

	private TaxaBrasilService taxaService;

	public ServiceAluguel(Double precoPorHora, Double precoPorDia, TaxaBrasilService taxaService) {
		this.precoPorHora = precoPorHora;
		this.precoPorDia = precoPorDia;
		this.taxaService = taxaService;
	}

	public void processoFatura(AluguelCarro aluguelCarro) {

		double minutos = Duration.between(aluguelCarro.getInicio(), aluguelCarro.getFim()).toMinutes();
		double horas = minutos / 60.0;
		double pagamentoBasico;
		if (horas <= 12) {
			pagamentoBasico = precoPorHora * Math.ceil(horas); // arredonda pra cima
		} else {
			pagamentoBasico = precoPorDia * Math.ceil(horas / 24);
		}
		double tax = taxaService.taxa(pagamentoBasico);

		aluguelCarro.setFatura(new Fatura(pagamentoBasico, tax));
	}
}
