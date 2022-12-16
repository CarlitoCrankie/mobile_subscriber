package com.example.mobile_subscriber.controllers;

import com.example.mobile_subscriber.dtos.MobileDto;
import com.example.mobile_subscriber.models.Mobile;
import com.example.mobile_subscriber.services.MobileService;
import com.example.mobile_subscriber.services.MobileServiceImpl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Controller
@Slf4j
@RequiredArgsConstructor
public class MobileController{

    @Autowired
    private MobileServiceImpl mobileService;

    @GetMapping(value = "/home")
    public String welcome(Model model){

        long count = mobileService.countMobiles();
        long count1 = mobileService.countPrepaid();
        long count2 = mobileService.countPostPaid();

        model.addAttribute("countPrepaid", count1);
        model.addAttribute("countPostpaid", count2);
        model.addAttribute("countMobiles", count);
        return "Homepage";
    }

    @GetMapping(value = "/list")
    public String viewHomePage(Model model){
        return findPaginated(1, "id", "asc","",5, model);
    }

    @GetMapping(value = "/mobiles/{id}")
    @ResponseBody
    public Mobile getMobile( @PathVariable(name = "id") Integer id){
        return mobileService.getMobile(id);
    }


    @PostMapping(value = "/addMobile")
    public String addMobile(Mobile mobile){
        mobileService.addMobile(mobile);
        return "redirect:list";
    }

    @GetMapping(path = "/deleteMobile/{id}")
    public String deleteMobile(@PathVariable(name="id") Integer id){
        mobileService.deleteMobile(id);
        return "redirect:/list";
    }

    @PutMapping(value = "/updateMobile/{id}")
    @ResponseBody
    public void updateMobile(@RequestBody MobileDto mobileDto, @PathVariable Integer id){
          mobileDto.setId(id);
          mobileService.updateMobile(mobileDto);
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo,
                                @RequestParam(value = "sortField", defaultValue = "id") String sortField,
                                @RequestParam(value = "sortDir", defaultValue = "asc") String sortDir,
                                @RequestParam(value = "keyword", defaultValue = "") String keyword,
                                @RequestParam(value = "pageSize", defaultValue = "5", required = false) int pageSize,
                                Model model){


        Page<Mobile> page = null;

        if (keyword.trim() == "" || keyword.isEmpty()) {

            page = mobileService.getPagination(pageNo, pageSize, sortField, sortDir);
        }else{
            page = mobileService.searchSort(pageNo, pageSize, sortField,sortDir, keyword);
        }
        List<Mobile> mobiles = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("pageSize",pageSize);

        model.addAttribute("sortField", sortField);
        model.addAttribute("sortDir", sortDir);
        model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");

        model.addAttribute("mobileList", mobiles);
        model.addAttribute("keyword", keyword);


        return "mobile";
    }


}

