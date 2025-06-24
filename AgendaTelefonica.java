/*
Classe AgendaTelefonica – faz o gerenciamento dos contatos. Ter os seguintes
métodos:

•adicionarContato(Contato contato): adiciona um novo contato à agenda.
•removerContato(String nome): remove um contato da agenda.
•buscarContato(String nome): busca e retorna um contato da agenda.
•listarContatos(): lista todos os contatos armazenados na agenda.
*/

package AgendaTeste;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AgendaTelefonica {

    public void adicionarContato(Contato contato) {
        String sql = "INSERT INTO contatos (nome, telefone, email) VALUES (?, ?, ?)";
        Connection conexao = ConexaoBD.getConnection();
        if (conexao == null) return;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, contato.getNome());
            stmt.setString(2, contato.getTelefone());
            stmt.setString(3, contato.getEmail());
            stmt.executeUpdate();
            System.out.println("Contato " + contato.getNome() + " adicionado com sucesso!");
        } catch (SQLException e) {
            System.err.println("Erro ao adicionar contato: " + e.getMessage());
            e.printStackTrace();
        } finally {
            ConexaoBD.closeConnection(conexao);
        }
    }

    public void removerContato(String nome) {
        String sql = "DELETE FROM contatos WHERE nome = ?";
        Connection conexao = ConexaoBD.getConnection();
        if (conexao == null) return;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            int linhasAfetadas = stmt.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Contato " + nome + " excluido com sucesso!");
            } else {
                System.out.println("Contato " + nome + " não encontrado.");
            }
        } catch (SQLException e) {
            System.err.println("Erro ao excluir contato: " + e.getMessage());
            e.printStackTrace();
        } finally {
            ConexaoBD.closeConnection(conexao);
        }
    }

    public Contato buscarContato(String nome) {
        String sql = "SELECT id, nome, telefone, email FROM contatos WHERE nome = ?";
        Connection conexao = ConexaoBD.getConnection();
        if (conexao == null) return null;
        Contato contato = null;

        try (PreparedStatement stmt = conexao.prepareStatement(sql)) {
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int id = rs.getInt("id");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                contato = new Contato(id, nome, telefone, email);
            }
        } catch (SQLException e) {
            System.err.println("Erro ao buscar contato: " + e.getMessage());
            e.printStackTrace();
        } finally {
            ConexaoBD.closeConnection(conexao);
        }
        return contato;
    }

    public List<Contato> listarContatos() {
        List<Contato> contatos = new ArrayList<>();
        String sql = "SELECT id, nome, telefone, email FROM contatos ORDER BY nome";
        Connection conexao = ConexaoBD.getConnection();
        if (conexao == null) return contatos;

        try (PreparedStatement stmt = conexao.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String nome = rs.getString("nome");
                String telefone = rs.getString("telefone");
                String email = rs.getString("email");
                contatos.add(new Contato(id, nome, telefone, email));
            }
        } catch (SQLException e) {
            System.err.println("Erro ao listar contatos: " + e.getMessage());
            e.printStackTrace();
        } finally {
            ConexaoBD.closeConnection(conexao);
        }
        return contatos;
    }
}