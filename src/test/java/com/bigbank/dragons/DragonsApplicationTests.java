package com.bigbank.dragons;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.bigbank.dragons.client.DragonsClient;
import com.bigbank.dragons.model.Ad;
import com.bigbank.dragons.model.Game;
import com.bigbank.dragons.model.Item;
import com.bigbank.dragons.model.Reputation;

@SpringBootTest
class DragonsApplicationTests {

  @Autowired
  private DragonsClient dragonsClient;

  private Game game;

  private boolean initialized = false;

  @BeforeEach
  private void setup() {
    if (initialized) {
      return;
    }
    this.game = dragonsClient.startGame();
    this.initialized = true;
  }

  @Test
  public void startGameSuccess() {
    Game game = dragonsClient.startGame();
    assertNotNull(game.getGameId());
    assertFalse(game.getGameId().isEmpty());
  }

  @Test
  public void investigationSuccess() {
    Reputation reputation = dragonsClient.investigateReputation(this.game.getGameId());
    assertNotNull(reputation);
    assertTrue(
        reputation.getPeople().equals(new Float(0)) && reputation.getState().equals(new Float(0))
            && reputation.getUnderworld().equals(new Float(0)));
  }

  @Test
  public void getAllMessagesSuccess() {
    List<Ad> ads = dragonsClient.getMessages(this.game.getGameId());
    assertFalse(ads.isEmpty());
    assertNotNull(ads.get(0).getAdId());
  }

  @Test
  public void solveMessageSuccess() {
    List<Ad> ads = dragonsClient.getMessages(this.game.getGameId());
    Game game = dragonsClient.solveMessage(this.game.getGameId(), ads.get(0).getAdId());
    assertNotNull(game);
    assertTrue(game.getSuccess().equals(new Boolean(true))
        || game.getSuccess().equals(new Boolean(false)));
  }
  
  @Test
  public void getShopSuccess() {
    List<Item> items = dragonsClient.getShop(this.game.getGameId());
    assertFalse(items.isEmpty());
    assertNotNull(items.get(0).getId());
  }
  
  @Test
  public void buyItemSuccess() {
    List<Item> items = dragonsClient.getShop(this.game.getGameId());
    Game game = dragonsClient.buyItem(this.game.getGameId(), items.get(0).getId());
    assertNotNull(game);
    assertTrue(game.getShoppingSuccess().equals(new Boolean(false)));
  }
}
