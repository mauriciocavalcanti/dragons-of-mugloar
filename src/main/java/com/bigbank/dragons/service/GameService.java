package com.bigbank.dragons.service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.bigbank.dragons.client.DragonsClient;
import com.bigbank.dragons.model.Ad;
import com.bigbank.dragons.model.Game;
import com.bigbank.dragons.model.Item;

@Service
public class GameService {


  private static final Logger LOGGER = LoggerFactory.getLogger(GameService.class);

  private Random random = new SecureRandom();


  @Autowired
  private DragonsClient dragonsClient;

  public Game startGame() {
    return dragonsClient.startGame();
  }

  public List<Ad> findAds(Game game) {
    return dragonsClient.getMessages(game.getGameId());
  }

  public Game solveAd(Game game, Ad ad) {
    Game gameAfterMission = dragonsClient.solveMessage(game.getGameId(), ad.getAdId());
    this.updateGameStats(game, gameAfterMission);
    LOGGER.debug("Game after mission: {}", game);
    return game;
  }

  public Game goShopping(Game game) {
    if (game.getAds().isEmpty()) {
      throw new IllegalArgumentException("Game must have a list of ads");
    }
    if (game.getLives() == 0) {
      return game;
    }
    List<Item> shop = dragonsClient.getShop(game.getGameId());
    double avgReward = this.getAvgReward(game);

    do {
      Item itemToBuy = getItemToBuy(game, shop, avgReward);
      if (itemToBuy != null) {
        Game gameAfterShopping = dragonsClient.buyItem(game.getGameId(), itemToBuy.getId());
        this.updateGameStats(game, gameAfterShopping);
      }
    } while (game.getGold() > 300);
    return game;
  }

  private Item getItemToBuy(Game game, List<Item> shop, double avgReward) {
    Item itemToBuy = null;
    if (game.getLives() < 4 && game.getGold() > 50) {
      itemToBuy = shop.get(0);
    } else if (game.getGold() > 300) {
      itemToBuy = this.getRandomItemByCost(shop, 300);
    } else if (avgReward < 150 && game.getGold() < 300 && game.getGold() > 100) {
      itemToBuy = this.getRandomItemByCost(shop, 100);
    }
    return itemToBuy;
  }

  private double getAvgReward(Game game) {
    return game.getAds().stream().mapToDouble(Ad::getReward).average()
        .orElseThrow(() -> new IllegalArgumentException("invalid ads"));
  }

  private Item getRandomItemByCost(List<Item> items, int cost) {
    List<Item> itemsTobuy =
        items.stream().filter(item -> item.getCost() == cost).collect(Collectors.toList());
    return itemsTobuy.get(this.random.nextInt(itemsTobuy.size()));
  }

  private void updateGameStats(Game game, Game gameAfterAction) {
    game.setSuccess(
        gameAfterAction.getSuccess() != null ? gameAfterAction.getSuccess() : game.getSuccess());
    game.setLives(
        gameAfterAction.getLives() != null ? gameAfterAction.getLives() : game.getLives());
    game.setGold(gameAfterAction.getGold() != null ? gameAfterAction.getGold() : game.getGold());
    game.setScore(
        gameAfterAction.getScore() != null ? gameAfterAction.getScore() : game.getScore());
    game.setTurn(gameAfterAction.getTurn() != null ? gameAfterAction.getTurn() : game.getTurn());
    game.setMessage(
        gameAfterAction.getMessage() != null ? gameAfterAction.getMessage() : game.getMessage());
    game.setShoppingSuccess(
        gameAfterAction.getShoppingSuccess() != null ? gameAfterAction.getShoppingSuccess()
            : game.getShoppingSuccess());
    game.setLevel(
        gameAfterAction.getLevel() != null ? gameAfterAction.getLevel() : game.getLevel());
  }
}
