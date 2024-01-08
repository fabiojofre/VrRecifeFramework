package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;


public class ConexaoServidor {
  private int contBegin = 0;
  
  private Connection con;
  
  private String ip = "";
  
  private String ipSec = "";
  
  private int porta = 0;
  
  private String dataBase = "";
  
  private String usuario = "";
  
  private String senha = "";
  
  public void abrirConexao(String i_ip, int i_porta, String i_database, String i_usuario, String i_senha) throws Exception {
    abrirConexao(i_ip, "", i_porta, i_database, i_usuario, i_senha);
  }
  
  public void abrirConexao(String i_ip, String i_ipSec, int i_porta, String i_database, String i_usuario, String i_senha) throws Exception {
    Class.forName("org.postgresql.Driver");
    this.ip = i_ip;
    this.ipSec = i_ipSec;
    this.porta = i_porta;
    this.dataBase = i_database;
    this.usuario = i_usuario;
    this.senha = i_senha;
    try {
      this.con = DriverManager.getConnection("jdbc:postgresql://" + i_ip + ":" + i_porta + "/" + i_database, i_usuario, i_senha);
    } catch (Exception ex) {
      if (!this.ipSec.isEmpty()) {
        this.con = DriverManager.getConnection("jdbc:postgresql://" + i_ipSec + ":" + i_porta + "/" + i_database, i_usuario, i_senha);
      } else {
        throw ex;
      } 
    } 
  }
  
  public Connection getConexao() {
    return this.con;
  }
  
  public Statement createStatement() throws Exception {
    testarConexao();
    return this.con.createStatement(1004, 1007);
  }
  
  public void begin() throws Exception {
    if (this.contBegin == 0)
      this.con.setAutoCommit(false); 
    this.contBegin++;
  }
  
  public void commit() throws Exception {
    this.contBegin--;
    if (this.contBegin == 0) {
      this.con.commit();
      this.con.setAutoCommit(true);
    } 
  }
  
  public void rollback() throws Exception {
    this.contBegin--;
    if (this.contBegin == 0) {
      this.con.rollback();
      this.con.setAutoCommit(true);
    } 
  }
  
  public void close() throws Exception {
    this.con.close();
  }
  
  private void testarConexao() throws Exception {
    Statement stm = null;
    try {
      stm = this.con.createStatement(1004, 1007);
      stm.execute("SELECT 1");
      stm.close();
    } catch (Exception ex) {
      abrirConexao(this.ip, this.ipSec, this.porta, this.dataBase, this.usuario, this.senha);
    } 
  }
  
  public PreparedStatement prepareStatement(String i_sql) throws Exception {
    return this.con.prepareStatement(i_sql);
  }
  
  public void setApplicationName(String i_applicationName) throws Exception {
    Statement stm = createStatement();
    stm.execute("SET application_name TO '" + i_applicationName + "'");
    stm.close();
  }
}
