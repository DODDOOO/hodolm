package com.hodolm.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hodolm.api.domain.Post;
import com.hodolm.api.repository.PostRepository;
import com.hodolm.api.request.PostCreate;
import com.hodolm.api.response.PostResponse;

@SpringBootTest
class PostServiceTest {

	@Autowired
	private PostService postService;
	
	@Autowired
	private PostRepository postRepository;
	
	@BeforeEach
	void clean() {
		postRepository.deleteAll();
	}
	
	@Test
	@DisplayName("글 작성")
	void test1() {
		// given
		PostCreate postCreate = PostCreate.builder()
				.title("제목입니다.")
				.content("내용입니다.")
				.build();
		
		// when
		postService.write(postCreate);
		
		// then
		Assertions.assertEquals(1L, postRepository.count());
		
		Post post = postRepository.findAll().get(0);
		assertEquals("제목입니다.", post.getTitle());
		assertEquals("내용입니다.", post.getContent());
	}
	
	@Test
	@DisplayName("글 1개 조회")
	void test2() {
		// given
		Post requestPost = Post.builder()
				.title("제목")
				.content("내용")
				.build();
		postRepository.save(requestPost);
		
		/*
		 * 클라이언트 요구사항 -> json응답에서 title 값 길이를 최대 10자로 해주세요.
		 * 다른 서비스에 영향이 가기 때문에 서비스 정책을 엔티티에 적용하지 않아야 한다. 절대.
		 * 서비스 정책에 맞는 응답 클래스로 분리하기.
		 */
		
		// when
//		Post
		PostResponse
						post = postService.get(requestPost.getId());
//		log.info("포스트 ID={}, 제목={}, 내용={}", post.getId(), post.getTitle(), post.getContent());
		
		// then
		Assertions.assertNotNull(post);
		Assertions.assertEquals("제목", post.getTitle());
		Assertions.assertEquals("내용", post.getContent());

	}
	
	@Test
	@DisplayName("글 여러개 조회")
	void test3() {
		// given
		Post requestPost1 = Post.builder()
				.title("제목1")
				.content("내용1")
				.build();
		postRepository.save(requestPost1);
		
		Post requestPost2 = Post.builder()
				.title("제목2")
				.content("내용2")
				.build();
		postRepository.save(requestPost2);
		
		// when
//		Post
		List<PostResponse> posts = postService.getList();
//		log.info("포스트 ID={}, 제목={}, 내용={}", post.getId(), post.getTitle(), post.getContent());
		
		// then
		Assertions.assertEquals(2L, posts.size());

	}

}
