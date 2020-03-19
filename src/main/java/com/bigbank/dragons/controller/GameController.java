package com.bigbank.dragons.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.bigbank.dragons.model.Ad;
import com.bigbank.dragons.model.Game;
import com.bigbank.dragons.service.GameService;

@Component
public class GameController {

  @Autowired
  private GameService gameService;

  public Game startGame() {
    return gameService.startGame();
  }

  public List<Ad> findAds(Game game) {
    return gameService.findAds(game);
  }

  public Game solveAd(Game game, Ad ad) {
    return gameService.solveAd(game, ad);
  }

  public Game goShopping(Game game) {
    return gameService.goShopping(game);
  }

}
