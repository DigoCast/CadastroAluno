package dao;

import factory.ConnectionFactory;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import modelo.Aluno;
import java.sql.ResultSet;


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
    
    public void atualizar(Aluno aluno) throws SQLException{
        String sql = "UPDATE Aluno SET alu_nome=?, alu_cpf=?, alu_nasc=?, alu_peso=?, alu_altura=? WHERE alu_cpf=?";
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
    
    public void deletar(Aluno aluno) throws SQLException{
        String sql = "DELETE FROM Aluno WHERE alu_cpf = ?";
        try{
            PreparedStatement stmt = connection.prepareStatement(sql);
            stmt.setInt(1, aluno.getAlu_cpf());
            stmt.execute();
            stmt.close();
        }catch (SQLException u){
            throw new RuntimeException(u);
        }
    }
    
    public boolean cpfJaCadastrado(String cpf) {
        String sql = "SELECT COUNT(*) AS total FROM Aluno WHERE alu_cpf = ?";

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, cpf); // Substitui o "?" pelo CPF digitado
            ResultSet rs = pstmt.executeQuery();
            
            if (rs.next()) {
                return rs.getInt("total") > 0; // Retorna true se o CPF já existe
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return false; // Retorna false caso ocorra algum erro
    }
}
