package com.example.sicoob.dao;

import java.util.List;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.sicoob.entity.Endereco;

@Component
public class EnderecoDao {
	@Autowired
	private EntityManager em;
	
	public List<Endereco> getEnderecos(Integer idPessoa){
		return em.createQuery("SELECT e FROM Endereco e where e.pessoa.id = :idPessoa", Endereco.class)
				.setParameter("idPessoa", idPessoa).getResultList();
	}

	public Endereco getEndereco(Integer id) {
		return em.find(Endereco.class, id);
	}
	
	@Transactional
	public void incluirEndereco(Endereco endereco) {
		em.persist(endereco);
	}

	@Transactional
	public void excluirEndereco(Integer id) {
		Endereco endereco = getEndereco(id);
		em.remove(endereco);
	}

	public List<Endereco> getEnderecos() {
		return em.createQuery("SELECT e FROM Endereco e", Endereco.class).getResultList();
	}
}
