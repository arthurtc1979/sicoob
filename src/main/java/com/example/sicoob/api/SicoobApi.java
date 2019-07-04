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
	@ApiOperation(value = "Recupera todas as pessoas da base")
	public List<Pessoa> getPessoas() {
		return pessoaDao.getPessoas();
	}
	
	@PostMapping("/pessoas")
	@ApiOperation(value = "Inclui uma entidade pessoa a base de dados")
	public void incluirPessoa(@RequestBody Pessoa pessoa) {
		pessoaDao.incluirPessoa(pessoa);
	}

	@DeleteMapping("/pessoas/{id}")
	@ApiOperation(value = "Exclui uma pessoa localizada pelo parâmetro id")
	public void excluirPessoa(@PathVariable Integer id) {
		pessoaDao.excluirPessoa(id);
	}
	
	@DeleteMapping("/enderecos/{id}")
	@ApiOperation(value = "Exclui um endereço localizado pelo parâmetro id")
	public void excluirEndereco(@PathVariable Integer id) {
		enderecoDao.excluirEndereco(id);
	}
	
	@PostMapping("/endereco")
	@ApiOperation(value = "Inclui uma entidade endereço a base de dados")
	public void incluirEndereco(@RequestBody Endereco endereco) {
		enderecoDao.incluirEndereco(endereco);
	}
	
	@GetMapping("/pessoas/{id}/enderecos")
	@ApiOperation(value = "Recupera todos os endereços de uma pessoa identificada pelo ID")
	public List<Endereco> getEnderecos(@PathVariable Integer id) {
		return enderecoDao.getEnderecos(id);
	}
	
	@GetMapping("/enderecos")
	@ApiOperation(value = "Recupera todos os endereços da base")
	public List<Endereco> getEnderecos() {
		return enderecoDao.getEnderecos();
	}
	
	@GetMapping("/pessoas/{id}")
	@ApiOperation(value = "Recupera uma pessoa da base localizada pelo ID")
	public Pessoa getPessoa(@PathVariable Integer id) {
		return pessoaDao.getPessoa(id);
	}
}
