package com.bigbank.dragons.service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import com.bigbank.dragons.model.Ad;
import com.bigbank.dragons.model.Probability;

@Service
public class AdService {

  private static final Logger LOGGER = LoggerFactory.getLogger(AdService.class);

  public Ad findBestAd(List<Ad> ads) {
    //stealing is wrong!
    List<Ad> adsWithoutStealing = this.removeAdsAboutStealing(ads);
    Probability bestProbability = this.getBestProbability(adsWithoutStealing);
    LOGGER.debug("Best ads probability: {}", bestProbability.getDescription());
    List<Ad> bestProbabilityAds = this.getAdsByProbability(ads, bestProbability);
    Ad ad = this.getHighestRewardAd(bestProbabilityAds);
    LOGGER.debug("Best ad: {}", ad);
    return ad;
  }

  private List<Ad> removeAdsAboutStealing(List<Ad> ads) {
    return ads.stream().filter(ad-> !ad.getMessage().contains("Steal")).collect(Collectors.toList());
  }

  private Ad getHighestRewardAd(List<Ad> bestProbabilityAds) {
    return bestProbabilityAds.stream().max(Comparator.comparing(Ad::getReward))
        .orElseThrow(() -> new IllegalArgumentException("invalid ads"));
  }

  private List<Ad> getAdsByProbability(List<Ad> ads, Probability bestProbability) {
    return ads.stream().filter(ad -> ad.getProbability().equals(bestProbability))
        .collect(Collectors.toList());
  }

  private Probability getBestProbability(List<Ad> ads) {
    return ads.stream().map(Ad::getProbability).min(Comparator.comparing(Probability::getWeigth))
        .orElseThrow(() -> new IllegalArgumentException("invalid ads"));
  }

}
