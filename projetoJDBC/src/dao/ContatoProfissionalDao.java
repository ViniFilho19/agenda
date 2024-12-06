package dao;

import java.util.List;
import entidades.ContatoProfissional;

public interface ContatoProfissionalDao {

    void insert(ContatoProfissional contatoProfissional);
    void update(ContatoProfissional contatoProfissional);
    void deleteById(Integer id);
    ContatoProfissional findById(Integer id);
    List<ContatoProfissional> findAll();
}

