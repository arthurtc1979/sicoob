package com.example.sicoob.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.sicoob.entity.Pessoa;

@Component
public class PessoaDao {
	@Autowired
	private EntityManager em;
	
	public List<Pessoa> getPessoas(){
		return em.createQuery("SELECT p FROM Pessoa p", Pessoa.class).getResultList();
	}

	public Pessoa getPessoa(Integer id) {
		return em.find(Pessoa.class, id);
	}

	@Transactional
	public void incluirPessoa(Pessoa pessoa) {
		em.persist(pessoa);
	}

	@Transactional
	public void excluirPessoa(Integer id) {
		Pessoa pessoa = getPessoa(id);
		em.remove(pessoa);
	}

}
