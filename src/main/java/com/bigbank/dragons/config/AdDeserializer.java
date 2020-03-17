package com.bigbank.dragons.config;

import java.io.IOException;
import org.apache.tomcat.util.codec.binary.Base64;
import com.bigbank.dragons.model.Ad;
import com.bigbank.dragons.model.Game;
import com.bigbank.dragons.model.Probability;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.ObjectCodec;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonNode;

public class AdDeserializer extends JsonDeserializer<Ad> {

  @Override
  public Ad deserialize(JsonParser parser, DeserializationContext context) throws IOException {
    ObjectCodec oc = parser.getCodec();
    JsonNode node = oc.readTree(parser);

    final String adId;
    final String message;
    final String probability;
    final Integer encrypted = node.get("encrypted") != null ? node.get("encrypted").asInt(0) : 0 ;
    if (!encrypted.equals(Integer.valueOf(0)) && encrypted.equals(Integer.valueOf(1))) {
      adId = new String(Base64.decodeBase64(node.get("adId").asText()));
      message = new String(Base64.decodeBase64(node.get("message").asText()));
      probability = new String(Base64.decodeBase64(node.get("probability").asText()));
    } else {
      adId = node.get("adId").asText();
      message = node.get("message").asText();
      probability = node.get("probability").asText();
    }
    final Integer reward = node.get("reward").asInt();
    final Integer expiresIn = node.get("expiresIn").asInt();

    return new Ad(adId, message, reward, expiresIn, Probability.getByDescription(probability), encrypted, new Game());
  }

}
