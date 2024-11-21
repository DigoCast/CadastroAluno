package dao;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import modelo.Aluno;
import java.sql.ResultSet;
import java.time.LocalDate;


public class AlunoDAO {
    private final Connection connection;
    
    public AlunoDAO(){
        this.connection = new ConnectionFactory().getConnection();
    }
    
    public void inserir(Aluno aluno) throws SQLException{
        String sql = "INSERT INTO Aluno(alu_nome, alu_cpf, alu_nasc, alu_peso, alu_altura) VALUES(?, ?, ?, ?, ?)";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, aluno.getAlu_nome());
            stmt.setInt(2, aluno.getAlu_cpf());
            stmt.setDate(3, java.sql.Date.valueOf(aluno.getAlu_nasc()));
            stmt.setFloat(4, aluno.getAlu_peso());
            stmt.setFloat(5, aluno.getAlu_altura());
            stmt.execute();
            stmt.close();
        }catch (SQLException u){
            throw new RuntimeException(u);
        }
    }
    
    public boolean atualizar(Aluno aluno) throws SQLException{
        try {
            String sql = "UPDATE Aluno SET alu_nome=?, alu_nasc=?, alu_peso=?, alu_altura=? WHERE alu_cpf=?";
            PreparedStatement stmt = connection.prepareStatement(sql);

            // Substituindo os pontos de interrogação pelos parâmetros:
            stmt.setString(1, aluno.getAlu_nome()); // Nome do aluno
            System.out.println(aluno.getAlu_nome());
            stmt.setDate(2, java.sql.Date.valueOf(aluno.getAlu_nasc())); // Data de nascimento
            stmt.setFloat(3, aluno.getAlu_peso()); // Peso do aluno
            stmt.setFloat(4, aluno.getAlu_altura()); // Altura do aluno
            stmt.setInt(5, aluno.getAlu_cpf()); // CPF do aluno (onde será feita a atualização)

            // Executando o comando e verificando se alguma linha foi afetada
            int rowsAffected = stmt.executeUpdate();
            System.out.println(rowsAffected);
            if (rowsAffected > 0) {
                return true;  // Atualização bem-sucedida
            } else {
                return false;  // Nenhuma linha foi atualizada
            }
        } catch (SQLException ex) {
            ex.printStackTrace(); // Exibe o erro detalhado
            throw new RuntimeException(ex);
        }
    }
    
    public boolean deletar(String alu_cpf) throws SQLException{
        try{
            String sql = "DELETE FROM Aluno WHERE alu_cpf = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, alu_cpf);
            stmt.executeUpdate();
            return true;
        }catch (SQLException ex){
            return false;
        }
    }
    
    public Aluno consultar(String alu_cpf)throws SQLException{
        try{
            Aluno aluno = new Aluno();
            String sql = "SELECT * FROM Aluno WHERE alu_cpf = ?";
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setString(1, alu_cpf);
            ResultSet rs = stmt.executeQuery();
            
            if(rs.next()){
                aluno.setAlu_cpf(rs.getInt("alu_cpf"));
                aluno.setAlu_nome(rs.getString("alu_nome"));
                aluno.setAlu_nasc(rs.getDate("alu_nasc").toLocalDate());
                aluno.setAlu_peso(rs.getFloat("alu_peso"));
                aluno.setAlu_altura(rs.getFloat("alu_altura"));
                return aluno;
            }else{
                return null;
            }
        }catch (SQLException ex) {
            return null;
        }
    }
    
    public boolean cpfJaCadastrado(String cpf) {
        String sql = "SELECT COUNT(*) AS total FROM Aluno WHERE alu_cpf = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setString(1, cpf);
            ResultSet rs = stmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("total") > 0;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return false;
    }
    
}
