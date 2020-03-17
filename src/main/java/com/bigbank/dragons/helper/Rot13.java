package com.bigbank.dragons.helper;

public class Rot13 {

  private Rot13() {}

  public static String rot13(String value) {

    char[] values = value.toCharArray();
    for (int i = 0; i < values.length; i++) {
      char letter = values[i];

      if (letter >= 'a' && letter <= 'z') {

        if (letter > 'm') {
          letter -= 13;
        } else {
          letter += 13;
        }
      } else if (letter >= 'A' && letter <= 'Z') {

        if (letter > 'M') {
          letter -= 13;
        } else {
          letter += 13;
        }
      }
      values[i] = letter;
    }
    return new String(values);
  }

}
