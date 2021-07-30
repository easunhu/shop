package com.miw.shop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JacksonJsonParser;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.*;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("test")
public class ITests {

    @Autowired
    MockMvc mockMvc;

    RestTemplate restTemplate = new RestTemplate();

    String read_token = null;

    String write_token = null;

    @BeforeEach
    public void before() throws Exception {
        read_token = obtainAccessToken("shop:read");
        write_token = obtainAccessToken("shop:write");
    }

    @Test
    void getItemTest() throws Exception {
        this.mockMvc.perform(get("/api/items/1")
                        .header("Authorization", "Bearer " + read_token))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Alpha")));
    }

    @Test
    void getItemsTest() throws Exception {
        this.mockMvc.perform(get("/api/items/")
                        .header("Authorization", "Bearer " + read_token))
                .andExpect(status().isOk());
    }

    @Test
    void getVisitByIdTest() throws Exception {
        this.mockMvc.perform(get("/api/visits/1")
                        .header("Authorization", "Bearer " + read_token))
                .andExpect(status().isOk());
    }

    @Test
    void getVisitByItemIdTest() throws Exception {
        this.mockMvc.perform(get("/api/visits/itemId/1")
                        .header("Authorization", "Bearer " + read_token))
                .andExpect(status().isOk());
    }

    @Test
    void getVisitsTest() throws Exception {
        this.mockMvc.perform(get("/api/visits/")
                        .header("Authorization", "Bearer " + read_token))
                .andExpect(status().isOk());
    }

    @Test
    void getPurchasesTest() throws Exception {
        this.mockMvc.perform(get("/api/purchases/")
                        .header("Authorization", "Bearer " + read_token))
                .andExpect(status().isOk());
    }

    @Test
    void postPurchasesTest() throws Exception {
        this.mockMvc.perform(post("/api/purchases/createPurchase")
                        .content("{\"purchaseItems\":[{\"itemId\":1,\"price\":100,\"quantity\":1}],\"total\":100,\"userId\":1}")
                        .header("Authorization", "Bearer " + write_token)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void postPurchasesWithMoreItemsTest() throws Exception {
        this.mockMvc.perform(post("/api/purchases/createPurchase")
                        .content("{\"purchaseItems\":[{\"itemId\":1,\"price\":100,\"quantity\":1},{\"itemId\":2,\"price\":200,\"quantity\":2},{\"itemId\":3,\"price\":300,\"quantity\":3}],\"total\":1400,\"userId\":1}")
                        .header("Authorization", "Bearer " + write_token)
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    private String obtainAccessToken(String scope) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBasicAuth("shop-client", "secret");

        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        params.add("grant_type", "client_credentials");
        params.add("client_id", "shop-client");
        params.add("username", "shop-client");
        params.add("password", "secret");
        params.add("scope", scope);

        ResponseEntity<String> response = restTemplate
                .exchange("http://localhost:9000/oauth2/token", HttpMethod.POST, new HttpEntity<>(params, headers), String.class);

        JacksonJsonParser jsonParser = new JacksonJsonParser();
        return jsonParser.parseMap(response.getBody()).get("access_token").toString();
    }
}