package entites;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Tabuleiro {
	private Integer nlinha;
	private Integer ncoluna;

	private Dama[][] matTabuleiro;
	
	private List<Dama> listDamaTabuleiro = new ArrayList<>();
	private List<Dama> listDamaForaTabuleiro = new ArrayList<>();
	   
	public Tabuleiro(Integer nlinha, Integer ncoluna) {
		this.nlinha = nlinha;
		this.ncoluna = ncoluna;
		this.matTabuleiro = new Dama[nlinha][ncoluna];
	}

	public Integer getNlinha() {
		return nlinha;
	}

	public Integer getNcoluna() {
		return ncoluna;
	}
	public Dama getMatTabuleiro(Integer linha, Integer coluna) {
		return matTabuleiro[linha][coluna];
	}
	
	public List<Dama> getListDamaTabuleiro() {
		return listDamaTabuleiro;
	}

	public void setListDamaTabuleiro(List<Dama> listDamaTabuleiro) {
		this.listDamaTabuleiro = listDamaTabuleiro;
	}

	public List<Dama> getListDamaForaTabuleiro() {
		return listDamaForaTabuleiro;
	}

	public void setListDamaForaTabuleiro(List<Dama> listDamaForaTabuleiro) {
		this.listDamaForaTabuleiro = listDamaForaTabuleiro;
	}
	
	public void adicionarPiece(Dama dama) {	
		dama.tornarRainha(dama.getPosicao(), dama.getCor());
		this.matTabuleiro[dama.getPosicao().getLinha()][dama.getPosicao().getColuna()] = dama;
		this.listDamaTabuleiro.add(dama);
	}

	public void removePiece(Dama dama) {
		this.matTabuleiro[dama.getPosicao().getLinha()][dama.getPosicao().getColuna()] = null;
		this.listDamaTabuleiro.remove(new Dama(dama.getTabuleiro(),dama.getPosicao(), dama.getCor()));
		this.listDamaForaTabuleiro.add(dama);
	}
    
	public Integer quantPiecePretas() {	
		List<Dama> DamaPretasTabuleiro = this.listDamaTabuleiro.stream().filter(x -> x.getCor() == Cor.PRETO).collect(Collectors.toList());
		int quant = DamaPretasTabuleiro.size();
		return quant;
	}
	
	public Integer quantPieceBrancas() {	
		List<Dama> DamaBrancasTabuleiro = this.listDamaTabuleiro.stream().filter(x -> x.getCor() == Cor.BRANCO).collect(Collectors.toList());
		int quant = DamaBrancasTabuleiro.size();
		return quant;
	}

	public boolean[][] PocisaoDasDama(Cor cor) {
		boolean[][] aux = new boolean[this.nlinha][this.ncoluna];
		for (int i = 0; i < aux.length; i++) {
			for (int j = 0; j < aux.length; j++) {
				if(getMatTabuleiro(i, j) == null) {
					aux[i][j] = false;
				}
				else if(getMatTabuleiro(i, j).getCor() == cor) {
					aux[i][j] = true;
				}
				else {
					aux[i][j] = false;
				}
			}
		}

		return aux;
	}

	public boolean posicaoExiste(int linha, int coluna) {
		
		if (linha >= 0 && coluna >= 0 && this.nlinha > linha && this.ncoluna > coluna) {
			return true;
		}
		return false;
	}
	
	public void imprimirTabuleiro() {
		for (int i = 0; i <getNlinha(); i++) {
			System.out.printf(" %d ", (i + 1));
			for (int j = 0; j < getNcoluna(); j++) {
				if (getMatTabuleiro(i, j) == null) {
					System.out.print(" _ ");
				} else if (getMatTabuleiro(i, j).getCor() == Cor.BRANCO) {
					if(getMatTabuleiro(i, j).isRainha() == true) {
						System.out.print(" Q ");
					}
					else {
					 System.out.print(" O ");
					}
				} else if(getMatTabuleiro(i, j).getCor() == Cor.PRETO) {
					if(getMatTabuleiro(i, j).isRainha() == true) {
						System.out.print(" K ");
					}
					else {
						System.out.print(" X ");
					}
					
				}
			}
			System.out.println();
			System.out.println();
		}
		System.out.print("    ");
		for (int k = 'A'; k < ('A' + getNlinha()); k++) {
			System.out.print((char) k);
			System.out.print("  ");
		}

		System.out.println();
	}
	
	
	
		
}
