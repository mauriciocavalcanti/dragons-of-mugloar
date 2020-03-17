package com.bigbank.dragons.model;

import java.util.HashMap;
import java.util.Map;
import com.fasterxml.jackson.annotation.JsonValue;

public enum Probability {
  PIECE_OF_CAKE("Piece of cake", 0), SURE_THING("Sure thing", 1), WALK_IN_THE_PARK(
      "Walk in the park", 2), QUITE_LIKELY("Quite likely", 3), HMMM("Hmmm....", 4), GAMBLE("Gamble",
          5), RATHER_DETRIMENTAL("Rather detrimental", 6), PLAYING_WITH_FIRE("Playing with fire",
              7), RISKY("Risky",
                  8), SUICIDE_MISSION("Suicide mission", 9), IMPOSSIBLE("Impossible", 10);

  private String description;
  private int weight;
  
  private static Map<String, Probability> map = new HashMap<>();

  static {
      for (Probability probability : Probability.values()) {
          map.put(probability.description, probability);
      }
  }

  Probability(String description, int weight) {
    this.description = description;
    this.weight = weight;
  }

  @JsonValue
  public String getDescription() {
    return description;
  }

  public int getWeigth() {
    return weight;
  }
  
  public static Probability getByDescription(String description) {
    return map.get(description);
}

}
