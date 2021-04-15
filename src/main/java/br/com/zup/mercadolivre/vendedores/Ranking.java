package br.com.zup.mercadolivre.vendedores;

import br.com.zup.mercadolivre.finalizacompra.EventoPedido;
import br.com.zup.mercadolivre.pedido.Pedido;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Ranking implements EventoPedido {

    @Override
    public void processa(Pedido pedido){
        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> request = Map.of(
                "idPedido", pedido.getId(), "idVendedor", pedido.getProdutos().get(0).getIdUsuario());


        HttpEntity<Map> entity = new HttpEntity<Map>(request, retornarHeader());
        restTemplate.postForEntity("http://localhost:8080/ranking", entity, String.class);
    }

    public HttpHeaders retornarHeader() {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJpc3MiOiJBUEkgTWVyY2FkbyBMaXZyZSIsInN1YiI6ImNhaW9AZW1haWwuY29tIiwiaWF0IjoxNjE4NDE2NzY4LCJleHAiOjE2MTg1MDMxNjh9.GDOREfHMfHrgc6PnZyKzAd8VZwFNpCeYZHcG2aMcQU4";
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("Authorization", "Bearer " + token);

        return headers;
    }
}
