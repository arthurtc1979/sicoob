package com.example.sicoob.api;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.sicoob.dao.EnderecoDao;
import com.example.sicoob.dao.PessoaDao;
import com.example.sicoob.entity.Endereco;
import com.example.sicoob.entity.Pessoa;

@RunWith(SpringRunner.class)
public class SicoobApiTest {

    private SicoobApi sicoobApi;
    private PessoaDao pessoaDao;
    private EnderecoDao enderecoDao;
    private final String DESCRICAO_1 = "Lago sul";
    private final String DESCRICAO_2 = "Asa norte";
    private final String NOME_1 = "Joao";
    private final String NOME_2 = "Maria";
    
    
    @Before
    public void setUp() {
		// Sobescrevendo métodos para trabalhar com a lista configurada por toda a
		// classe
    	enderecoDao = new EnderecoDao() {
            
            private List<Endereco> lista = new ArrayList<>();
            
            @Override
            public void incluirEndereco(Endereco endereco) {
                lista.add(endereco);
            }
            
            @Override
            public List<Endereco> getEnderecos() {
                return lista;
            }
            
            @Override
            public void excluirEndereco(Integer id) {
            	lista.remove(new Endereco(id));
            }
            
            @Override
            public Endereco getEndereco(Integer id) {
                for (Endereco p : lista) {
                    if (p.getId().equals(id))
                        return p;
                }
                return null;
            }
        };
        
    	pessoaDao = new PessoaDao() {
            
            private List<Pessoa> lista = new ArrayList<>();
            
            @Override
            public void incluirPessoa(Pessoa pessoa) {
                lista.add(pessoa);
            }
            
            @Override
            public List<Pessoa> getPessoas() {
                return lista;
            }
            
            @Override
            public void excluirPessoa(Integer id) {
            	lista.remove(new Pessoa(id));
            }
            
            @Override
            public Pessoa getPessoa(Integer id) {
                for (Pessoa p : lista) {
                    if (p.getId().equals(id))
                        return p;
                }
                return null;
            }
        };
        
        sicoobApi = new SicoobApi(pessoaDao, enderecoDao);
        
        Pessoa p = new Pessoa();
        p.setId(1);
        p.setNome(NOME_1);
        pessoaDao.incluirPessoa(p);
        
        p = new Pessoa();
        p.setId(2);
        p.setNome(NOME_2);
        pessoaDao.incluirPessoa(p);
        
        Endereco endereco = new Endereco();
        endereco.setId(1);
        endereco.setDescricao(DESCRICAO_1);
        endereco.setPessoa(p);
        enderecoDao.incluirEndereco(endereco);
        
        endereco = new Endereco();
        endereco.setId(2);
        endereco.setDescricao(DESCRICAO_2);
        endereco.setPessoa(p);
        enderecoDao.incluirEndereco(endereco);
    }
    
    @Test
    public void test() {
        List<Pessoa> pessoas = sicoobApi.getPessoas();
        Assert.assertEquals(2, pessoas.size());
        Assert.assertEquals(NOME_1, pessoas.get(0).getNome());
        Assert.assertEquals(NOME_2, pessoas.get(1).getNome());
        
        List<Endereco> enderecos = sicoobApi.getEnderecos();
        Assert.assertEquals(2, enderecos.size());
        Assert.assertEquals(DESCRICAO_1, enderecos.get(0).getDescricao());
        Assert.assertEquals(DESCRICAO_2, enderecos.get(1).getDescricao());
    }
    
    @Test
    public void incluirPessoaTest() {
        Pessoa p = new Pessoa();
        p.setId(3);
        p.setNome("Ele");
        pessoaDao.incluirPessoa(p);
        Assert.assertEquals(sicoobApi.getPessoas().size(), 3); 
    }
    
    @Test
    public void excluirPessoaTest() {
    	pessoaDao.excluirPessoa(3);
    	Assert.assertEquals(sicoobApi.getPessoas().size(), 2);
    }
    
    @Test
    public void incluirEnderecoTest() {
    	Endereco endereco = new Endereco(3);
    	endereco.setDescricao("Brazlandia");
    	endereco.setPessoa(pessoaDao.getPessoa(2));
    	enderecoDao.incluirEndereco(endereco);
    	Assert.assertEquals(sicoobApi.getEnderecos().size(), 3);
    }
    
    @Test
    public void excluirEnderecoTest() {
    	enderecoDao.excluirEndereco(3);
    	Assert.assertEquals(sicoobApi.getEnderecos().size(), 2);
    }
}