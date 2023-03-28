package ru.kubsu.geocoder.controller;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.kubsu.geocoder.dto.RestApiError;
import ru.kubsu.geocoder.model.Mark;
import ru.kubsu.geocoder.repository.TestRepository;
import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class TestControllerTest {

  @LocalServerPort
  Integer port;

  @Autowired
  TestRepository testRepository;

  private TestRepository repository;

  private final TestRestTemplate testRestTemplate = new TestRestTemplate();

  @BeforeAll
  static void beforeAll() {
    System.out.println("beforeAll");
  }

  @BeforeEach
  public void setUp() {
    ru.kubsu.geocoder.model.Test test = new ru.kubsu.geocoder.model.Test();
    test.setName("megurx");
    test.setId(1);
    test.setDone(true);
    test.setMark(Mark.A);
    testRepository.save(test);
  }

  @AfterEach
  void tearDown() {
    System.out.println("tearDown");

  }

  @Test
  void integrationTest() {
    ResponseEntity<ru.kubsu.geocoder.model.Test> response = testRestTemplate.
      getForEntity(
        "http://localhost:"+ port +"/tests/1?name=test", ru.kubsu.geocoder.model.Test.class);

    final ru.kubsu.geocoder.model.Test body = response.getBody();

    assertEquals(1,body.getId());
    assertEquals("test",body.getName());
    assertEquals(false, body.getDone());
    assertEquals(null, body.getMark());
  }

  @Test
  void integrationTestWhenNameIsNull() {
    ResponseEntity<String> response = testRestTemplate.
      getForEntity(
        "http://localhost:"+ port +"/tests/1",
        String.class);
    assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
    final String body = response.getBody();
    System.out.println(body);
  }

  @Test
  void integrationTestWhenIdIsString() {
    ResponseEntity<RestApiError> response = testRestTemplate.
      exchange(
        "http://localhost:"+ port +"/tests/abc?name=test",
        HttpMethod.GET,null, RestApiError.class);

    assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());

    final RestApiError body = response.getBody();
    assertEquals(400,body.status());
    assertEquals("Bad Request",body.error());
    assertEquals("/tests/abc",body.path());
    System.out.println(body);
  }

  @Test
  void testSaveDatabase() {
    ResponseEntity<Void> response = testRestTemplate.
      getForEntity(
        "http://localhost:"+ port +"/tests/save?name=testSave", Void.class);

    assertEquals(HttpStatus.OK,response.getStatusCode());
  }

  @Test
  void testSaveDatabaseFailed() {
    ResponseEntity<Void> response = testRestTemplate.
      getForEntity(
        "http://localhost:"+ port +"/tests/save", Void.class);

    assertEquals(HttpStatus.BAD_REQUEST,response.getStatusCode());
  }

  @Test
  void testLoadDatabase() {
    ResponseEntity<ru.kubsu.geocoder.model.Test> response = testRestTemplate.
      getForEntity(
        "http://localhost:"+ port +"/tests/load/megurx", ru.kubsu.geocoder.model.Test.class);

    final ru.kubsu.geocoder.model.Test body = response.getBody();

    assertEquals(HttpStatus.OK,response.getStatusCode());
    assertEquals("megurx",body.getName());
    assertEquals(true, body.getDone());
    assertEquals(Mark.A, body.getMark());
    System.out.println(body);
  }

  @Test
  void testLoadDatabaseFailed() {
    ResponseEntity<ru.kubsu.geocoder.model.Test> response = testRestTemplate.
      getForEntity(
        "http://localhost:"+ port +"/tests/load/9999999999", ru.kubsu.geocoder.model.Test.class);

    final ru.kubsu.geocoder.model.Test body = response.getBody();
    assertEquals(null,body);
    System.out.println(body);
    System.out.println(response.getStatusCode());
  }

  @AfterAll
  static void afterAll() {
    System.out.println("afterAll");
  }
}
