package com.example.mobile_subscriber.models;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.time.ZonedDateTime;


@Entity
@Table(name="mobile")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Mobile {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id; //primary key
    @Column(name = "msisdn")
    private String msisdn;
    @Column(name = "customer_id_owner")
    private Integer customerIdOwner;
    @Column(name = "customer_id_user")
    private Integer customerIdUser;
    @Enumerated(EnumType.STRING)
    @Column(name = "service_type", nullable = false)
    private ServiceTypeEnum serviceType;
    @Column(name = "service_start_date")
    @CreationTimestamp
    private ZonedDateTime serviceStartDate;
}

