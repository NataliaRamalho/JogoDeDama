package application;

import java.util.Scanner;

import entites.Cor;
import entites.Dama;
import entites.Partida;
import entites.Posicao;
import entites.Tabuleiro;

public class Program {

	public static void main(String[] args) {

		Tabuleiro tb = new Tabuleiro(8, 8);
		Partida partida = new Partida(tb);
		Scanner sc = new Scanner(System.in);
		Dama piece = new Dama(tb, new Posicao(0, 0), partida.getJogadorCorreto());

		partida.preencherTabuleiro();
		System.out.println("");
		System.out.println("BEM VINDO AO JOGO DE DAMAS");
		System.out.println("JOGADOR PRETO: O  E JOGADOR BRANCO: X");
		System.out.println("");
		tb.imprimirTabuleiro();

		while (tb.quantPieceBrancas() != 0 && tb.quantPiecePretas() != 0) {
			try {
				System.out.println();
				System.out.println("VEZ DO JOGADOR:  " + partida.getJogadorCorreto());
				System.out.println();
				System.out.print("Digite a posicao da peca que voce quer mover: ");
				String posicaoPartida = sc.nextLine();
				Posicao inicio = Posicao.converteVetor(posicaoPartida, tb);

				if (inicio.validarPosicaoInicio(partida, tb) == false) {
					System.out.println("Essa posicao � invalida ");
				}

				while (inicio == null || inicio.validarPosicaoInicio(partida, tb) == false) {
					System.out.println();
					tb.imprimirTabuleiro();
					System.out.print("Digite novamente posicao da peca que voce quer mover: ");
					posicaoPartida = sc.nextLine();
					inicio = Posicao.converteVetor(posicaoPartida, tb);
					if (inicio.validarPosicaoInicio(partida, tb) == false) {
						System.out.println("Essa posicao � invalida ");
					}
				}
				System.out.print("Digite a posicao de destino: ");
				String posicaoChegada = sc.nextLine();
				Posicao chegada = Posicao.converteVetor(posicaoChegada, tb);
				while (chegada == null) {
					System.out.println();
					tb.imprimirTabuleiro();
					System.out.print("Digite novamente a posicao de destino: ");
					System.out.println();
					posicaoPartida = sc.nextLine();
					chegada = Posicao.converteVetor(posicaoPartida, tb);
				}

				partida.jogar(inicio, chegada);
				System.out.println();
				piece.mudarCor();
				tb.imprimirTabuleiro();
				if (piece.existeMovimentosAinda(piece.getCor()) == false) {
					break;
				}
			} catch (RuntimeException e) {
				e.getMessage();
			}
		}
		sc.close();
		if (piece.existeMovimentosAinda(piece.getCor()) == false) {
			piece.mudarCor();
		}
		if (tb.quantPieceBrancas() == 0) {
			piece.setCor(Cor.PRETO);
		}
		if (tb.quantPiecePretas() == 0) {
			piece.setCor(Cor.BRANCO);
		}

		System.out.println("PARAB�NS JOGADOR " + piece.getCor() + "VOCE VENCEU!!!");

	}
}