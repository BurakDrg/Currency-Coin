package com.sgveteris.currency;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.GenerationType;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Currency {
    @Id
    @SequenceGenerator(
            name = "currency_id_sequence",
            sequenceName = "currency_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "currency_id_sequence"
    )
    private Integer id;

    private String currencyType;
    private String coinType;
    private Double last_trade_price;

}
