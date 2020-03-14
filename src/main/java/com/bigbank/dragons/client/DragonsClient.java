package com.bigbank.dragons.client;

import java.util.ArrayList;
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
    return null;
  }

  public Reputation investigateReputation(String gameId) {
    return null;
  }

  public List<Ad> getMessages(String gameId) {
    return new ArrayList<>();
  }

  public Game solveMessage(String gameId, String adId) {
    return null;
  }

  public List<Item> getShop(String gameId) {
    return new ArrayList<>();
  }

  public Game buyItem(String gameId, String itemId) {
    return null;
  }
}
