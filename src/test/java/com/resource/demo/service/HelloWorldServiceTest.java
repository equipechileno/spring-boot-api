package com.resource.demo.service;

import com.resource.demo.service.data.Lance;
import com.resource.demo.service.data.Leilao;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;



@RunWith(SpringRunner.class)
public class HelloWorldServiceTest {

    private Lance lance;

    private Leilao leilao;

    @Test
    public void deveriaTestarMetodoNaClasseDeTeste(){
//        Assert.assertEquals(3, this.somaInts(1 , 2));
    }

    private int avalia(Leilao leilao) {

        return 1;
    }
}
