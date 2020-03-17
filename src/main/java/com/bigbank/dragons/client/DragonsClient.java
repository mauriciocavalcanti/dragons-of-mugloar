package com.bigbank.dragons.client;

import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.bigbank.dragons.model.Ad;
import com.bigbank.dragons.model.Game;
import com.bigbank.dragons.model.Item;
import com.bigbank.dragons.model.Reputation;

@Component
public class DragonsClient {


  private static final Logger LOGGER = LoggerFactory.getLogger(DragonsClient.class);


  @Autowired
  private WebClient webClient;

  public Game startGame() {
    Game game = webClient.post().uri("/game/start").retrieve().bodyToMono(Game.class).block();
    LOGGER.debug("Game started: {}", game);
    return game;
  }

  public Reputation investigateReputation(String gameId) {
    Reputation reputation = webClient.post().uri("/{gameId}/investigate/reputation", gameId)
        .retrieve().bodyToMono(Reputation.class).block();
    LOGGER.debug("Reputation: {}", reputation);
    return reputation;
  }

  public List<Ad> getMessages(String gameId) {
    List<Ad> ads = webClient.get().uri("/{gameId}/messages", gameId).retrieve().bodyToFlux(Ad.class)
        .collectList().block();
    for (Ad ad : ads) {
      LOGGER.debug("Ad: {}", ad);
    }
    return ads;
  }

  public Game solveMessage(String gameId, String adId) {
    Game game = webClient.post().uri("/{gameId}/solve/{adId}", gameId, adId).retrieve()
        .bodyToMono(Game.class).block();
    LOGGER.debug("Solve message result: {}", game);
    return game;
  }

  public List<Item> getShop(String gameId) {
    List<Item> items = webClient.get().uri("/{gameId}/shop", gameId).retrieve()
        .bodyToFlux(Item.class).collectList().block();
    for (Item item : items) {
      LOGGER.debug("Item: {}", item);
    }
    return items;
  }

  public Game buyItem(String gameId, String itemId) {
    Game game = webClient.post().uri("/{gameId}/shop/buy/{itemId}", gameId, itemId).retrieve()
        .bodyToMono(Game.class).block();
    LOGGER.debug("Shopping result: {}", game);
    return game;
  }
}
