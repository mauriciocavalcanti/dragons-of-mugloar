package com.bigbank.dragons.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Reputation {

  private Float people;
  private Float state;
  private Float underworld;
  private Game game;

  public Float getPeople() {
    return people;
  }

  public void setPeople(Float people) {
    this.people = people;
  }

  public Float getState() {
    return state;
  }

  public void setState(Float state) {
    this.state = state;
  }

  public Float getUnderworld() {
    return underworld;
  }

  public void setUnderworld(Float underworld) {
    this.underworld = underworld;
  }

  public Game getGame() {
    return game;
  }

  public void setGame(Game game) {
    this.game = game;
  }

  @Override
  public String toString() {
    return "Reputation [people=" + people + ", state=" + state + ", underworld=" + underworld + "]";
  }

}
