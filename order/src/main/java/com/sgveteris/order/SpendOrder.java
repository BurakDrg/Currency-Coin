package com.sgveteris.order;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class SpendOrder implements Serializable {

    @Id
    @SequenceGenerator(
            name = "spend_order_id_sequence",
            sequenceName = "spend_order_id_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "spend_order_id_sequence"
    )
    private Integer id;

    private String currencyType;

    private String currencyAmount;

    private String coinType;

    private String coinAmount;

    @CreatedDate
    @Column(name = "creation_date", insertable=false, updatable = false)
    private Date date;
}
