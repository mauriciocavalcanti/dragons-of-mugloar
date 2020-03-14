package com.bigbank.dragons.model;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Game {

  private String gameId;
  private Integer lives;
  private Integer gold;
  private Integer level;
  private Integer score;
  private Integer highScore;
  private Integer turn;
  private String message;
  private Boolean success;
  private Boolean shoppingSuccess;
  private Reputation reputation;
  private List<Ad> ads;
  private List<Item> items;
  
  public String getGameId() {
    return gameId;
  }

  public void setGameId(String gameId) {
    this.gameId = gameId;
  }

  public Integer getLives() {
    return lives;
  }

  public void setLives(Integer lives) {
    this.lives = lives;
  }

  public Integer getGold() {
    return gold;
  }

  public void setGold(Integer gold) {
    this.gold = gold;
  }

  public Integer getLevel() {
    return level;
  }

  public void setLevel(Integer level) {
    this.level = level;
  }

  public Integer getScore() {
    return score;
  }

  public void setScore(Integer score) {
    this.score = score;
  }

  public Integer getHighScore() {
    return highScore;
  }

  public void setHighScore(Integer highScore) {
    this.highScore = highScore;
  }

  public Integer getTurn() {
    return turn;
  }

  public void setTurn(Integer turn) {
    this.turn = turn;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Reputation getReputation() {
    return reputation;
  }

  public void setReputation(Reputation reputation) {
    this.reputation = reputation;
  }

  public List<Item> getItems() {
    return items;
  }

  public void setItems(List<Item> items) {
    this.items = items;
  }

  public Boolean getSuccess() {
    return success;
  }

  public void setSuccess(Boolean success) {
    this.success = success;
  }

  public Boolean getShoppingSuccess() {
    return shoppingSuccess;
  }

  public void setShoppingSuccess(Boolean shoppingSuccess) {
    this.shoppingSuccess = shoppingSuccess;
  }

  public List<Ad> getAds() {
    return ads;
  }

  public void setAds(List<Ad> ads) {
    this.ads = ads;
  }

  @Override
  public String toString() {
    return "Game [gameId=" + gameId + ", lives=" + lives + ", gold=" + gold + ", level=" + level
        + ", score=" + score + ", highScore=" + highScore + ", turn=" + turn + ", message="
        + message + ", success=" + success + ", shoppingSuccess=" + shoppingSuccess
        + ", reputation=" + reputation + "]";
  }

}
