package application;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Scanner;

import model.entities.AluguelCarro;
import model.entities.Veiculo;
import model.services.ServiceAluguel;
import model.services.TaxaBrasilService;

public class Program {

	public static void main(String[] args) {

		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner(System.in);
		DateTimeFormatter sdf = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");

		System.out.println("Insira os dados do aluuel");
		System.out.println("Insira o modelo do carro: ");
		String modelo = sc.nextLine();
		System.out.println("Inicio: (dd/MM/yyyy hh:mm");
		LocalDateTime inicio = LocalDateTime.parse(sc.nextLine(), sdf);
		System.out.println("Fim: (dd/MM/yyyy hh:mm");
		LocalDateTime fim = LocalDateTime.parse(sc.nextLine(), sdf);
		
		AluguelCarro car = new AluguelCarro(inicio, fim, new Veiculo(modelo)); 
		
		System.out.print("Entre com o preço por hora: ");
		double precoPorHora = sc.nextDouble();
		System.out.print("Insira o preço por dia: ");
		double precoPorDia = sc.nextDouble();
		
		ServiceAluguel serviceAluguel = new ServiceAluguel(precoPorHora, precoPorDia, new TaxaBrasilService());
		serviceAluguel.processoFatura(car);
		
		System.out.println("Fatura:");
		System.out.println("Pagamento basico: " + car.getFatura().getPagamentoBasico());
		System.out.println("Imposto: " + car.getFatura().getTaxa());
		System.out.println("Pagamento total: " + car.getFatura().getTotalFautra());
		sc.close();
	}
}