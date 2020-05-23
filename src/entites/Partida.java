package entites;

public class Partida {
	private Tabuleiro tabuleiro;
	private Cor JogadorCorreto;

	public Partida(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
		this.JogadorCorreto = Cor.BRANCO;
	}
	
	public Cor getJogadorCorreto() {
		return JogadorCorreto;
	}
	
	public boolean jogar(Posicao inicio, Posicao chegada) {
		if (this.tabuleiro.posicaoExiste(chegada.getLinha(), chegada.getColuna())) {
			if (this.tabuleiro.getMatTabuleiro(inicio.getLinha(), inicio.getColuna()) != null) {
				Dama dama = this.tabuleiro.getMatTabuleiro(inicio.getLinha(), inicio.getColuna());
				if (dama.getCor() == getJogadorCorreto()) {
					if (dama.moverDama(inicio, chegada)) {
						System.out.println("PECA MOVIDA COM SUCESSO");
						System.out.println();
						setJogadorCorreto(dama.getCor());
						return true;
					} else {
						System.out.println("MOVIMENTO INVALIDO!!!");
						System.out.println();
						return false;
					}
				}
				else {
					System.out.println("ESSA PECA NAO É SUA");
					System.out.println();
					return false;
				}
			}
			
		}
		return false;
	}

	public void setJogadorCorreto(Cor jogadorCorreto) {
		if(jogadorCorreto == Cor.PRETO) {
			JogadorCorreto = Cor.BRANCO;
		}
		else{
			JogadorCorreto = Cor.PRETO;
		}
	}

	
		
	public void preencherTabuleiro() {
		this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(0, 0),Cor.PRETO));
		this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(0, 2),Cor.PRETO));
		this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(0, 4),Cor.PRETO));
		this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(0, 6),Cor.PRETO));
		this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(1, 1),Cor.PRETO));
		this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(1, 3),Cor.PRETO));
		this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(1, 5),Cor.PRETO));
		this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(1, 7),Cor.PRETO));
		this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(2, 0),Cor.PRETO));
		this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(2, 2),Cor.PRETO));
		this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(2, 4),Cor.PRETO));
		this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(2, 6),Cor.PRETO));
		
	    this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(7, 1),Cor.BRANCO));
		this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(7, 3),Cor.BRANCO));
		this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(7, 5),Cor.BRANCO));
	    this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(7, 7),Cor.BRANCO));
		this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(6, 0),Cor.BRANCO));
		this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(6, 2),Cor.BRANCO));
		this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(6, 4),Cor.BRANCO));
		this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(6, 6),Cor.BRANCO));
		this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(5, 1),Cor.BRANCO));
		this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(5, 3),Cor.BRANCO));
		this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(5, 5),Cor.BRANCO));
		this.tabuleiro.adicionarPiece(new Dama(this.tabuleiro, new Posicao(5, 7),Cor.BRANCO));
	}


}
