package com.hodolm.api.repository;

import java.util.List;

import com.hodolm.api.domain.Post;
import com.hodolm.api.domain.QPost;
import com.hodolm.api.request.PostSearch;
import com.querydsl.jpa.impl.JPAQueryFactory;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

	private final JPAQueryFactory jpaQueryFactory;

	@Override
	public List<Post> getList(PostSearch postSearch) {
		return jpaQueryFactory.selectFrom(QPost.post)
		.limit(postSearch.getSize())
		.offset(
//				(postSearch.getPage() - 1) * postSearch.getSize()
				postSearch.getOffset()
				)
		.orderBy(QPost.post.id.desc())
		.fetch();
	}
	
	
}
