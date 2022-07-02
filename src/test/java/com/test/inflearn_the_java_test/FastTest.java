package com.test.inflearn_the_java_test;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

// 이 어노테이션을 메서드에 사용할 수 있다.
@Target(ElementType.METHOD)
// 이 어노테이션을 런타임까지 유지를 해야 한다.
@Retention(RetentionPolicy.RUNTIME)
@Test
@Tag("fast")
// 쥬피터에서 제공하는 어노테이션
// FastTest라는 어노테이션을 @Test과 @Tag("fast") 그리고 @Retention(RetentionPolicy.RUNTIME)의 메타 어노테이션으로 사용해서
// 여러개의 어노테이션을 조합해서 만든 새로운 어노테이션
public @interface FastTest {
}
