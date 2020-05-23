package entites;

public class Dama {
	private Cor cor;
	private Posicao posicao;
	private Tabuleiro tabuleiro;
	private boolean rainha;

	public Dama(Tabuleiro tabuleiro, Posicao posicao, Cor cor) {
		this.cor = cor;
		this.posicao = posicao;
		this.tabuleiro = tabuleiro;
	}

	public Cor getCor() {
		return cor;
	}

	public void setCor(Cor cor) {
		this.cor = cor;
	}

	public Posicao getPosicao() {
		return posicao;
	}

	public void setPosicao(Posicao posicao) {
		this.posicao = posicao;
	}

	public Tabuleiro getTabuleiro() {
		return tabuleiro;
	}

	public void setTabuleiro(Tabuleiro tabuleiro) {
		this.tabuleiro = tabuleiro;
	}

	public boolean isRainha() {
		return rainha;
	}

	public void setRainha(boolean rainha) {
		this.rainha = rainha;
	}
	
	public void tornarRainha(Posicao chegada, Cor piece) {
		if (chegada.getLinha() == 7 && cor == Cor.PRETO) {
			this.rainha = true;
		} else if (chegada.getLinha() == 0 && cor == Cor.BRANCO) {
			this.rainha = true;
		}

	}
   
	public void mudarCor() {
		if(this.cor == Cor.BRANCO) {
		    setCor(Cor.PRETO);
		}
		else {
			setCor(Cor.BRANCO);
		}	
	}

	public boolean moverDama(Posicao inicio, Posicao chegada) {
		if ((inicio.getLinha() == chegada.getLinha() + 1 || inicio.getLinha() == chegada.getLinha() - 1)) {
			if(andar(inicio, chegada)) {
				return true;
			}
		
			return false;
		} else if ((inicio.getLinha() == chegada.getLinha() + 2 || inicio.getLinha() == chegada.getLinha() - 2)) {
			if (comerDama(inicio, chegada)) {
				inicio = chegada;
				chegada = chegada.novaPosicao(this.tabuleiro);

				while (comerMais(inicio)) {
					if (comerDama(inicio, chegada)) {
						inicio = chegada;
						chegada = chegada.novaPosicao(this.tabuleiro);
					} else {
						System.out.println("Não foi possivel comer peca");
						return false;
					}

				}
				return true;
			}
			else {
				System.out.println("Não foi possivel comer a peca");
				return false;
			}

		}
		return false;
	}

	public Posicao posicaodoAdv(Posicao inicio, Posicao chegada) {
		Posicao posicaoAdv = new Posicao(0, 0);
		if (this.tabuleiro.posicaoExiste(inicio.getLinha(), inicio.getColuna())
				&& this.tabuleiro.posicaoExiste(chegada.getLinha(), chegada.getColuna())) {
			if (this.tabuleiro.getMatTabuleiro(inicio.getLinha(), inicio.getColuna()) != null) {
				Dama dama = this.tabuleiro.getMatTabuleiro(inicio.getLinha(), inicio.getColuna());

				if (inicio.getLinha() + 2 == chegada.getLinha() && inicio.getColuna() + 2 == chegada.getColuna()
						&& this.tabuleiro.posicaoExiste(inicio.getLinha() + 1, inicio.getColuna() + 1)) {
					if (this.tabuleiro.getMatTabuleiro(inicio.getLinha() + 1, inicio.getColuna() + 1) != null) {
						if (this.tabuleiro.getMatTabuleiro(inicio.getLinha() + 1, inicio.getColuna() + 1)
								.getCor() != dama.getCor()) {
							posicaoAdv.setLinha(inicio.getLinha() + 1);
							posicaoAdv.setColuna(inicio.getColuna() + 1);
							return posicaoAdv;
						}
					}
				}

				if (inicio.getLinha() + 2 == chegada.getLinha() && inicio.getColuna() - 2 == chegada.getColuna()
						&& this.tabuleiro.posicaoExiste(inicio.getLinha() + 1, inicio.getColuna() - 1)) {
					if (this.tabuleiro.getMatTabuleiro(inicio.getLinha() + 1, inicio.getColuna() - 1) != null) {
						if (this.tabuleiro.getMatTabuleiro(inicio.getLinha() + 1, inicio.getColuna() - 1)
								.getCor() != dama.getCor()) {
							posicaoAdv.setLinha(inicio.getLinha() + 1);
							posicaoAdv.setColuna(inicio.getColuna() - 1);
							return posicaoAdv;
						}
					}
				}

				if (inicio.getLinha() - 2 == chegada.getLinha() && inicio.getColuna() + 2 == chegada.getColuna()
						&& this.tabuleiro.posicaoExiste(inicio.getLinha() - 1, inicio.getColuna() + 1)) {
					if (this.tabuleiro.getMatTabuleiro(inicio.getLinha() - 1, inicio.getColuna() + 1) != null) {
						if (this.tabuleiro.getMatTabuleiro(inicio.getLinha() - 1, inicio.getColuna() + 1)
								.getCor() != dama.getCor()) {
							posicaoAdv.setLinha(inicio.getLinha() - 1);
							posicaoAdv.setColuna(inicio.getColuna() + 1);
							return posicaoAdv;
						}
					}
				}

				if (inicio.getLinha() - 2 == chegada.getLinha() && inicio.getColuna() - 2 == chegada.getColuna()
						&& this.tabuleiro.posicaoExiste(inicio.getLinha() - 1, inicio.getColuna() - 1)) {
					if (this.tabuleiro.getMatTabuleiro(inicio.getLinha() - 1, inicio.getColuna() - 1) != null) {
						if (this.tabuleiro.getMatTabuleiro(inicio.getLinha() - 1, inicio.getColuna() - 1)
								.getCor() != dama.getCor()) {
							posicaoAdv.setLinha(inicio.getLinha() - 1);
							posicaoAdv.setColuna(inicio.getColuna() - 1);
							return posicaoAdv;
						}
					}
				}

			}
		}
		return posicaoAdv;
	}

	public boolean andar(Posicao inicio, Posicao chegada) {
		if (this.tabuleiro.getMatTabuleiro(inicio.getLinha(), inicio.getColuna()) != null) {
			Dama dama = this.tabuleiro.getMatTabuleiro(inicio.getLinha(), inicio.getColuna());
			boolean aux[][] = movPossiveis(dama);
			for (int i = 0; i < aux.length; i++) {
				for (int j = 0; j < aux.length; j++) {
					if (aux[i][j] == true && chegada.getLinha() == i && chegada.getColuna() == j) {
						this.tabuleiro.removePiece(dama);
						dama.setPosicao(chegada);
						this.tabuleiro.adicionarPiece(dama);
						return true;
					}

				}
			}
		}
		return false;

	}

	public boolean comerDama(Posicao inicio, Posicao chegada) {
		Posicao posicaoAdv = new Posicao(0, 0);
		Dama damaAdv = new Dama(tabuleiro, posicaoAdv, Cor.BRANCO);
		if (this.tabuleiro.getMatTabuleiro(inicio.getLinha(), inicio.getColuna()) != null) {
			Dama dama = this.tabuleiro.getMatTabuleiro(inicio.getLinha(), inicio.getColuna());
			posicaoAdv = posicaodoAdv(inicio, chegada);
			if (validarComerDama(dama, chegada, dama.getCor(), posicaoAdv)) {
				damaAdv = this.tabuleiro.getMatTabuleiro(posicaoAdv.getLinha(), posicaoAdv.getColuna());
				this.tabuleiro.removePiece(dama);
				this.tabuleiro.removePiece(damaAdv);
				dama.setPosicao(chegada);
				this.tabuleiro.adicionarPiece(dama);
				return true;
			}
		}
		return false;

	}

	public boolean comerMais(Posicao inicio) {
		if (this.tabuleiro.getMatTabuleiro(inicio.getLinha(), inicio.getColuna()) != null) {
			Dama dama = this.tabuleiro.getMatTabuleiro(inicio.getLinha(), inicio.getColuna());
			if (dama.getCor() == Cor.PRETO) {
				if (this.tabuleiro.posicaoExiste(inicio.getLinha() + 2, inicio.getColuna() + 2)) {
					if (this.tabuleiro.getMatTabuleiro(inicio.getLinha() + 2, inicio.getColuna() + 2) == null) {
						Posicao chegada = new Posicao(inicio.getLinha() + 2, inicio.getColuna() + 2);
						Posicao posicaoAdv = posicaodoAdv(inicio, chegada);
						if (posicaoAdv.getLinha() != 0 && posicaoAdv.getColuna() != 0) {
							return true;
						}
					}

				}

				if (this.tabuleiro.posicaoExiste(inicio.getLinha() + 2, inicio.getColuna() - 2)) {
					if (this.tabuleiro.getMatTabuleiro(inicio.getLinha() + 2, inicio.getColuna() - 2) == null) {
						Posicao chegada = new Posicao(inicio.getLinha() + 2, inicio.getColuna() - 2);
						Posicao posicaoAdv = posicaodoAdv(inicio, chegada);
						if (posicaoAdv.getLinha() != 0 && posicaoAdv.getColuna() != 0) {
							return true;
						}
					}

				}
			}
			if (dama.getCor() == Cor.BRANCO) {
				if (this.tabuleiro.posicaoExiste(inicio.getLinha() - 2, inicio.getColuna() - 2)) {
					if (this.tabuleiro.getMatTabuleiro(inicio.getLinha() - 2, inicio.getColuna() - 2) == null) {
						Posicao chegada = new Posicao(inicio.getLinha() - 2, inicio.getColuna() - 2);
						Posicao posicaoAdv = posicaodoAdv(inicio, chegada);
						if (posicaoAdv.getLinha() != 0 && posicaoAdv.getColuna() != 0) {
							return true;
						}
					}

				}

				if (this.tabuleiro.posicaoExiste(inicio.getLinha() - 2, inicio.getColuna() + 2)) {
					if (this.tabuleiro.getMatTabuleiro(inicio.getLinha() - 2, inicio.getColuna() + 2) == null) {
						Posicao chegada = new Posicao(inicio.getLinha() - 2, inicio.getColuna() + 2);
						Posicao posicaoAdv = posicaodoAdv(inicio, chegada);
						if (posicaoAdv.getLinha() != 0 && posicaoAdv.getColuna() != 0) {
							return true;
						}
					}

				}
			}
		}

		return false;

	}

	public boolean validarAndar(Dama dama, Posicao posicao, Cor cor) {
		if (cor == Cor.PRETO) {
			if (this.tabuleiro.posicaoExiste(posicao.getLinha(), posicao.getColuna())
					&& (dama.getCor() == Cor.PRETO || dama.rainha == true)) {
				if (this.tabuleiro.getMatTabuleiro(posicao.getLinha(), posicao.getColuna()) == null) {
					return true;
				}
			}
		} else if (cor == Cor.BRANCO) {
			if (this.tabuleiro.posicaoExiste(posicao.getLinha(), posicao.getColuna())
					&& (dama.getCor() == Cor.BRANCO || dama.rainha == true)) {
				if (this.tabuleiro.getMatTabuleiro(posicao.getLinha(), posicao.getColuna()) == null) {
					return true;
				}
			}
		}

		return false;
	}

	public boolean validarComerDama(Dama dama, Posicao pChegada, Cor cor, Posicao posicaoAdv) {
		if (cor == Cor.PRETO) {
			if (this.tabuleiro.posicaoExiste(pChegada.getLinha(), pChegada.getColuna())
					&& this.tabuleiro.posicaoExiste(posicaoAdv.getLinha(), posicaoAdv.getColuna())
					&& (dama.getCor() == Cor.PRETO || dama.rainha == true)) {
				if (this.tabuleiro.getMatTabuleiro(pChegada.getLinha(), pChegada.getColuna()) == null
						&& (this.tabuleiro.getMatTabuleiro(posicaoAdv.getLinha(), posicaoAdv.getColuna()) != null)
						&& (this.tabuleiro.getMatTabuleiro(dama.getPosicao().getLinha(),
								dama.getPosicao().getColuna()) != null)) {

					if (this.tabuleiro.getMatTabuleiro(posicaoAdv.getLinha(), posicaoAdv.getColuna()).getCor() != dama
							.getCor()
							&& this.tabuleiro
									.getMatTabuleiro(dama.getPosicao().getLinha(), dama.getPosicao().getColuna())
									.getCor() == dama.getCor()) {
						return true;
					}

				}
			}
		} else if (cor == Cor.BRANCO) {
			if (this.tabuleiro.posicaoExiste(pChegada.getLinha(), pChegada.getColuna())
					&& this.tabuleiro.posicaoExiste(posicaoAdv.getLinha(), posicaoAdv.getColuna())
					&& (dama.getCor() == Cor.BRANCO || dama.rainha == true)) {
				if (this.tabuleiro.getMatTabuleiro(pChegada.getLinha(), pChegada.getColuna()) == null
						&& (this.tabuleiro.getMatTabuleiro(posicaoAdv.getLinha(), posicaoAdv.getColuna()) != null)
						&& (this.tabuleiro.getMatTabuleiro(dama.getPosicao().getLinha(),
								dama.getPosicao().getColuna()) != null)) {

					if (this.tabuleiro.getMatTabuleiro(posicaoAdv.getLinha(), posicaoAdv.getColuna()).getCor() != dama
							.getCor()
							&& this.tabuleiro
									.getMatTabuleiro(dama.getPosicao().getLinha(), dama.getPosicao().getColuna())
									.getCor() == dama.getCor()) {

						return true;
					}

				}
			}

		}
		return false;
	}

	public boolean[][] movPossiveis(Dama dama) {
		boolean[][] aux = new boolean[this.tabuleiro.getNlinha()][this.tabuleiro.getNcoluna()];
		Posicao posicao = new Posicao(0, 0);
		Posicao posicaoAdv = new Posicao(0, 0);

		// Peca preta andar
		posicao.setLinha(dama.getPosicao().getLinha() + 1);
		posicao.setColuna(dama.getPosicao().getColuna() + 1);
		if (validarAndar(dama, posicao, Cor.PRETO)) {
			aux[posicao.getLinha()][posicao.getColuna()] = true;
		}
		posicao.setColuna(dama.getPosicao().getColuna() - 1);
		if (validarAndar(dama, posicao, Cor.PRETO)) {
			aux[posicao.getLinha()][posicao.getColuna()] = true;
		}
		// Peca branca andar
		posicao.setLinha(dama.getPosicao().getLinha() - 1);
		posicao.setColuna(dama.getPosicao().getColuna() + 1);
		if (validarAndar(dama, posicao, Cor.BRANCO)) {
			aux[posicao.getLinha()][posicao.getColuna()] = true;
		}
		posicao.setColuna(dama.getPosicao().getColuna() - 1);
		if (validarAndar(dama, posicao, Cor.BRANCO)) {
			aux[posicao.getLinha()][posicao.getColuna()] = true;
		}

		// Comer peca preta
		posicao.setLinha(dama.getPosicao().getLinha() + 2);
		posicao.setColuna(dama.getPosicao().getColuna() + 2);
		posicaoAdv.setLinha(dama.getPosicao().getLinha() + 1);
		posicaoAdv.setColuna(dama.getPosicao().getColuna() + 1);

		if (validarComerDama(dama, posicao, Cor.PRETO, posicaoAdv)) {
			aux[posicao.getLinha()][posicao.getColuna()] = true;
		}

		posicao.setColuna(dama.getPosicao().getColuna() - 2);
		posicaoAdv.setColuna(dama.getPosicao().getColuna() - 1);
		if (validarComerDama(dama, posicao, Cor.PRETO, posicaoAdv)) {
			aux[posicao.getLinha()][posicao.getColuna()] = true;
		}
		// comer peca branca
		posicao.setLinha(dama.getPosicao().getLinha() - 2);
		posicao.setColuna(dama.getPosicao().getColuna() + 2);
		posicaoAdv.setLinha(dama.getPosicao().getLinha() - 1);
		posicaoAdv.setColuna(dama.getPosicao().getColuna() + 1);
		if (validarComerDama(dama, posicao, Cor.BRANCO, posicaoAdv)) {
			aux[posicao.getLinha()][posicao.getColuna()] = true;
		}

		posicao.setColuna(dama.getPosicao().getColuna() - 2);
		posicaoAdv.setColuna(dama.getPosicao().getColuna() - 1);
		if (validarComerDama(dama, posicao, Cor.BRANCO, posicaoAdv)) {
			aux[posicao.getLinha()][posicao.getColuna()] = true;
		}

		return aux;
	}

	public Integer quantMovPossiveis(Dama dama) {
		Integer quant = 0;
		boolean[][] aux = movPossiveis(dama);
		for (int i = 0; i < aux.length; i++) {
			for (int j = 0; j < aux.length; j++) {
				if (aux[i][j]) {
					quant++;
				}
			}
		}

		return quant;
	}
	
	public boolean existeMovimentosAinda(Cor cor) {
		boolean[][] aux = this.tabuleiro.PocisaoDasDama(cor);
		for (int i = 0; i < aux.length; i++) {
			for (int j = 0; j < aux.length; j++) {
				if (aux[i][j] == true) {
					Dama dama = this.tabuleiro.getMatTabuleiro(i, j);
					if (dama.quantMovPossiveis(dama) > 0) {
						return true;
					}

				}
			}
		}

		return false;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cor == null) ? 0 : cor.hashCode());
		result = prime * result + ((posicao == null) ? 0 : posicao.hashCode());
		result = prime * result + (rainha ? 1231 : 1237);
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
		Dama other = (Dama) obj;
		if (cor != other.cor)
			return false;
		if (posicao == null) {
			if (other.posicao != null)
				return false;
		} else if (!posicao.equals(other.posicao))
			return false;
		if (rainha != other.rainha)
			return false;
		return true;
	}

	

}
