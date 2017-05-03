
public class ValorAPagar {
	
	Acesso acesso; 
	
	int quantidadeHoras;
	int quantidadeMinutos; 
	float valorTotalHoras;
	float valorTotalMinutos;

	
	public ValorAPagar(Acesso acesso, int quantidadeHoras, int quantidadeMinutos, float valorTotalHoras,
			float valorTotalMinutos) {
		this.acesso = acesso;
		this.quantidadeHoras = quantidadeHoras;
		this.quantidadeMinutos = quantidadeMinutos;
		this.valorTotalHoras = valorTotalHoras;
		this.valorTotalMinutos = valorTotalMinutos;
	}

	public float computar(){
		
		getTotalHorasMinutos();
		
		calculaValorTotal();		
		
		if (isDiaria())
			return acesso.VALOR_DIARIA;
		else 
			return (valorTotalHoras+valorTotalMinutos);
		
	}
	
	private boolean isDiaria(){
		return (quantidadeHoras >=9);
	}

	private void calculaValorTotal() {
		valorTotalHoras += quantidadeHoras * acesso.VALOR_HORA;
		
		valorTotalMinutos += Math.ceil(quantidadeMinutos / 15.0) * acesso.VALOR_FRACAO;
	}

	private void getTotalHorasMinutos() {
		
		quantidadeHoras = calculaHoras(); 
		
		if (horasIguaisOuMinutosEHorasMaiorers())
			quantidadeMinutos = calculaMinutos();
		else if (horasMaiorEMinutosIguais()){
			quantidadeHoras = calculaHoras();
		}else if (horasMaiorEMinutosMenores()){
			quantidadeMinutos = acesso.minutosSaida + (60 - acesso.minutosEntrada);
			quantidadeHoras = calculaHoras() - 1;
		}
	}
	
	private boolean horasIguaisOuMinutosEHorasMaiorers(){
		return (acesso.horaSaida == acesso.horaEntrada || (acesso.horaSaida > acesso.horaEntrada && acesso.minutosEntrada > acesso.minutosSaida));
	}
	
	private boolean horasMaiorEMinutosIguais(){
		return (acesso.horaSaida > acesso.horaEntrada && acesso.minutosEntrada == acesso.minutosSaida);
	}
	private boolean horasMaiorEMinutosMenores(){
		return (acesso.horaSaida > acesso.horaEntrada && acesso.minutosEntrada == acesso.minutosSaida);
	}
	
	private int calculaMinutos(){
		return acesso.minutosSaida - acesso.minutosEntrada;
	}
	
	private int calculaHoras(){
		return acesso.horaSaida - acesso.horaEntrada;
	}

}
