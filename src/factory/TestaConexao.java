package factory;

import java.sql.Connection;
import java.io.IOException;
import java.sql.SQLException;

public class TestaConexao {
    public static void main(String[] args) throws IOException, SQLException {
        Connection connection = new ConnectionFactory().getConnection();
        System.out.println("Conexao aberta");
        connection.close();
    }
    
}
