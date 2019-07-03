package com.example.sicoob.api;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.sicoob.dao.PessoaDao;
import com.example.sicoob.entity.Pessoa;

@RunWith(SpringRunner.class)
public class SicoobApiTest {

    private SicoobApi sicoobApi;
    private PessoaDao pessoaDao;
    
    @Before
    public void setUp() {
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
            public Pessoa getPessoa(Integer id) {
                for (Pessoa p : lista) {
                    if (p.getId().equals(id))
                        return p;
                }
                return null;
            }
        };
        
        sicoobApi = new SicoobApi(pessoaDao, null);
        
        Pessoa p = new Pessoa();
        p.setId(1);
        p.setNome("Eu");
        pessoaDao.incluirPessoa(p);
        
        p = new Pessoa();
        p.setId(2);
        p.setNome("Tu");
        pessoaDao.incluirPessoa(p);
    }
    
    @Test
    public void test() {
        List<Pessoa> pessoas = sicoobApi.getPessoas();
        Assert.assertEquals(2, pessoas.size());
        Assert.assertEquals("Eu", pessoas.get(0).getNome());
        Assert.assertEquals("Tu", pessoas.get(1).getNome());
    }
}