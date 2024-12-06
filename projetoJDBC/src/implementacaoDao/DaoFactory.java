
package implementacaoDao;

import dao.ContatoDao;
import dao.ContatoProfissionalDao;
import db.DB;

public class DaoFactory {

    public static ContatoDao createContatoDao() {
        return new ContatoDaoJDBC(DB.getConnection());
    }

    public static ContatoProfissionalDao createContatoProfissionalDao() {
        return new ContatoProfissionalDaoJDBC(DB.getConnection());
    }
}
