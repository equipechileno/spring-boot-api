package com.resource.demo.service.data;

import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Leilao {

    private String descricao;
    private List<Lance> lances;
    private Double maiorLance = Double.NEGATIVE_INFINITY;
    private Double menorLance = Double.NEGATIVE_INFINITY;

    public void Leilao(String descricao){
        this.descricao = descricao;
        this.lances = new ArrayList<>();

    }

    public void propoe(Lance lance){
        this.getLances().add(lance);
    }
}
