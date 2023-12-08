package com.hodolm.api.service;

import org.springframework.stereotype.Service;

import com.hodolm.api.domain.Post;
import com.hodolm.api.repository.PostRepository;
import com.hodolm.api.request.PostCreate;

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
	}

	public Post get(Long id) {
		Post post = postRepository.findById(id)
				.orElseThrow(() -> new IllegalArgumentException("존재하지 않는 글입니다."));
		
		return post;
	}
}
