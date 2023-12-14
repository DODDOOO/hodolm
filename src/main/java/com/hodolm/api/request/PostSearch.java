package com.hodolm.api.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class PostSearch {
	
	private static final int MAX_SIZE = 2000;
	
	@Builder.Default
	private Integer page = 1;
	
	@Builder.Default
	private Integer size = 10;
	
	public int getOffset() {
		// 페이지의 최솟값은 1, 사이즈의 최댓값은 2000으로 설정
		return (Math.max(1, page) - 1) * Math.min(size, MAX_SIZE);
	}
	
}
