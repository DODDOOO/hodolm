package com.hodolm.api.repository;

import java.util.List;

import com.hodolm.api.domain.Post;
import com.hodolm.api.request.PostSearch;

public interface PostRepositoryCustom {
	
	List<Post> getList(PostSearch postSearch); 

}
