package repositorio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.david.DatabaseConnection;

import modelo.Produto;

public class ProdutoRepository {

    public void adicionarProduto(Produto produto) {
        String sql = "INSERT INTO produto (id, nome, preco) VALUES (?, ?, ?)";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, produto.getId());
            statement.setString(2, produto.getNome());
            statement.setDouble(3, produto.getPreco());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Produto> listarProdutos() {
        List<Produto> produtos = new ArrayList<>();
        String sql = "SELECT * FROM produto";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String nome = resultSet.getString("nome");
                double preco = resultSet.getDouble("preco");
                Produto produto = new Produto(id, nome, preco);
                produtos.add(produto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produtos;
    }

    public Produto buscarProdutoPorId(int id) {
        Produto produto = null;
        String sql = "SELECT * FROM produto WHERE id = ?";

        try (Connection connection = DatabaseConnection.getConnection();
                PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String nome = resultSet.getString("nome");
                    double preco = resultSet.getDouble("preco");
                    produto = new Produto(id, nome, preco);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return produto;
    }
}
