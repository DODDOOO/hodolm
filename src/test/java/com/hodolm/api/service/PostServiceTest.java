package com.hodolm.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.hodolm.api.domain.Post;
import com.hodolm.api.repository.PostRepository;
import com.hodolm.api.request.PostCreate;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		
		// when
		Post post = postService.get(requestPost.getId());
		log.info("포스트 ID={}, 제목={}, 내용={}", post.getId(), post.getTitle(), post.getContent());
		
		// then
		Assertions.assertNotNull(post);
		Assertions.assertEquals("제목", post.getTitle());
		Assertions.assertEquals("내용", post.getContent());

	}

}
