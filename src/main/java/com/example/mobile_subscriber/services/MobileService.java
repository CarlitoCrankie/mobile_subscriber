package com.example.mobile_subscriber.services;

import com.example.mobile_subscriber.dtos.MobileDto;
import com.example.mobile_subscriber.models.Mobile;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface MobileService {


     /*Page<Mobile> getAllMobiles(String keyword, int pageNo, int pageSize, String sortField, String sortDirection);*/
     List<Mobile> getAllMobiles();
     Mobile getMobile(Integer id);
     Mobile addMobile(Mobile mobile);
//     List<Mobile> deleteMobile(Integer id);
     void updateMobile(MobileDto mobileDto);
     long countMobiles();
     Page<Mobile> searchSort(int pageNo, int pageSize, String sortField, String sortDirection,String keyword);
     Page<Mobile> getPagination(int pageNo, int pageSize, String sortField, String sortDirection);
     long countPrepaid();
     long countPostPaid();


}
