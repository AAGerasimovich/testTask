package model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "price_list")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PriceList {

    @Column
    Integer count;
    @Column
    Integer price;
    @Column
    String state;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "products_name")
    private Product product;
    private LocalDate date;

    public enum State {
        ACCEPTANCE, SHIPMENT
    }
}
