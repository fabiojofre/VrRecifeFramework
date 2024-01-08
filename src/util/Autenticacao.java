package util;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Autenticacao {
	Calendar c = Calendar.getInstance();
	SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
	String dataFormatada = formatter.format(c.getTime());
	
	public String retornaData() {
		return dataFormatada;
	}
	
	public boolean autenticacao(String usuario, String senha) {
		boolean retorno= false;
		if(usuario.equalsIgnoreCase("VRSOFTWARE")&& senha.equalsIgnoreCase(retornaData())) {
		retorno = true;
		}
		return retorno;
		
	}
	private String retornaSenhaPadrao() {
		String dia = dataFormatada.substring(0,2);
    	String mes = dataFormatada.substring(3,5);
		String ano = dataFormatada.substring(8,10);
		return "VR"+Integer.parseInt(mes)+dia+ano+"OFT";
	}

}
