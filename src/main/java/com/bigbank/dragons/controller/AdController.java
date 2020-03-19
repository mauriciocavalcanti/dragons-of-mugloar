package com.bigbank.dragons.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bigbank.dragons.model.Ad;
import com.bigbank.dragons.service.AdService;

@Component
public class AdController {

  @Autowired
  private AdService adService;
    
  public Ad findBestAd(List<Ad> ads) {
    return adService.findBestAd(ads);
  }

}
