package util;

import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import conexao.ConexaoServidor;

public class TestaClasse {
	
	public static void main(String[] args) {
		ConexaoServidor con = new ConexaoServidor();
		try {
			
			Date date = new Date();
			date.getTime();
			PreparedStatement pstm = null;
			String sql = "INSERT INTO metaassessoria.log_prod(ean,dataconsulta) VALUES (?, ?)";
			con.abrirConexao("localhost", 8745, "vr", "postgres", "VrPost@Server");
			
			pstm =  con.prepareStatement(sql);
			// Adicionar os valores que são esperados pela query
			pstm.setString(1,"7891000100103");
			pstm.setDate(2, (java.sql.Date) date);

			// Executar a query
			pstm.execute();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
