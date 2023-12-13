package com.hodolm.api.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Post {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	public String title;
	
	@Lob
	public String content;

	@Builder
	public Post(String title, String content) {
		this.title = title;
		this.content = content;
	}
	/*
	 * Lombok의 @Getter 어노테이션은 클래스의 모든 필드에 대해 기본 getter 메소드를 생성합니다.
	 * 하지만, 만약 클래스 내에 같은 이름의 메소드가 이미 정의되어 있다면, Lombok은 해당 필드에 대한 getter를 생성하지 않습니다.
	 * 따라서 @Overring을 사용하지 않아도 됩니다.
	 */
	// 다른 서비스에 영향이 가기 때문에 서비스 정책을 여기에 적용하지 않아야 한다. 절대.
	// 서비스 정책에 맞는 응답 클래스로 분리하기.
//	public String getTitle() {
//		return this.title.substring(0, 10);
//	}
}
