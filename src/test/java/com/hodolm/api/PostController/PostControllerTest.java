package com.hodolm.api.PostController;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.http.MediaType.APPLICATION_JSON;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.hamcrest.Matcher;
import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.hodolm.api.domain.Post;
import com.hodolm.api.repository.PostRepository;
import com.hodolm.api.request.PostCreate;

// 이 어노테이션은 Spring MVC에 집중한 테스트를 위해 사용됩니다. 이를 사용함으로써 Web 계층만을 로드하여 빠르게 컨트롤러 테스트를 할 수 있습니다.
//@WebMvcTest
// 스프링 부트(Sping Boot) 애플리케이션에 대한 통합 테스트를 수행할 때 사용되는 어노테이션입니다.
// 이 어노테이션은 스프링 부트의 전체 테스트 환경을 구성하여, 실제로 애플리케이션을 실행하는 것과 유사한 환경에서 테스트를 수행할 수 있도록 해줍니다.
@SpringBootTest
// MockMvc를 bean에 주입
@AutoConfigureMockMvc
class PostControllerTest {

	@Autowired
	private PostRepository postRepository;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	@Autowired
	private MockMvc mockMvc;
	// MockMvc는 Spring MVC의 동작을 모의로 테스트할 수 있게 해주는 클래스입니다. 
	// 컨트롤러에 대한 HTTP 요청과 응답을 시뮬레이션하기 위해 사용됩니다.
	// Content-Type은 application/json 타입으로 보냅니다.
	
	// 각각 메소드들이 실행되기 이전에 실행되는 메소드를 만들어줘야 각각의 메소드의 개별성이 보장된다.
	@BeforeEach
	void clean() {
		// 각각의 메소드가 모두 postRepository를 사용하기 때문에 메소드가 실행되기전에 postRepository로 실행된 값을 지워준다.
		postRepository.deleteAll();
	}
	
	@Test												// JUnit에서 단위 테스트를 표시하는 어노테이션입니다.								
	@DisplayName("/posts 요청시 Hello World를 출력한다.")		// 테스트에 대한 설명을 제공합니다. 이 설명은 테스트 실행 결과에 표시됩니다.
	void test() throws Exception {						// 테스트 메소드입니다. 'throws Exception'은 이 메소드가 예외를 던질 수 있음을 나타냅니다.
		// 테스트를 수행할 예정인 코드입니다.
		mockMvc.perform(MockMvcRequestBuilders.post("/posts")
//				.contentType(MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//				.param("title", "글제목입니다.")
//				.param("content", "글내용입니다.")
				.contentType(APPLICATION_JSON)
				.content("{\"title\": \"글제목입니다.\", \"content\": \"글내용입니다.\"}")
				)
		// .perform() 메소드는 MockMvc를 사용하여 HTTP 요청을 실행합니다.
		// MockMvcRequestBuilders.get("/posts")는 "/posts" 경로로 HTTP GET 요청을 생성합니다.
		.andExpect(MockMvcResultMatchers.status().isOk())
		// .andExpect() 메소드는 실행된 요청의 결과를 검증합니다. 
		// MockMvcResultMatchers.status().isOk()는 HTTP 응답 상태 코드가 200(OK)인지 확인합니다.
//		.andExpect(MockMvcResultMatchers.content().string("Hello World"))
		// 여기서도 .andExpect() 메소드를 사용합니다.
		// MockMvcResultMatchers.content().string("Hello World")는 응답 본문이 "Hello World" 문자열과 일치하는지 확인합니다.
		.andDo(MockMvcResultHandlers.print());
		// .andDo() 메소드는 추가적인 동작을 수행합니다.
		// 여기서 MockMvcResultHandlers.print()는 테스트의 결과를 콘솔에 출력하도록 지시합니다. 이는 테스트의 디버깅에 유용합니다.
		
		/* andDo의 결과값
		 * 
		 * MockHttpServletRequest: HTTP Method = GET Request URI = /posts Parameters =
		 * {} Headers = [] Body = null Session Attrs = {}
		 * 
		 * Handler: Type = com.hodolm.api.controller.PostController Method =
		 * com.hodolm.api.controller.PostController#get()
		 * 
		 * Async: Async started = false Async result = null
		 * 
		 * Resolved Exception: Type = null
		 * 
		 * ModelAndView: View name = null View = null Model = null
		 * 
		 * FlashMap: Attributes = null
		 * 
		 * MockHttpServletResponse: Status = 200 Error message = null Headers =
		 * [Content-Type:"text/plain;charset=UTF-8", Content-Length:"11"] Content type =
		 * text/plain;charset=UTF-8 Body = Hello World Forwarded URL = null Redirected
		 * URL = null Cookies = []
		 */
	}
	
	@Test
	@DisplayName("/posts 요청시 title값은 필수다.")
	void test2() throws Exception {
		mockMvc.perform(MockMvcRequestBuilders.post("/posts")
				.contentType(APPLICATION_JSON)
				.content("{\"title\": \"\", \"content\": \"글내용입니다.\"}")
				)
		.andExpect(MockMvcResultMatchers.status().isBadRequest())
		.andExpect(MockMvcResultMatchers.jsonPath("$.code").value("400"))
		.andExpect(MockMvcResultMatchers.jsonPath("$.message").value("잘못된 요청입니다."))
		.andExpect(MockMvcResultMatchers.jsonPath("$.vaildation.title").value("타이틀을 입력해주세요."))
		.andDo(MockMvcResultHandlers.print());
		
	}
	
	@Test
	@DisplayName("/posts 요청시 DB에 값이 저장된다.")
	void test3() throws Exception {
		
		// when
		mockMvc.perform(MockMvcRequestBuilders.post("/posts")
				.contentType(APPLICATION_JSON)
				.content("{\"title\": \"제목입니다.\", \"content\": \"내용입니다.\"}")
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andDo(MockMvcResultHandlers.print());
		
		// then
		Assertions.assertEquals(1L, postRepository.count());		// count의 반환 타입은 Long
		
		Post post = postRepository.findAll().get(0);
		assertEquals("제목입니다.", post.getTitle());
		assertEquals("내용입니다.", post.getContent());
	}
	
	@Test
	@DisplayName("/posts 요청시 JSON 형식 반환")
	void test4() throws Exception {
		
		// given
		PostCreate request = 
//				new PostCreate("제목입니다.", "내용입니다.");
				PostCreate.builder()
				.title("제목입니다.")
				.content("내용입니다")
				.build();
		
		// 객체 -> JSON 형식 변환
//		ObjectMapper objectMapper = new ObjectMapper(); -> @Autowired로 빈을 등록하는 방식으로 변경
		String toJson = objectMapper.writeValueAsString(request);
		
		System.out.println(toJson);
		// when
		mockMvc.perform(MockMvcRequestBuilders.post("/posts")
				.contentType(APPLICATION_JSON)
				.content(toJson)
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.content().string("{}"))
		.andDo(MockMvcResultHandlers.print());
	}
	
	@Test
	@DisplayName("글 1개 조회")
	void test5() throws Exception {
		// given
		Post post = Post.builder()
				.title("123123123123123")
				.content("내용")
				.build();
		postRepository.save(post);
		
		// expected
		mockMvc.perform(MockMvcRequestBuilders.get("/posts/{postId}", post.getId())
				.contentType(APPLICATION_JSON)
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.id").value(post.getId()))
		.andExpect(MockMvcResultMatchers.jsonPath("$.title").value(post.getTitle().substring(0, 10)))
		.andExpect(MockMvcResultMatchers.jsonPath("$.content").value(post.getContent()))
		.andDo(MockMvcResultHandlers.print());
		
	}

	@Test
	@DisplayName("글 여러개 조회")
	void test6() throws Exception {
		// given
//		Post post1 = Post.builder()
//				.title("title_1")
//				.content("content_1")
//				.build();
//		postRepository.save(post1);
//		
//		Post post2 = Post.builder()
//				.title("title_2")
//				.content("content_2")
//				.build();
//		postRepository.save(post2);
		
		Post post1 = postRepository.save(Post.builder()
				.title("title_1")
				.content("content_1")
				.build());
		
		Post post2 = postRepository.save(Post.builder()
				.title("title_2")
				.content("content_2")
				.build());
		
		// expected
		mockMvc.perform(MockMvcRequestBuilders.get("/posts")
				.contentType(APPLICATION_JSON)
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.is(2)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(post1.getId()))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].title").value("title_1"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].content").value("content_1"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].id").value(post2.getId()))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].title").value("title_2"))
		.andExpect(MockMvcResultMatchers.jsonPath("$[1].content").value("content_2"))
		.andDo(MockMvcResultHandlers.print());
		
	}
	
	@Test
	@DisplayName("글 여러개 조회 페이징")
	void test7() throws Exception {
		
		// given
		List<Post> requestPosts = IntStream.range(1, 31)
				.mapToObj(i -> Post.builder()
						.title("제목" + i)
						.content("내용" + i)
						.build()
					)
				.collect(Collectors.toList());
		
		postRepository.saveAll(requestPosts);
		
		// expected
		mockMvc.perform(MockMvcRequestBuilders.get("/posts?page=1&size=10"
//				+ "&size=5"
				)
				.contentType(APPLICATION_JSON)
				)
		.andExpect(MockMvcResultMatchers.status().isOk())
		.andExpect(MockMvcResultMatchers.jsonPath("$.length()", Matchers.is(10)))
		.andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(30))
		.andDo(MockMvcResultHandlers.print());
	}
	
}
