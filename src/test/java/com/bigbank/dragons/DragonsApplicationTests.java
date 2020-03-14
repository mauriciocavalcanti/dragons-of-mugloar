package com.bigbank.dragons;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.bigbank.dragons.client.DragonsClient;
import com.bigbank.dragons.model.Game;
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
}
