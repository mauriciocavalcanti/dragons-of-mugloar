package com.bigbank.dragons.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Ad {

  private String adId;
  private String message;
  private Integer reward;
  private Integer expiresIn;
  private String probability;
  private Integer encrypted;
  private Game game;

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

  public String getProbability() {
    return probability;
  }

  public void setProbability(String probability) {
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
        + expiresIn + ", probability=" + probability + ", encrypted=" + encrypted + "]";
  }

}
