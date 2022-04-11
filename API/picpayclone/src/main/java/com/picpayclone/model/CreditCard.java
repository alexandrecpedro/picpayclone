package com.picpayclone.model;

import com.picpayclone.enums.CardBrand;
import lombok.*;

import javax.persistence.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "CREDIT_CARD")
public class CreditCard extends BaseEntity {

    @Column(name = "CC_NUMBER", nullable = false)
    private String number;

    @Enumerated(EnumType.STRING)
    @Column(name = "CC_BRAND", nullable = false)
    private CardBrand brand;

    @Column(name = "CC_TOKEN")
    private String tokenNumber;

    @ManyToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name = "CC_USER_ID", nullable = false)
    private User user;

}
