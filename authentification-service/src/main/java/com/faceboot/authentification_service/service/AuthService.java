package com.faceboot.authentification_service.service;

import com.faceboot.authentification_service.client.UserClient;
import com.faceboot.authentification_service.dto.UserCheckCredentialsDTO;
import com.faceboot.authentification_service.dto.UserRequestDTO;
import com.faceboot.authentification_service.model.User;
import lombok.AllArgsConstructor;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@AllArgsConstructor
public class AuthService implements AuthServiceInterface{
    private final UserClient userClient;
    private final RestTemplate restTemplate = new RestTemplate();
    private static final String KEYCLOAK_URL = "http://keycloak:8080/admin/realms/SpringOrbit/users";


    @Override
    public boolean login(UserCheckCredentialsDTO userCheckCredentialsDTO) {

        return userClient.checkCredentials(userCheckCredentialsDTO);
    }

    @Override
    public User register(UserRequestDTO userRequestDTO) {
        return null;
    }

    public ResponseEntity<String> createUser(UserRequestDTO userDto) {
        String adminToken = getAdminToken();  // Get admin token from Keycloak

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.setBearerAuth(adminToken);

        Map<String, Object> user = new HashMap<>();
        user.put("username", userDto.name());
        user.put("email", userDto.email());
        user.put("enabled", true);
        user.put("credentials", List.of(Map.of(
                "type", "password",
                "value", userDto.password(),
                "temporary", false
        )));

        HttpEntity<Map<String, Object>> request = new HttpEntity<>(user, headers);
        return restTemplate.postForEntity(KEYCLOAK_URL, request, String.class);
    }

    public String getAdminToken() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> form = new LinkedMultiValueMap<>();
        form.add("client_id", "admin-cli");
        form.add("username", "admin");
        form.add("password", "admin");
        form.add("grant_type", "password");

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(form, headers);
        ResponseEntity<Map> response = restTemplate.postForEntity(
                "http://keycloak:8080/realms/master/protocol/openid-connect/token", request, Map.class);

        return response.getBody().get("access_token").toString();
    }
}
