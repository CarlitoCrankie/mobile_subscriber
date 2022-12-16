package com.example.mobile_subscriber.services;

import com.example.mobile_subscriber.Repositories.MobileRepository;
import com.example.mobile_subscriber.dtos.MobileDto;
import com.example.mobile_subscriber.models.Mobile;
import com.example.mobile_subscriber.models.ServiceTypeEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.ZonedDateTime;
import java.util.List;

@Service("mobileService")
public class MobileServiceImpl implements MobileService{
    @Autowired
    private MobileRepository mobileRepository;





    public List<Mobile> getAllMobiles(){
        return mobileRepository.findAll();
    }
    public Mobile getMobile(Integer id){

        return mobileRepository.findById(id).get();
    }

    public Mobile addMobile(Mobile mobile){

        return mobileRepository.save(mobile);
    }

    @Transactional
    public List<Mobile> deleteMobile(Integer id){
        boolean exists = mobileRepository.existsById(id);

        if(!exists){
            throw new IllegalStateException("Subscriber with id" + id + "does not exist.");
        }
         return mobileRepository.deleteMobileById(id);
    }

    public void updateMobile(MobileDto mobileDto){
        Mobile mobile1 = mobileRepository.findById(mobileDto.getId()).orElseThrow(()-> new IllegalArgumentException("Not found"));
        mobile1.setCustomerIdOwner(mobileDto.getCustomerIdOwner());
        mobile1.setCustomerIdUser(mobileDto.getCustomerIdUser());
        mobile1.setMsisdn(mobileDto.getMsisdn());
        mobile1.setServiceStartDate(ZonedDateTime.now());
        mobile1.setServiceType(mobileDto.getServiceType());

        mobileRepository.save(mobile1);
    }

    @Override
    public Page<Mobile> getPagination( int pageNo, int pageSize, String sortField, String sortDirection){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
                Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
        return this.mobileRepository.findAll(pageable);
    }

    @Override
    public Page<Mobile> searchSort( int pageNo, int pageSize, String sortField, String sortDirection, String keyword){
        Sort sort = sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortField).ascending():
        Sort.by(sortField).descending();

        Pageable pageable = PageRequest.of(pageNo - 1, pageSize, sort);
            return this.mobileRepository.search(keyword.trim().toLowerCase(), pageable);
    }

    public long countMobiles(){
        return mobileRepository.count();
    }

    public long countPrepaid(){
        return  mobileRepository.countByServiceType(ServiceTypeEnum.MOBILE_PREPAID);
    }

    public long countPostPaid(){
        return  mobileRepository.countByServiceTypeIs(ServiceTypeEnum.MOBILE_POSTPAID);
    }

//  public List<Mobile> listAll(String keyword){
//        if(keyword != null){
//            return mobileRepository.search(keyword);
//        }
//        return mobileRepository.findAll();
//   }
}
