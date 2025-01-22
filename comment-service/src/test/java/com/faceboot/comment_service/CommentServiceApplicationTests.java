package com.faceboot.comment_service;

import io.restassured.RestAssured;
import io.restassured.matcher.ResponseAwareMatcher;
import io.restassured.matcher.RestAssuredMatchers;
import io.restassured.response.Response;
import io.restassured.specification.Argument;
import net.bytebuddy.asm.MemberSubstitution;
import org.hamcrest.Matcher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.boot.testcontainers.service.connection.ServiceConnection;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.shaded.org.hamcrest.Matchers;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.notNullValue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CommentServiceApplicationTests {

	@ServiceConnection
	static MongoDBContainer mongoDBContainer = new MongoDBContainer("mongo:7.0.5");

	@LocalServerPort
	private Integer port;

	@BeforeEach
	void setUp() {
		RestAssured.baseURI = "http://localhost";
		RestAssured.port = port;
	}

	static {
		mongoDBContainer.start();
	}

//	@Test
	void shouldCreateComment() {
		String requestBody = """
				{
				   "userId": 1,
				   "postId": "3e2",
				   "content": "test comment",
				   "createTime": "2025-01-07T22:38:12.727"
				}
				""";
		List<MemberSubstitution.Argument> arguments =  new ArrayList<>();

		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/comment/add")
				.then()
				.statusCode(200)
				.assertThat()
				.body("id",notNullValue())
				.body("user.id",equalTo(1) )
				.body("post.id",equalTo("3e2"))
				.body("content", equalTo("test comment"))
				.body("createTime",equalTo("2025-01-07T22:38:12.727"));
	}

//	@Test
	void shouldDeleteCommentsByPost() {

		String requestBody = """
				{
				   "userId": 1,
				   "postId": "3e2",
				   "content": "test comment",
				   "createTime": "2025-01-07T22:38:12.727"
				}
				""";
		RestAssured.given()
				.contentType("application/json")
				.body(requestBody)
				.when()
				.post("/comment/add");



		RestAssured.given()
				.contentType("application/json")
				.when()
				.delete("/comment/delete/byPost/3e2")
				.then()
				.statusCode(200)
				.assertThat()
				.body(equalTo("comments deleted"));
	}

}
