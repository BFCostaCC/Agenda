package AgendaTeste;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoBD {
    private static final String URL = "jdbc:mysql://localhost:3306/Agenda";
    private static final String USUARIO = "Bruna_"; 
    private static final String SENHA = "Server@123";

    public static Connection getConnection() {
        Connection conexao = null;
        try {
            // Carrega o driver JDBC
            Class.forName("com.mysql.cj.jdbc.Driver");
            conexao = DriverManager.getConnection(URL, USUARIO, SENHA);
            System.out.println("Conexão com o banco de dados estabelecida com sucesso!");
        } catch (ClassNotFoundException e) {
            System.err.println("Erro: Driver JDBC não encontrado. Adicione o MySQL Connector/J ao seu projeto.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.err.println("Erro ao conectar ao banco de dados: " + e.getMessage());
            e.printStackTrace();
        }
        return conexao;
    }

    public static void closeConnection(Connection conexao) {
        if (conexao != null) {
            try {
                conexao.close();
                System.out.println("Conexão com o banco de dados fechada.");
            } catch (SQLException e) {
                System.err.println("Erro ao fechar a conexão com o banco de dados: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}