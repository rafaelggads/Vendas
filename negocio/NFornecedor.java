package negocio;

import java.util.ArrayList;
import java.util.InputMismatchException;

import javax.swing.JOptionPane;

import persistencia.PFornecedor;
import entidade.EFornecedor;



public class NFornecedor {
	private PFornecedor pFornecedor;
	
	public NFornecedor(){
		pFornecedor = new PFornecedor();
	}
	
	public void validarEntrada(EFornecedor eFornecedor) throws Exception{
		//Validar Nome
		if(eFornecedor.getNome().length()<=0)
			throw new Exception("Nome deve ser preenchido!");
		// validar endereco
		if(eFornecedor.getEndereco().length()<=0)
			throw new Exception("Endere�o deve ser preenchido");
		// validar telefone
		if(eFornecedor.getTelefone().length()<=0)
			throw new Exception("Telefone deve ser preenchido");
		
		//if(eFornecedor.getCnpj().length()<=11)throw new Exception("Cnpj Invalido");
		//if(isCNPJ(eFornecedor.getCnpj()))throw new Exception("Cnpj Invalido");	
		
	}
	
	public static boolean isCNPJ(String CNPJ) {
		// considera-se erro CNPJ's formados por uma sequencia de numeros iguais
		    if (CNPJ.equals("00000000000000") || CNPJ.equals("11111111111111") ||
		        CNPJ.equals("22222222222222") || CNPJ.equals("33333333333333") ||
		        CNPJ.equals("44444444444444") || CNPJ.equals("55555555555555") ||
		        CNPJ.equals("66666666666666") || CNPJ.equals("77777777777777") ||
		        CNPJ.equals("88888888888888") || CNPJ.equals("99999999999999") ||
		       (CNPJ.length() != 14))
		       return(false);
		 
		    char dig13, dig14;
		    int sm, i, r, num, peso;
		 
		// "try" - protege o c�digo para eventuais erros de conversao de tipo (int)
		    try {
		// Calculo do 1o. Digito Verificador
		      sm = 0;
		      peso = 2;
		      for (i=11; i>=0; i--) {
		// converte o i-�simo caractere do CNPJ em um n�mero:
		// por exemplo, transforma o caractere '0' no inteiro 0
		// (48 eh a posi��o de '0' na tabela ASCII)
		        num = (int)(CNPJ.charAt(i) - 48);
		        sm = sm + (num * peso);
		        peso = peso + 1;
		        if (peso == 10)
		           peso = 2;
		      }
		 
		      r = sm % 11;
		      if ((r == 0) || (r == 1))
		         dig13 = '0';
		      else dig13 = (char)((11-r) + 48);
		 
		// Calculo do 2o. Digito Verificador
		      sm = 0;
		      peso = 2;
		      for (i=12; i>=0; i--) {
		        num = (int)(CNPJ.charAt(i)- 48);
		        sm = sm + (num * peso);
		        peso = peso + 1;
		        if (peso == 10)
		           peso = 2;
		      }
		 
		      r = sm % 11;
		      if ((r == 0) || (r == 1))
		         dig14 = '0';
		      else dig14 = (char)((11-r) + 48);
		 
		// Verifica se os d�gitos calculados conferem com os d�gitos informados.
		      if ((dig13 == CNPJ.charAt(12)) && (dig14 == CNPJ.charAt(13)))
		         return(true);
		      else return(false);
		    } catch (InputMismatchException erro) {
		        return(false);
		    }
		  }
		 
		  public static String imprimeCNPJ(String CNPJ) {
		// m�scara do CNPJ: 99.999.999.9999-99
		    return(CNPJ.substring(0, 2) + "." + CNPJ.substring(2, 5) + "." +
		      CNPJ.substring(5, 8) + "." + CNPJ.substring(8, 12) + "-" +
		      CNPJ.substring(12, 14));
		  }

		public int incluir(EFornecedor eFornecedor)throws Exception {
			return this.pFornecedor.incluir(eFornecedor);
		}
		public void excluir(EFornecedor eFornecedor) throws Exception
		{
			this.pFornecedor.excluir(eFornecedor);
		}
		public void alterar(EFornecedor eFornecedor) throws Exception
		{
			this.pFornecedor.alterar(eFornecedor);
		}
		public EFornecedor consultar(EFornecedor eFornecedor) throws Exception
		{
			return this.pFornecedor.consultar(eFornecedor);
		}
		
		public ArrayList<EFornecedor> listar() throws Exception
		{
			return this.pFornecedor.listar();
		}
		
		



}
