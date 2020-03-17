package com.bigbank.dragons.config;

import java.io.IOException;
import org.apache.tomcat.util.codec.binary.Base64;
import com.bigbank.dragons.helper.Rot13;
import com.bigbank.dragons.model.Ad;
import com.bigbank.dragons.model.Game;
import com.bigbank.dragons.model.Probability;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class AdDeserializer extends JsonDeserializer<Ad> {
  
  private static final String EXPIRES_IN = "expiresIn";
  private static final String REWARD = "reward";
  private static final String ENCRYPTED = "encrypted";
  private static final String AD_ID = "adId";
  private static final String PROBABILITY = "probability";
  private static final String MESSAGE = "message";

  @Override
  public Ad deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    ObjectCodec oc = parser.getCodec();
    JsonNode node = oc.readTree(parser);

    final String adId;
    final String message;
    final String probability;
    final Integer encrypted = node.get(ENCRYPTED) != null ? node.get(ENCRYPTED).asInt(0) : 0 ;
    
    if (encrypted.equals(Integer.valueOf(1))) {
      adId = new String(Base64.decodeBase64(node.get(AD_ID).asText()));
      message = new String(Base64.decodeBase64(node.get(MESSAGE).asText()));
      probability = new String(Base64.decodeBase64(node.get(PROBABILITY).asText()));
    } else if(encrypted.equals(Integer.valueOf(2))) {
      adId = Rot13.rot13(node.get(AD_ID).asText());
      message = Rot13.rot13(node.get(MESSAGE).asText());
      probability = Rot13.rot13(node.get(PROBABILITY).asText());
    } else {
      adId = node.get(AD_ID).asText();
      message = node.get(MESSAGE).asText();
      probability = node.get(PROBABILITY).asText();
    }
    final Integer reward = node.get(REWARD).asInt();
    final Integer expiresIn = node.get(EXPIRES_IN).asInt();

    return new Ad(adId, message, reward, expiresIn, Probability.getByDescription(probability), encrypted, new Game());
  }

}
