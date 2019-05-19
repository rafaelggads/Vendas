package negocio;

import java.util.ArrayList;

import persistencia.PUnidade;
import entidade.EUnidade;

public class NUnidade {
	private PUnidade pUnidade;
	
	public NUnidade() {
		pUnidade = new PUnidade();
	}
	
	public int incluir(EUnidade eUnidade) throws Exception {
		return this.pUnidade.incluir(eUnidade);
	}

	public void excluir(EUnidade eUnidade) throws Exception {
		this.pUnidade.excluir(eUnidade);
	}

	public void alterar(EUnidade eUnidade) throws Exception {
		this.pUnidade.alterar(eUnidade);
	}
	
	public EUnidade consultar(EUnidade eUnidade) throws Exception {
		return this.pUnidade.consultar(eUnidade);
	}
	
	public ArrayList<EUnidade> listar() throws Exception {
		return this.pUnidade.listar();
	}
}
