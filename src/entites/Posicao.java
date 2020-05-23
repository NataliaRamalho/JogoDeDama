package entites;

public class Posicao {
	private Integer linha;
	private Integer coluna;
	
	public Posicao(Integer linha, Integer coluna) {
		this.linha = linha;
		this.coluna = coluna;
	}

	public Integer getLinha() {
		return linha;
	}

	public void setLinha(Integer linha) {
		this.linha = linha;
	}

	public Integer getColuna() {
		return coluna;
	}

	public void setColuna(Integer coluna) {
		this.coluna = coluna;
	}
	
	public boolean validarPosicaoInicio(Partida partida, Tabuleiro tabuleiro) {
		if(tabuleiro.posicaoExiste(getLinha(), getColuna())) {
			if(tabuleiro.getMatTabuleiro(getLinha(), getColuna()) != null) {
				if(tabuleiro.getMatTabuleiro(getLinha(), getColuna()).getCor() == partida.getJogadorCorreto()) {
					Dama dama = tabuleiro.getMatTabuleiro(getLinha(), getColuna());
					if(dama.quantMovPossiveis(dama)>0) {
						return true;
					}
				}
			}
		}
		return false;
	}

	public Posicao novaPosicao(Tabuleiro tabuleiro) {
		Posicao novaPosicao = new Posicao(0, 0);
		Dama dama = tabuleiro.getMatTabuleiro(getLinha(), getColuna());
		if (tabuleiro.posicaoExiste(getLinha() + 2, getColuna() + 2)) {
			if (tabuleiro.getMatTabuleiro(getLinha() + 2, getColuna() + 2) == null && dama.getCor() == Cor.PRETO)  {
				novaPosicao.setLinha(getLinha()+2);
				novaPosicao.setColuna(getColuna()+2);
				return novaPosicao;
			}
		}
		if (tabuleiro.posicaoExiste(getLinha() + 2, getColuna() - 2)) {
			if (tabuleiro.getMatTabuleiro(getLinha() + 2, getColuna() - 2) == null && dama.getCor() == Cor.PRETO)  {
				novaPosicao.setLinha(getLinha()+2);
				novaPosicao.setColuna(getColuna()-2);
				return novaPosicao;
			}
		}
		
		if (tabuleiro.posicaoExiste(getLinha() - 2, getColuna() - 2)) {
			if (tabuleiro.getMatTabuleiro(getLinha() - 2, getColuna() - 2) == null && dama.getCor() == Cor.BRANCO)  {
				novaPosicao.setLinha(getLinha()-2);
				novaPosicao.setColuna(getColuna()-2);
				return novaPosicao;
			}
		}
		if (tabuleiro.posicaoExiste(getLinha() - 2, getColuna() + 2)) {
			if (tabuleiro.getMatTabuleiro(getLinha() - 2, getColuna() + 2) == null && dama.getCor() == Cor.BRANCO)  {
				novaPosicao.setLinha(getLinha()-2);
				novaPosicao.setColuna(getColuna()+2);
				return novaPosicao;
			}
		}
		return novaPosicao;
	}
	
	public static Posicao converteVetor(String posicao, Tabuleiro tabuleiro) {
		try {
			char b[] = posicao.toCharArray();
			Integer linha = Character.getNumericValue(b[0] - 1);
			int quant = 0, coluna = 0;
			for (int i = 'A'; i < ('A' + tabuleiro.getNcoluna()); i++) {
				if (b[1] == (char) i) {
					coluna = quant;
				}
				quant++;
			}
			if (coluna == 0 && b[1] != 'A') {
				quant = 0;
				for (int i = 'a'; i < ('a' + tabuleiro.getNcoluna()); i++) {
					if (b[1] == (char) i) {
						coluna = quant;
					}
					quant++;
				}
			}
			return new Posicao(linha, coluna);
		} catch (RuntimeException e) {
              System.out.println("VOCE DIGITOU UMA POSICAO INVALIDA ");
              System.out.println("DIGITE SEMPRE UMA NUMERO(LINHA) E UMA LETRA(COLUNA)");
              System.out.println();
		}
		return null;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((coluna == null) ? 0 : coluna.hashCode());
		result = prime * result + ((linha == null) ? 0 : linha.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Posicao other = (Posicao) obj;
		if (coluna == null) {
			if (other.coluna != null)
				return false;
		} else if (!coluna.equals(other.coluna))
			return false;
		if (linha == null) {
			if (other.linha != null)
				return false;
		} else if (!linha.equals(other.linha))
			return false;
		return true;
	}
	
	


}
