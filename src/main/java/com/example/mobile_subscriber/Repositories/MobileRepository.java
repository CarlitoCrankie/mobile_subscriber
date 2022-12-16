package com.example.mobile_subscriber.Repositories;

import com.example.mobile_subscriber.models.Mobile;
import com.example.mobile_subscriber.models.ServiceTypeEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MobileRepository extends JpaRepository<Mobile, Integer> {

    List<Mobile> findAll();

   List<Mobile> deleteMobileById(Integer id);

    long countByServiceType(ServiceTypeEnum serviceType);

    long countByServiceTypeIs(ServiceTypeEnum serviceType);

    @Query("SELECT p FROM Mobile p WHERE LOWER(CONCAT(p.serviceType,' ', p.customerIdOwner,' ', p.customerIdUser,' ', p.msisdn,' ', p.serviceStartDate)) LIKE %?1%")
    Page<Mobile> search(String keyword, Pageable pageable);


}