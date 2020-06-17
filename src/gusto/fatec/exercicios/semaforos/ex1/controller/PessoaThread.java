package gusto.fatec.exercicios.semaforos.ex1.controller;

import java.util.concurrent.Semaphore;

public class PessoaThread extends Thread {

	private int pessoa;
	private static int chegadaPorta;
	private static int passagemPorta;
	private Semaphore semaforo;

	public PessoaThread(int pessoa, Semaphore semaforo) {
		this.pessoa = pessoa;
		this.semaforo = semaforo;
	}

	@Override
	public void run() {
		pessoaAndando();

		try {
			semaforo.acquire();
			pessoaEsperando();
			pessoaSaindo();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			semaforo.release();
		}
	}

	private void pessoaAndando() {
		int corredor = 200;
		int distanciaPecorrida = 0;
		int delocamento = (int) ((Math.random() * 3) + 4);
		int tempo = 10;

		while (distanciaPecorrida < corredor) {
			distanciaPecorrida += delocamento;
			try {
				Thread.sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println("Pessoa: #" + pessoa + " andou " + distanciaPecorrida + "m");
		}

		chegadaPorta++;
	}

	private void pessoaEsperando() {
		System.out.println("Pessoa #" + pessoa + " chegou na porta.");

		int abrindoPorta = ((int) (Math.random() * 2) + 1);

		try {
			Thread.sleep(abrindoPorta);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void pessoaSaindo() {
		passagemPorta++;

		System.out.println("Pessoa: #" + pessoa + " foi o " + passagemPorta + "� a sair.");
	}

}
