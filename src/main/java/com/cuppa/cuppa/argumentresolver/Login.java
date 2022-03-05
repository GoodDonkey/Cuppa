package com.cuppa.cuppa.argumentresolver;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.PARAMETER) // 파라미터에 적용하는 어노테이션이다.
@Retention(RetentionPolicy.RUNTIME) // 애플리케이션이 동작할 때까지 남아있다.
public @interface Login {
}
