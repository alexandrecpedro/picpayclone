package com.picpayclone.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@Entity
@Table(name = "TRANSACTIONS")
public class Transaction extends BaseEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Column(name = "TR_CODE", nullable = false)
    private String code;

    @ManyToOne(cascade = { CascadeType.MERGE })
    @JoinColumn(name = "TR_USER_ORIGIN", nullable = false)
    private User origin;

    @ManyToOne(cascade = { CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinColumn(name = "TR_USER_DESTINATION", nullable = false)
    private User destination;

    @Column(name = "TR_DATE_TIME", nullable = false)
    private LocalDateTime dateTime;

    @Column(name = "TR_VALUE", nullable = false)
    private Double value;

}
