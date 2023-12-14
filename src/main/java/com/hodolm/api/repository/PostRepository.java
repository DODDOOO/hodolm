package com.hodolm.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hodolm.api.domain.Post;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

}
