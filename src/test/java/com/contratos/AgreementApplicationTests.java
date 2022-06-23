package com.contratos;

import com.contratos.controller.AgreementController;
import com.contratos.model.*;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AgreementApplicationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    private AgreementController controller;

    @Test
    void contextLoads() throws Exception {
        assertThat(controller).isNotNull();
    }

    @Test
    void getProvincesApi() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);

        ResponseEntity<Set<String>> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/provinces",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Set<String>>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(Province.getList(), response.getBody());
    }

    @Test
    void getCategoriesApi() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);

        ResponseEntity<List<Category>> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/categories",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Category>>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(3 < response.getBody().size());
        assertEquals("TCAE", response.getBody().get(0).getId());
        assertEquals("Enfermería", response.getBody().get(1).getFullName());
        assertEquals("TER", response.getBody().get(2).getId());
        assertEquals("Facultativo Especialista Adjunto", response.getBody().get(3).getFullName());
    }

    @Test
    void getInstitutesApi() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);

        ResponseEntity<List<Institute>> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/institutes",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Institute>>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(1 < response.getBody().size());
        assertEquals("Centro de salud Abla", response.getBody().get(0).getName());
        assertEquals(Province.ALMERIA, response.getBody().get(1).getProvince());
    }

    @Test
    void postAndGetAgreementApi() throws Exception {
        Calendar initialDate = Calendar.getInstance();
        initialDate.set(2022, 7, 6, 0, 0, 0);
        Calendar endDate = Calendar.getInstance();
        endDate.set(2022, 8, 10, 0, 0, 0);
        AgreementRequest agreementRequest = new AgreementRequest(1L, "DUE", 7.0, initialDate.getTime(), endDate.getTime());
        HttpHeaders headersPost = new HttpHeaders();
        headersPost.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entityPost = new HttpEntity<Object>(agreementRequest, headersPost);
        ResponseEntity<String> responsePost = restTemplate.exchange(
                "http://localhost:" + port + "/api/agreements",
                HttpMethod.POST,
                entityPost,
                String.class);
        assertEquals(HttpStatus.CREATED, responsePost.getStatusCode());
        assertThat(responsePost.getHeaders().getLocation().toString()).contains("/api/agreements/");

        HttpHeaders headersGet = new HttpHeaders();
        headersGet.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entityGet = new HttpEntity<Object>(headersGet);
        ResponseEntity<List<AgreementResponse>> responseGet = restTemplate.exchange(
                "http://localhost:" + port + "/api/agreements",
                HttpMethod.GET,
                entityGet,
                new ParameterizedTypeReference<List<AgreementResponse>>() {
                });
        assertEquals(HttpStatus.OK, responseGet.getStatusCode());
        assertTrue(0 < responseGet.getBody().size());
        assertEquals("Centro de salud Abla", responseGet.getBody().get(0).getInstitute().getName());
        assertEquals(Province.ALMERIA, responseGet.getBody().get(0).getInstitute().getProvince());
        assertEquals(agreementRequest.getCategoryId(), responseGet.getBody().get(0).getCategory().getId());
        assertEquals(agreementRequest.getPoints(), responseGet.getBody().get(0).getPoints());
        assertEquals(agreementRequest.getInitialDate().toString(), responseGet.getBody().get(0).getInitialDate().toString());
        assertEquals(agreementRequest.getEndDate().toString(), responseGet.getBody().get(0).getEndDate().toString());
        Calendar current = Calendar.getInstance();
        current.set(Calendar.HOUR_OF_DAY, 0);
        current.set(Calendar.MINUTE, 0);
        current.set(Calendar.SECOND, 0);
        assertEquals(current.getTime().toString(), responseGet.getBody().get(0).getAssignedDate().toString());
    }
}
