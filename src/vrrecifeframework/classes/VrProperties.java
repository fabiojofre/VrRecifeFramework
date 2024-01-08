package vrrecifeframework.classes;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import javax.swing.JOptionPane;

public class VrProperties {
	private static String arquivo = "config.properties";
	
	public static Properties getProp() throws IOException {
		Properties props = new Properties();
		InputStream file = new FileInputStream(arquivo);
		props.load(file);
		return props;

	}

	public static String getString(String params){
		Properties prop = null;
	
		try {
			prop = getProp();	
		} catch (IOException e) {
			// TODO Auto-generated catch block
		    JOptionPane.showMessageDialog(null,"Valor não encontrado: "+"\n"+e);	
		    e.printStackTrace();
		}
		String valor = prop.getProperty(params);
		return valor;
	}
	
	public static int getInt(String params){
		Properties prop = null;
		try {
			prop = getProp();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		int valor = Integer.parseInt(prop.getProperty(params));
		return valor;
	}

	public static double getDouble(String params) {
		Properties prop = null;
		try {
			prop = getProp();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		double valor = Double.parseDouble(prop.getProperty(params));
		return valor;
	}
	
	public static void setProperty(String parametro, String valor) {
		
		try {
			File file = new File(arquivo);
			Properties prop = new Properties();
			FileInputStream fis= new FileInputStream(file);
		
			prop.load(fis);

			prop.setProperty(parametro, valor.toString());
			 FileWriter writer = new FileWriter(file);
			 prop.store(writer, arquivo);
	            writer.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
