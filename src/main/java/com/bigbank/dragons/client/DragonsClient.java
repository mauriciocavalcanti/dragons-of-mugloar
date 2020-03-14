package com.bigbank.dragons.client;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import com.bigbank.dragons.model.Ad;
import com.bigbank.dragons.model.Game;
import com.bigbank.dragons.model.Item;
import com.bigbank.dragons.model.Reputation;

@Component
public class DragonsClient {

  @Autowired
  private WebClient webClient;

  public Game startGame() {
    return webClient.post().uri("/game/start").retrieve().bodyToMono(Game.class).block();
  }

  public Reputation investigateReputation(String gameId) {
    return webClient.post().uri("/{gameId}/investigate/reputation", gameId).retrieve()
        .bodyToMono(Reputation.class).block();
  }

  public List<Ad> getMessages(String gameId) {
    return webClient.get().uri("/{gameId}/messages", gameId).retrieve().bodyToFlux(Ad.class)
        .collectList().block();
  }

  public Game solveMessage(String gameId, String adId) {
    return webClient.post().uri("/{gameId}/solve/{adId}", gameId, adId).retrieve()
        .bodyToMono(Game.class).block();
  }

  public List<Item> getShop(String gameId) {
    return webClient.get().uri("/{gameId}/shop", gameId).retrieve().bodyToFlux(Item.class)
        .collectList().block();
  }

  public Game buyItem(String gameId, String itemId) {
    return webClient.post().uri("/{gameId}/shop/buy/{itemId}", gameId, itemId).retrieve()
        .bodyToMono(Game.class).block();
  }
}
