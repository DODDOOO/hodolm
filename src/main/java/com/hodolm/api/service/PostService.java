package com.hodolm.api.service;

import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.hodolm.api.domain.Post;
import com.hodolm.api.repository.PostRepository;
import com.hodolm.api.request.PostCreate;
import com.hodolm.api.response.PostResponse;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class PostService {
	
	private final PostRepository postRepository;
	
	public void write(PostCreate postCreate) {
		// postCreate -> Entity
		Post post = 
//				new Post(postCreate.getTitle(), postCreate.getContent());
				Post.builder()
				.title(postCreate.getTitle())
				.content(postCreate.getContent())
				.build();
		
//		return 
		postRepository.save(post);
		log.info("포스트 ID={}, 제목={}, 내용={}", post.getId(), post.getTitle(), post.getContent());
	}

//	public Post get(Long id) {
//		Post post = postRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));
//		
//		return post;
//	}
	
	public PostResponse get(Long id) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));
		
		PostResponse response = PostResponse.builder()
					.id(post.getId())
					.title(post.title)
					.content(post.content)
					.build();
		
		return response;
	}

	public List<PostResponse> getList() {
		return postRepository.findAll().stream()
				.map(post -> new PostResponse(post)
						// 반복되는 작업을 막기위해 PostResponse에서 생성자를 오버로딩하여 사용
//						PostResponse.builder()
//						.id(post.getId())
//						.title(post.getTitle())
//						.content(post.getContent())
//						.build()
						)
				.collect(Collectors.toList());
	}
	
//	public Post getRss(Long id) {
//		Post post = postRepository.findById(id)
//				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));
//		
//		return post;
//	}
}
