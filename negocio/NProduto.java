package negocio;

import java.util.ArrayList;

import persistencia.PProduto;
import entidade.EProduto;

public class NProduto {
	
	private PProduto pProduto;
	
	public NProduto()
	{
		pProduto = new PProduto();
	}
	
	public void validarEntrada(EProduto eproduto) throws Exception {
		
		//validar nome
		if(eproduto.getNome().length()<=0)
			throw new Exception("Nome deve ser preenchido!");
		// Valida unidade do produto
				if (eproduto.getEUnidade().getId() == 0)
					throw new Exception("Selecione uma unidade!");

	}
	
	public int incluir(EProduto eProduto) throws Exception{
		return this.pProduto.incluir(eProduto);
	}
	
	public void alterar(EProduto eProduto) throws Exception{
		this.pProduto.alterar(eProduto);
	}
	
	public void excluir(EProduto eProduto) throws Exception{
		this.pProduto.excluir(eProduto);
	}
	
	public EProduto consultar(EProduto eProduto)throws Exception
	{
		return this.pProduto.consultar(eProduto);		
	}
	
	public ArrayList<EProduto> listar() throws Exception
	{
		return this.pProduto.listar();
	}

}