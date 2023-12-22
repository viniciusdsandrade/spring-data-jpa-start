package com.resftul.springdatajpaecommerce.entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "Address")
@Table(name = "tb_addresses",
        schema = "db_ecommerce")
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String cep;
    private String logradouro;
    private String complemento;
    private String bairro;
    private String localidade;
    private String uf;
    private String ibge;
    private String gia;
    private String ddd;
    private String siafi;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id", referencedColumnName = "id")
    private Order order;

    @Override
    public String toString() {
        return "{\n" +
                "  \"id\": " + this.id + ",\n" +
                "  \"cep\": \"" + this.cep + "\",\n" +
                "  \"logradouro\": \"" + this.logradouro + "\",\n" +
                "  \"complemento\": \"" + this.complemento + "\",\n" +
                "  \"bairro\": \"" + this.bairro + "\",\n" +
                "  \"localidade\": \"" + this.localidade + "\",\n" +
                "  \"uf\": \"" + this.uf + "\",\n" +
                "  \"ibge\": \"" + this.ibge + "\",\n" +
                "  \"gia\": \"" + this.gia + "\",\n" +
                "  \"ddd\": \"" + this.ddd + "\",\n" +
                "  \"siafi\": \"" + this.siafi + "\"\n" +
                "}";
    }
}