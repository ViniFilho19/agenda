package implementacaoDao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import dao.ContatoProfissionalDao;
import db.DB;
import db.DbException;
import entidades.ContatoProfissional;

public class ContatoProfissionalDaoJDBC implements ContatoProfissionalDao {

    private Connection conn;

    public ContatoProfissionalDaoJDBC(Connection conn) {
        this.conn = conn;
    }

     public void insert(ContatoProfissional contatoProfissional) {
        String sql = "INSERT INTO contatos_profissionais (nome, telefone, email, nome_empresa) VALUES (?, ?, ?, ?)";
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, contatoProfissional.getNome());
            st.setString(2, contatoProfissional.getTelefone());
            st.setString(3, contatoProfissional.getEmail());
            st.setString(4, contatoProfissional.getNomeEmpresa());

            int linhasAfetadas = st.executeUpdate();
            if (linhasAfetadas > 0) {
                ResultSet rs = st.getGeneratedKeys();
                if (rs.next()) {
                    int id = rs.getInt(1);
                    contatoProfissional.setId(id);
                }
                DB.closeResultSet(rs);
            } else {
                throw new DbException("Erro inesperado! Nenhuma inserção ocorreu.");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    
    public void update(ContatoProfissional contatoProfissional) {
        String sql = "UPDATE contatos_profissionais SET nome = ?, telefone = ?, email = ?, nome_empresa = ? WHERE id = ?";
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(sql);
            st.setString(1, contatoProfissional.getNome());
            st.setString(2, contatoProfissional.getTelefone());
            st.setString(3, contatoProfissional.getEmail());
            st.setString(4, contatoProfissional.getNomeEmpresa());
            st.setInt(5, contatoProfissional.getId());

            st.executeUpdate();
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    
    public void deleteById(Integer id) {
        String sql = "DELETE FROM contatos_profissionais WHERE id = ?";
        PreparedStatement st = null;

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, id);

            int linhasAfetadas = st.executeUpdate();
            if (linhasAfetadas == 0) {
                throw new DbException("Nenhuma exclusão ocorreu! Id inexistente.");
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
        }
    }

    
    public ContatoProfissional findById(Integer id) {
        String sql = "SELECT * FROM contatos_profissionais WHERE id = ?";
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(sql);
            st.setInt(1, id);

            rs = st.executeQuery();
            if (rs.next()) {
                ContatoProfissional contatoProfissional = instanciaContatoProfissional(rs);
                return contatoProfissional;
            }
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
        return null;
    }

    
    public List<ContatoProfissional> findAll() {
        String sql = "SELECT * FROM contatos_profissionais ORDER BY nome";
        PreparedStatement st = null;
        ResultSet rs = null;

        try {
            st = conn.prepareStatement(sql);
            rs = st.executeQuery();

            List<ContatoProfissional> lista = new ArrayList<>();

            while (rs.next()) {
                ContatoProfissional contatoProfissional = instanciaContatoProfissional(rs);
                lista.add(contatoProfissional);
            }
            return lista;
        } catch (SQLException e) {
            throw new DbException(e.getMessage());
        } finally {
            DB.closeStatement(st);
            DB.closeResultSet(rs);
        }
    }

    private ContatoProfissional instanciaContatoProfissional(ResultSet rs) throws SQLException {
        ContatoProfissional contatoProfissional = new ContatoProfissional();
        contatoProfissional.setId(rs.getInt("id"));
        contatoProfissional.setNome(rs.getString("nome"));
        contatoProfissional.setTelefone(rs.getString("telefone"));
        contatoProfissional.setEmail(rs.getString("email"));
        contatoProfissional.setNomeEmpresa(rs.getString("nome_empresa"));
        return contatoProfissional;
    }
}