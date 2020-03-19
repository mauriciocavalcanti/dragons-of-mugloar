package com.bigbank.dragons;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.bigbank.dragons.controller.AdController;
import com.bigbank.dragons.controller.GameController;
import com.bigbank.dragons.model.Ad;
import com.bigbank.dragons.model.Game;
import com.bigbank.dragons.model.Probability;

@SpringBootTest
@ActiveProfiles("test")
class DragonsControllerTests {

  @Autowired
  private GameController gameController;

  @Autowired
  private AdController adController;

  private Game game;

  private List<Ad> ads = new ArrayList<>();

  private boolean initialized = false;

  @BeforeEach
  private void setup() {
    if (initialized) {
      return;
    }
    this.game = gameController.startGame();

    ads.add(new Ad("QVLW6CNz",
        "Create an advertisement campaign for Vaska Winston to promote their dog based business",
        new Integer(29), new Integer(7), Probability.RATHER_DETRIMENTAL, null, null));
    ads.add(new Ad("5WhQCnxw",
        "Create an advertisement campaign for Fereshteh Thrussell to promote their dog based business",
        new Integer(25), new Integer(7), Probability.SURE_THING, null, null));
    ads.add(new Ad("94jGzvrL",
        "Create an advertisement campaign for Alister Lauderman to promote their chariot based business",
        new Integer(19), new Integer(7), Probability.QUITE_LIKELY, null, null));
    ads.add(new Ad("O9o2cDO8",
        "Help Ayodele Willoughby to transport a magic chariot to village in Greymere",
        new Integer(18), new Integer(7), Probability.WALK_IN_THE_PARK, null, null));
    ads.add(
        new Ad("N6108xu2", "Help Yaromil Edwards to sell an unordinary wagon on the local market",
            new Integer(30), new Integer(7), Probability.GAMBLE, null, null));
    ads.add(new Ad("Bdjx529C",
        "Escort Sakiko Williams to village in Doveham where they can meet with their long lost potatoes",
        new Integer(56), new Integer(7), Probability.QUITE_LIKELY, null, null));
    ads.add(new Ad("vd6gzszw",
        "Steal chicken delivery to Juho Masters and share some of the profits with the people.",
        new Integer(56), new Integer(7), Probability.SURE_THING, null, null));
    ads.add(
        new Ad("Nw35omSM", "Help Carter Sempers to transport a magic pan to savannah in Metalham",
            new Integer(9), new Integer(7), Probability.SURE_THING, null, null));
    ads.add(new Ad("cXJlYzfj",
        "Escort Karita Trollope to savannah in Westdrone where they can meet with their long lost chicken",
        new Integer(77), new Integer(7), Probability.SUICIDE_MISSION, null, null));
    ads.add(new Ad("rAumEYNr",
        "Steal water delivery to Kattalin Derricks and share some of the profits with the people.",
        new Integer(57), new Integer(7), Probability.SURE_THING, null, null));
    this.initialized = true;
  }

  @Test
  public void startGameSuccess() {
    Game game = gameController.startGame();
    assertNotNull(game.getGameId());
    assertFalse(game.getGameId().isEmpty());
  }

  @Test
  public void getAllMessagesSuccess() {
    List<Ad> ads = gameController.findAds(this.game);
    assertFalse(ads.isEmpty());
    assertNotNull(ads.get(0).getAdId());
  }

  @Test
  public void solveMessageSuccess() {
    List<Ad> ads = gameController.findAds(this.game);
    Game game = gameController.solveAd(this.game, ads.get(0));
    assertNotNull(game);
    assertTrue(game.getSuccess().equals(new Boolean(true))
        || game.getSuccess().equals(new Boolean(false)));
  }

  @Test
  public void goShoppingShouldThrowIllegalArgumentWhenEmptyAds() {
    assertThrows(IllegalArgumentException.class, () -> {
      gameController.goShopping(this.game);
    });
  }

  @Test
  public void goShoppingShouldNotBuyAnything() {
    this.game.setAds(this.ads);
    Game game = gameController.goShopping(this.game);
    this.game.setAds(null);
    assertTrue(game.equals(this.game));
  }

  @Test
  public void shouldGetBestAdToSolve() {
    Ad bestAd = adController.findBestAd(this.ads);
    assertTrue(bestAd.getAdId().equals("5WhQCnxw"));
  }

}
