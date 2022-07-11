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

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
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
        assertEquals("Enfermera", response.getBody().get(1).getFullName());
        assertEquals("TER", response.getBody().get(2).getId());
        assertEquals("Facultativo Especialista Adjunto", response.getBody().get(3).getFullName());
    }

    @Test
    void getCategoriesByIdApi() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);

        ResponseEntity<Category> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/categories/TCAE",
                HttpMethod.GET,
                entity,
                Category.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("TCAE", response.getBody().getId());
        assertEquals("Auxiliar de Enfermeria", response.getBody().getFullName());
    }

    @Test
    void postCategoriesApi() throws Exception {
        Category category = new Category("TEST", "test");
        HttpHeaders headersPost = new HttpHeaders();
        headersPost.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entityPost = new HttpEntity<Object>(category, headersPost);
        ResponseEntity<String> responsePost = restTemplate.exchange(
                "http://localhost:" + port + "/api/categories",
                HttpMethod.POST,
                entityPost,
                String.class);
        assertEquals(HttpStatus.CREATED, responsePost.getStatusCode());
        assertThat(responsePost.getHeaders().getLocation().toString()).contains("/api/categories/TEST");
    }

    @Test
    void invalidPostCategoriesApi() throws Exception {
        Category category = new Category("DUE", "");
        HttpHeaders headersPost = new HttpHeaders();
        headersPost.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entityPost = new HttpEntity<Object>(category, headersPost);
        ResponseEntity<String> responsePost = restTemplate.exchange(
                "http://localhost:" + port + "/api/categories",
                HttpMethod.POST,
                entityPost,
                String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responsePost.getStatusCode());
        assertThat(responsePost.getBody()).contains("Validation error");
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
        assertEquals("061 Almeria Centro coordinador", response.getBody().get(0).getName());
        assertEquals("Cadiz", response.getBody().get(1).getProvince());
    }

    @Test
    void getInstitutesByProvinceApi() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);

        ResponseEntity<List<Institute>> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/institutes?province=Almeria",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Institute>>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(1 < response.getBody().size());
        assertEquals("Almeria", response.getBody().get(0).getProvince());
    }

    @Test
    void getInstitutesByKindApi() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);

        ResponseEntity<List<Institute>> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/institutes?kind=Centro de salud",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Institute>>() {
                });
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(1 < response.getBody().size());
        assertEquals("Centro de salud", response.getBody().get(0).getKind());
    }

    @Test
    void getInstitutesByProvinceAndKindApi() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);

        ResponseEntity<List<Institute>> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/institutes?province=Almeria&kind=Centro de salud",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<List<Institute>>() {
                });
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(1 < response.getBody().size());
        assertEquals("Centro de salud", response.getBody().get(0).getKind());
        assertEquals("Almeria", response.getBody().get(0).getProvince());
    }

    @Test
    void getInstitutesByIdApi() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);

        ResponseEntity<Institute> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/institutes/1",
                HttpMethod.GET,
                entity,
                Institute.class);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("061 Almeria Centro coordinador", response.getBody().getName());
        assertEquals("Almeria", response.getBody().getProvince());
    }

    @Test
    void getInstitutesProvincesApi() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);

        ResponseEntity<Set<String>> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/institutes/provinces",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Set<String>>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(1 < response.getBody().size());
        assertTrue(response.getBody().contains("Almeria"));
        assertTrue(response.getBody().contains("Cadiz"));
    }

    @Test
    void getInstitutesProvincesByKindApi() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);

        ResponseEntity<Set<String>> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/institutes/provinces?kind=Centro de salud",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Set<String>>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(1 < response.getBody().size());
        assertTrue(response.getBody().contains("Almeria"));
        assertTrue(response.getBody().contains("Cadiz"));
    }

    @Test
    void getInstitutesKindsApi() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);

        ResponseEntity<Set<String>> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/institutes/kinds",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Set<String>>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(1 < response.getBody().size());
        assertTrue(response.getBody().contains("Centro de salud"));
        assertTrue(response.getBody().contains("Centro de consultas externas"));
    }

    @Test
    void getInstitutesKindsByProvinceApi() throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<Object>(headers);

        ResponseEntity<Set<String>> response = restTemplate.exchange(
                "http://localhost:" + port + "/api/institutes/kinds?province=Almeria",
                HttpMethod.GET,
                entity,
                new ParameterizedTypeReference<Set<String>>() {
                });

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertTrue(1 < response.getBody().size());
        assertTrue(response.getBody().contains("Centro de salud"));
        assertTrue(response.getBody().contains("Centro de consultas externas"));
    }

    @Test
    void postInstitutesApi() throws Exception {
        InstituteDTO institute = new InstituteDTO("Centro de salud Abla", "centro salud", "avenida estacion", "Almeria");
        HttpHeaders headersPost = new HttpHeaders();
        headersPost.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entityPost = new HttpEntity<Object>(institute, headersPost);
        ResponseEntity<String> responsePost = restTemplate.exchange(
                "http://localhost:" + port + "/api/institutes",
                HttpMethod.POST,
                entityPost,
                String.class);
        assertEquals(HttpStatus.CREATED, responsePost.getStatusCode());
        assertThat(responsePost.getHeaders().getLocation().toString()).contains("/api/institutes/");
    }

    @Test
    void invalidEmptyFieldPostInstitutesApi() throws Exception {
        InstituteDTO institute = new InstituteDTO("", "", "", "");
        HttpHeaders headersPost = new HttpHeaders();
        headersPost.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entityPost = new HttpEntity<Object>(institute, headersPost);
        ResponseEntity<String> responsePost = restTemplate.exchange(
                "http://localhost:" + port + "/api/institutes",
                HttpMethod.POST,
                entityPost,
                String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responsePost.getStatusCode());
        assertThat(responsePost.getBody()).contains("Validation error");
    }

    @Test
    void postAndGetAgreementApi() throws Exception {
        // Post agreement
        LocalDate tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS);
        AgreementRequest agreementRequest = new AgreementRequest(1L, "DUE", 7.0, AgreementDurationType.LONG, tomorrow, tomorrow.plus(15, ChronoUnit.DAYS), false);
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

        // Get all the agreement
        HttpHeaders headersGet = new HttpHeaders();
        headersGet.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entityGet = new HttpEntity<Object>(headersGet);
        ResponseEntity<List<Agreement>> responseGet = restTemplate.exchange(
                "http://localhost:" + port + "/api/agreements",
                HttpMethod.GET,
                entityGet,
                new ParameterizedTypeReference<List<Agreement>>() {
                });
        assertEquals(HttpStatus.OK, responseGet.getStatusCode());
        assertTrue(0 < responseGet.getBody().size());
        assertEquals(1L, responseGet.getBody().get(0).getInstitute().getId());
        assertEquals(agreementRequest.getCategoryId(), responseGet.getBody().get(0).getCategory().getId());
        assertEquals(agreementRequest.getPoints(), responseGet.getBody().get(0).getPoints());
        assertEquals(agreementRequest.getDurationType(), responseGet.getBody().get(0).getDurationType());
        assertEquals(agreementRequest.getInitialDate().toString(), responseGet.getBody().get(0).getInitialDate().toString());
        assertEquals(agreementRequest.getEndDate().toString(), responseGet.getBody().get(0).getEndDate().toString());
        assertEquals(LocalDate.now(ZoneId.of("Europe/Paris")).toString(), responseGet.getBody().get(0).getAssignedDate().toString());
        assertEquals("P15D", responseGet.getBody().get(0).getDuration().toString());
        assertEquals(agreementRequest.getAccepted(), responseGet.getBody().get(0).getAccepted());

        // Get agreement by Id
        HttpHeaders headersGetById = new HttpHeaders();
        headersGet.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entityGetById = new HttpEntity<Object>(headersGetById);
        ResponseEntity<Agreement> responseGetById = restTemplate.exchange(
                "http://localhost:" + port + responsePost.getHeaders().getLocation().toString(),
                HttpMethod.GET,
                entityGetById,
                Agreement.class);
        assertEquals(HttpStatus.OK, responseGetById.getStatusCode());
        assertEquals(1L, responseGet.getBody().get(0).getInstitute().getId());
        assertEquals(agreementRequest.getCategoryId(), responseGetById.getBody().getCategory().getId());
        assertEquals(agreementRequest.getPoints(), responseGetById.getBody().getPoints());
        assertEquals(agreementRequest.getDurationType(), responseGetById.getBody().getDurationType());
        assertEquals(agreementRequest.getInitialDate().toString(), responseGetById.getBody().getInitialDate().toString());
        assertEquals(agreementRequest.getEndDate().toString(), responseGetById.getBody().getEndDate().toString());
        assertEquals(LocalDate.now(ZoneId.of("Europe/Paris")).toString(), responseGetById.getBody().getAssignedDate().toString());
        assertEquals("P15D", responseGetById.getBody().getDuration().toString());
        assertEquals(agreementRequest.getAccepted(), responseGetById.getBody().getAccepted());
    }

    @Test
    void invalidInstitutePostAgreementsApi() throws Exception {
        LocalDate tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS);
        AgreementRequest agreementRequest = new AgreementRequest(100000L, "DUE", 7.0, AgreementDurationType.LONG, tomorrow, tomorrow.plus(30, ChronoUnit.DAYS), false);
        HttpHeaders headersPost = new HttpHeaders();
        headersPost.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entityPost = new HttpEntity<Object>(agreementRequest, headersPost);
        ResponseEntity<String> responsePost = restTemplate.exchange(
                "http://localhost:" + port + "/api/agreements",
                HttpMethod.POST,
                entityPost,
                String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responsePost.getStatusCode());
        assertThat(responsePost.getBody()).contains("Institute ID (100000) is not valid");
    }

    @Test
    void invalidCategoryPostAgreementsApi() throws Exception {
        LocalDate tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS);
        AgreementRequest agreementRequest = new AgreementRequest(1L, "wrongCategory", 7.0, AgreementDurationType.LONG, tomorrow, tomorrow.plus(30, ChronoUnit.DAYS), true);
        HttpHeaders headersPost = new HttpHeaders();
        headersPost.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entityPost = new HttpEntity<Object>(agreementRequest, headersPost);
        ResponseEntity<String> responsePost = restTemplate.exchange(
                "http://localhost:" + port + "/api/agreements",
                HttpMethod.POST,
                entityPost,
                String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responsePost.getStatusCode());
        assertThat(responsePost.getBody()).contains("Category ID (wrongCategory) is not valid");
    }

    @Test
    void invalidDatesPostAgreementsApi() throws Exception {
        LocalDate tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS);
        AgreementRequest agreementRequest = new AgreementRequest(1L, "DUE", 7.0, AgreementDurationType.LONG, tomorrow.plus(30, ChronoUnit.DAYS), tomorrow, true);
        HttpHeaders headersPost = new HttpHeaders();
        headersPost.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entityPost = new HttpEntity<Object>(agreementRequest, headersPost);
        ResponseEntity<String> responsePost = restTemplate.exchange(
                "http://localhost:" + port + "/api/agreements",
                HttpMethod.POST,
                entityPost,
                String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responsePost.getStatusCode());
        assertThat(responsePost.getBody()).contains("Initial date");
    }

    @Test
    void invalidEmptyFieldPostAgreementsApi() throws Exception {
        LocalDate tomorrow = LocalDate.now().plus(1, ChronoUnit.DAYS);
        AgreementRequest agreementRequest = new AgreementRequest(1L, "", 7.0, AgreementDurationType.SHORT, tomorrow, tomorrow.plus(30, ChronoUnit.DAYS), true);
        HttpHeaders headersPost = new HttpHeaders();
        headersPost.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entityPost = new HttpEntity<Object>(agreementRequest, headersPost);
        ResponseEntity<String> responsePost = restTemplate.exchange(
                "http://localhost:" + port + "/api/agreements",
                HttpMethod.POST,
                entityPost,
                String.class);
        assertEquals(HttpStatus.BAD_REQUEST, responsePost.getStatusCode());
        assertThat(responsePost.getBody()).contains("Validation error");
    }
}
