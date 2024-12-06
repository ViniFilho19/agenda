package implementacaoDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dao.ContatoDao;
import db.DB;
import db.DbException;
import entidades.Contato;

public class ContatoDaoJDBC implements ContatoDao {
    private Connection conn;

    public ContatoDaoJDBC(Connection conn) {
        this.conn = conn;
    }

    @Override
    public void insert(Contato contato) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                "INSERT INTO contatos (Nome, telefone, email) VALUES (?, ?, ?)",
                PreparedStatement.RETURN_GENERATED_KEYS);

            st.setString(1, contato.getNome());
            st.setString(2, contato.getTelefone());
            st.setString(3, contato.getEmail());

            int rowsAffected = st.executeUpdate();

            if (rowsAffected > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    contato.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Erro inesperado! Nenhuma linha foi inserida.");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void update(Contato contato) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement(
                "UPDATE contatos SET Nome = ?, telefone = ?, email = ? WHERE Id = ?");

            st.setString(1, contato.getNome());
            st.setString(2, contato.getTelefone());
            st.setString(3, contato.getEmail());
            st.setInt(4, contato.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public void deleteById(Integer id) {
        PreparedStatement st = null;
        try {
            st = conn.prepareStatement("DELETE FROM contatos WHERE Id = ?");

            st.setInt(1, id);

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    @Override
    public Contato findById(Integer id) {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM contatos WHERE Id = ?");

            st.setInt(1, id);
            rs = st.executeQuery();

            if (rs.next()) {
                Contato contato = instantiateContato(rs);
                return contato;
            }
            return null;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    @Override
    public List<Contato> findAll() {
        PreparedStatement st = null;
        ResultSet rs = null;
        try {
            st = conn.prepareStatement("SELECT * FROM contatos ORDER BY Nome");

            rs = st.executeQuery();

            List<Contato> lista = new ArrayList<>();
            while (rs.next()) {
                Contato contato = instantiateContato(rs);
                lista.add(contato);
            }
            return lista;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private Contato instantiateContato(ResultSet rs) throws SQLException {
        Contato contato = new Contato();
        contato.setId(rs.getInt("Id"));
        contato.setNome(rs.getString("Nome"));
        contato.setTelefone(rs.getString("telefone"));
        contato.setEmail(rs.getString("email"));
        return contato;
    }
}
