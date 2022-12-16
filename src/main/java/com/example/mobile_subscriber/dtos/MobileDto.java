package com.example.mobile_subscriber.dtos;

import com.example.mobile_subscriber.models.ServiceTypeEnum;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.ZonedDateTime;


@Data
@Setter
@Getter
public class MobileDto {

    private Integer id;
    private String msisdn;
    private Integer customerIdOwner;
    private Integer customerIdUser;
    private ServiceTypeEnum serviceType;

    private ZonedDateTime serviceStartDate;

}

