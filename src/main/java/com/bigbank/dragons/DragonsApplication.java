package com.bigbank.dragons;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.bigbank.dragons.controller.AdController;
import com.bigbank.dragons.controller.GameController;
import com.bigbank.dragons.model.Ad;
import com.bigbank.dragons.model.Game;

@SpringBootApplication
public class DragonsApplication implements CommandLineRunner {

  private static final Logger LOGGER = LoggerFactory.getLogger(DragonsApplication.class);

  public static void main(String[] args) {
    SpringApplication.run(DragonsApplication.class, args);
  }

  @Autowired
  private GameController gameController;
  
  @Autowired
  private AdController adController;
  
  @Override
  public void run(String... args) throws Exception {
    Game game = gameController.startGame();
    while (game.getLives() > 0) {
      List<Ad> ads = gameController.findAds(game);
      game.setAds(ads);
      Ad adToSolve = adController.findBestAd(ads);
      game = gameController.solveAd(game, adToSolve);
      game = gameController.goShopping(game);
      LOGGER.info("End turn, game stats: {}", game);
    }
    LOGGER.info("Final stats: {}", game);
  }

}
