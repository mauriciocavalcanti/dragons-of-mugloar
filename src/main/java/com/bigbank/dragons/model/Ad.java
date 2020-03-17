package com.bigbank.dragons.model;

import com.bigbank.dragons.config.AdDeserializer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonDeserialize(using = AdDeserializer.class)
public class Ad {

  private String adId;
  private String message;
  private Integer reward;
  private Integer expiresIn;
  private Probability probability;
  private Integer encrypted;
  private Game game;

  public Ad() {}
  
  public Ad(String adId, String message, Integer reward, Integer expiresIn, Probability probability,
      Integer encrypted, Game game) {
    super();
    this.adId = adId;
    this.message = message;
    this.reward = reward;
    this.expiresIn = expiresIn;
    this.probability = probability;
    this.encrypted = encrypted;
    this.game = game;
  }

  public String getAdId() {
    return adId;
  }

  public void setAdId(String adId) {
    this.adId = adId;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Integer getReward() {
    return reward;
  }

  public void setReward(Integer reward) {
    this.reward = reward;
  }

  public Integer getExpiresIn() {
    return expiresIn;
  }

  public void setExpiresIn(Integer expiresIn) {
    this.expiresIn = expiresIn;
  }

  public Probability getProbability() {
    return probability;
  }

  public void setProbability(Probability probability) {
    this.probability = probability;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  public Integer getEncrypted() {
    return encrypted;
  }

  public void setEncrypted(Integer encrypted) {
    this.encrypted = encrypted;
  }

  @Override
  public String toString() {
    return "Ad [adId=" + adId + ", message=" + message + ", reward=" + reward + ", experisIn="
        + expiresIn + ", probability=" + probability.getDescription() + ", encrypted=" + encrypted + "]";
  }

}
