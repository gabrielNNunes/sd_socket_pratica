package pratica03;

import java.net.*;
import java.io.*;

public class Servidor {

	public static void main(String[] args) throws IOException, EOFException {
		
		ServerSocket s = new ServerSocket(2001);
		
		while (true) {
			System.out.println("Esperando conectar...");
			Socket conexao = s.accept();
			System.out.println("Cliente iniciou uma conexão!");
			DataInputStream entrada = new DataInputStream(conexao.getInputStream());
			DataOutputStream saida = new DataOutputStream(conexao.getOutputStream());
			BufferedReader teclado = new BufferedReader(new InputStreamReader(System.in));

			
			String linha = entrada.readUTF();
			
			while (linha != null && !(linha.trim().equals("")) && !(linha.trim().equalsIgnoreCase("Sair"))) {
				
				System.out.println("Cliente: "+linha);
				
				System.out.print("> ");
				
				linha = teclado.readLine();				
				
				saida.writeUTF(linha);			
								
				linha = entrada.readUTF();
				
				if (linha.equalsIgnoreCase("Sair")) {
					System.out.println("Conexao encerrada!");
					break;				}
			}
			saida.writeUTF(linha);
			conexao.close();
		}
	}
}
