package com.example.sicoob.api;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.sicoob.dao.EnderecoDao;
import com.example.sicoob.dao.PessoaDao;
import com.example.sicoob.entity.Endereco;
import com.example.sicoob.entity.Pessoa;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/dados")
public class SicoobApi {
	
	private PessoaDao pessoaDao;
	private EnderecoDao enderecoDao;
	
	public SicoobApi(PessoaDao pessoaDao, EnderecoDao enderecoDao) {
		this.pessoaDao = pessoaDao;
		this.enderecoDao = enderecoDao;
	}

	@GetMapping
	public String getNome() {
		return "Teste CRUD SICOOB";
	}
	
	@GetMapping("/pessoas")
	@ApiOperation(value = "Recupera todas as pessoas da base", 
		notes = "Multiplos valores da entidade Pessoa", 
		responseContainer = "List")
	public List<Pessoa> getPessoas() {
		return pessoaDao.getPessoas();
	}
	
	@PostMapping("/pessoas")
	public void incluirPessoa(@RequestBody Pessoa pessoa) {
		pessoaDao.incluirPessoa(pessoa);
	}

	@DeleteMapping("/pessoas/{id}")
	public void excluirPessoa(@PathVariable Integer id) {
		pessoaDao.excluirPessoa(id);
	}
	
	@DeleteMapping("/enderecos/{id}")
	public void excluirEndereco(@PathVariable Integer id) {
		enderecoDao.excluirEndereco(id);
	}
	
	@PostMapping("/endereco")
	public void incluirEndereco(@RequestBody Endereco endereco) {
		enderecoDao.incluirEndereco(endereco);
	}
	
	@GetMapping("/pessoas/{id}/enderecos")
	public List<Endereco> getEnderecos(@PathVariable Integer id) {
		return enderecoDao.getEnderecos(id);
	}
	
	@GetMapping("/enderecos")
	public List<Endereco> getEnderecos() {
		return enderecoDao.getEnderecos();
	}
	
	@GetMapping("/pessoas/{id}")
	public Pessoa getPessoa(@PathVariable Integer id) {
		return pessoaDao.getPessoa(id);
	}
}
