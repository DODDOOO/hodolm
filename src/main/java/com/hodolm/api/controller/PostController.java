package com.hodolm.api.controller;

import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.hodolm.api.domain.Post;
import com.hodolm.api.request.PostCreate;
import com.hodolm.api.response.PostResponse;
import com.hodolm.api.service.PostService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
public class PostController {
	
	private final PostService postService;
	/*
	 *  SSR : JSP, thymeleaf, mustache, freemarker
	 *  html 랜더링
	 */
	/*
	 * SPA : VUE + SSR = NUXT.js, REACT + SSR = NEXT.js
	 * 자바스크립트 <-> API (JSON)
	 */
	/*
	 * Http 메소드 : GET, POST, PUT, PATCH, DELETE, OPTIONS, HEAD, TRACE, CONNECT
	 */
	@PostMapping("/posts")
	public Map<String, String> post(
//			@RequestParam String title, @RequestParam String content
//			@RequestParam Map<String, String> param
			@RequestBody @Valid PostCreate request
//			, BindingResult result
			) {
		/*
		 * 데이터를 검증하는 이유
		 * 
		 * 1. client 개발자가 깜밖하고 값을 보내지 않을 수 있다.
		 * 2. client bug로 값이 누락될 수 있다.
		 * 3. 외부에서 값을 임의로 조작해서 보내는 경우를 방지한다.
		 * 4. DB에 값을 저장할 때 의도치 않은 오류를 방지한다.
		 * 5. 서버 개발자의 편의성을 위해서 검증한다.
		 */
		
//		log.info("title={}, content={}", title, content);
//		log.info("param={}", param.toString());
//		
//		String title = param.getTitle();
//		String content = param.getContent();
		
//		if (title == null || title.isEmpty()) {
//			/*
//			 * Exception처리를 일일이 하지말아야 하는 이유
//			 * 
//			 * 1. 빡세다. (노가다)
//			 * 2. 개발팁 -> 무언가 3번이상 반복작업을 할 때 내가 잘못하고 있는 게 아닌지 의심해봐야 한다.
//			 * 3. 누락가능성이 있다.
//			 * 4. 생각보다 검증해야 할 것이 많다.
//			 * 5. 개발자스럽지 않다...
//			 */
//			throw new Exception("타이틀 값이 없어요.");
//		}
//		if (content == null || content.isEmpty()) {
//			throw new Exception("글 내용이 없어요.");
//		}
		/*
		 * BindingResult로 예외처리를 할 때 문제점
		 * 
		 * 1. 매번 메서드마다 값을 검증해야 한다.	->	버그가 발생할 여지가 많아진다.
		 * 2. 응답값에 HashMap이 들어가있는데 응답 클래스를 따로 만들어주는 것이 좋다.
		 * 3. 여러개의 에러처리를 하기 때문에 복잡하다.
		 * 4. 세 번이상의 반복적인 작업을 해야한다.
		 */
//		if (result.hasErrors()) {
//			List<FieldError> fieldErrors = result.getFieldErrors();
//			FieldError firstFieldError = fieldErrors.get(0);
//			String fieldName = firstFieldError.getField();				// title
//			String errorMessage = firstFieldError.getDefaultMessage();	// 에러 메시지
//			
//			Map<String, String> error = new HashMap<>();
//			error.put(fieldName, errorMessage);
//			return error;
//			
//		}
		
		/*
		 * Case1. 저장한 데이터 Entity -> response로 응답하기
		 * Case2. 저장한 데이터의 Primary_id -> response 응답하기
		 * 				Client에서는 수신한 id로 글 조회 API를 통해 데이터를 수신 받음.
		 * Case3. 응답이 필요없음 -> Client에서 모든 POST(글) 데이터 context를 잘 관리함.
		 * Bad Case: 서버에서 응답형태를 Fix하는 것은 좋지않다. 유연하게 대응하는 것이 Best.
		 */
//		return postService.write(request);
		
		return Map.of();
//		return "Hello World";
	}
	
	/*
	 * /posts -> 글 전체 조회(검색 + 페이징)
	 * /posts/{postId} -> 글 한개만 조회
	 */
	
//	@GetMapping("/posts/{postId}")
//	public Post get(@PathVariable(name = "postId") Long id) {
//		Post post = postService.get(id);
//		
//		return post;
//		
//	}
	
	@GetMapping("/posts/{postId}")
	public PostResponse get(@PathVariable(name = "postId") Long id) {
		PostResponse post = postService.get(id);
		
		// Request 클래스 -> PostCreate
		// Response 클래스 -> PostResponse
		
		return post;
		
	}
	
//	@GetMapping("/posts/{postId}/rss")
//	public Post getRss(@PathVariable(name = "postId") Long id) {
//		Post post = postService.getRss(id);
//		
//		return post;
//		
//	}
	
	// 여러개의 글 조회
	@GetMapping("/posts")
	public List<PostResponse> getList() {
		
		return postService.getList();
	}
}
