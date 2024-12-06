package dao;

import java.util.List;

import entidades.Contato;
import implementacaoDao.DaoFactory;

public interface ContatoDao {
	void insert(Contato obj);
	void update(Contato obj);
	void deleteById(Integer id);
	Contato findById(Integer id);
	List <Contato> findAll();
	ContatoDao contatoDao = DaoFactory.createContatoDao();
}