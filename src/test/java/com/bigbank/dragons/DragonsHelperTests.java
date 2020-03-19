package com.bigbank.dragons;

import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import com.bigbank.dragons.helper.Rot13;
import com.bigbank.dragons.model.Ad;
import com.bigbank.dragons.model.Probability;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@ActiveProfiles("test")
class DragonsHelperTests {
  
  @Test
  public void rot13Decryption() {
    assertTrue(Rot13.rot13("Guvf vf n fnzcyr").equals("This is a sample"));
  }
  
  @Test
  public void shouldDeserializeEncryptedBase64() throws JsonMappingException, JsonProcessingException {
    Ad ad = new ObjectMapper().readValue("{\n" + 
        "        \"adId\": \"RWRQMUsyb2Y=\",\n" + 
        "        \"message\": \"SGVscCBMdWRvbGYgS2l0Y2hlbnMgdG8gZml4IHRoZWlyIHBhbg==\",\n" + 
        "        \"reward\": 8,\n" + 
        "        \"expiresIn\": 6,\n" + 
        "        \"encrypted\": 1,\n" + 
        "        \"probability\": \"U3VyZSB0aGluZw==\"\n" + 
        "    }", Ad.class);
    assertTrue(ad.getAdId().equals("EdP1K2of") && ad.getMessage().equals("Help Ludolf Kitchens to fix their pan") && ad.getProbability().equals(Probability.SURE_THING));
  }
  
  @Test
  public void shouldDeserializeEncryptedRot13() throws JsonMappingException, JsonProcessingException {
    Ad ad = new ObjectMapper().readValue("{\n" + 
        "        \"adId\": \"RqC1X2bs\",\n" + 
        "        \"message\": \"Uryc Yhqbys Xvgpuraf gb svk gurve cna\",\n" + 
        "        \"reward\": 8,\n" + 
        "        \"expiresIn\": 6,\n" + 
        "        \"encrypted\": 2,\n" + 
        "        \"probability\": \"Fher guvat\"\n" + 
        "    }", Ad.class);
    assertTrue(ad.getAdId().equals("EdP1K2of") && ad.getMessage().equals("Help Ludolf Kitchens to fix their pan") && ad.getProbability().equals(Probability.SURE_THING));
  }

}
