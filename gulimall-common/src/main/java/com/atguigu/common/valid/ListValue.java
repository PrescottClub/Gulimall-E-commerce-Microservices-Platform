package com.atguigu.common.valid;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * <p>Title: ListValue</p>
 * Description：JSR303自定义注�?必须有前三个方法
 * date�?020/6/1 23:25
 */
@Documented
// 指定校验�?  这里可以指定多个不同的校验器
@Constraint(validatedBy = { ListValueConstraintValidator.class })
@Target({ METHOD, FIELD, ANNOTATION_TYPE, CONSTRUCTOR, PARAMETER, TYPE_USE })
@Retention(RUNTIME)
public @interface ListValue {
	// 1 使用该属性去Validation.properties中取
	String message() default "{com.atguigu.common.valid.ListValue.message}";

	// 2
	Class<?>[] groups() default { };

	// 3
	Class<? extends Payload>[] payload() default { };

	int[] vals() default { };
}
