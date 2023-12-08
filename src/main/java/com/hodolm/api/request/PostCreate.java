package com.hodolm.api.request;

import javax.validation.constraints.NotBlank;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

//@ToString
@Setter
@Getter
@NoArgsConstructor
public class PostCreate {
	
	@NotBlank(message = "타이틀을 입력해주세요.")
	private String title;
	
	@NotBlank(message = "컨텐츠를 입력해주세요.")
	private String content;

	@Override
	public String toString() {
		return "PostCreate [title=" + title + ", content=" + content + "]";
	}
	
	@Builder
	public PostCreate(String title, String content) {
		this.title = title;
		this.content = content;
	}
	
	/*
	 * 빌더패턴의 장점
	 * 1. 가독성: 값 생성 과정에서 각 필드가 무엇을 의미하는지 명확하게 알 수 있어, 코드의 가독성이 향상됩니다.
	 * 2. 필요한 값만 설정: 생성하려는 객체에 필요한 값만 설정할 수 있어, 유연한 객체 생성이 가능합니다.
	 * 3. 객체의 불변성: 객체가 한 번 생성된 후 변경되지 않도록 할 수 있어, 객체의 불변성을 유지할 수 있습니다.
	 */
	
	/*
	 * 객체의 불변성이란?
	 * 빌더 패턴에서의 객체 불변성은 객체가 한 번 생성되면 그 상태가 변경되지 않음을 의미합니다. 이는 다음과 같은 방식으로 달성됩니다.
	 * 1. 불변 필드 설정: 객체의 필드들을 final로 선언하여, 한 번 할당된 값이 변경되지 않도록 합니다.
	 * 2. 빌더를 통한 초기화: 객체의 모든 필드는 빌더를 통해서만 초기화되며, 객체 생성 후에는 필드 값을 변경할 수 있는 메소드를 제공하지 않습니다.
	 * 3. 스레드 안전성: 객체의 불변성은 스레드 안전성을 향상시킵니다. 여러 스레드에서 동시에 해당 객체에 접근해도, 객체의 상태가 변경되지 않으므로 동시성 문제를 방지할 수 있습니다.
	 * 
	 * 객체의 불변성은 프로그램의 복잡성을 줄이고, 실수로 인한 오류를 방지하는 데 도움을 줍니다. 또한, 멀티스레드 환경에서의 안전한 객체 사용을 보장합니다.
	 */
	
	/*
	 * 오버로딩이 가능한 조건
	 * 1. 메소드 이름 동일: 동일한 이름의 메소드를 사용합니다.
	 * 2. 파라미터 타입 또는 개수의 차이: 메소드의 매개변수 타입, 개수, 순서 중 적어도 하나가 달라야 합니다.
	 */
}
