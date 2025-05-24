package com.atguigu.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;
/**
 * 校验器：规定ListValue这个注解 用于校验 Integer 类型的数�? * 	POSTman :{"name":"aaa","logo":"https://github.com/1046762075","sort":0,"firstLetter":"d","showStatus":0}
 */
public class ListValueConstraintValidator
        implements ConstraintValidator<ListValue,Integer> {//泛型参数<校验注解,标注字段类型>

	/**
	 * set 里面就是使用注解时规定的�? 例如: @ListValue(vals = {0,1})  set= {0,1}
	 */
    private Set<Integer> set = new HashSet<>();

    //初始化方�?    @Override
    public void initialize(ListValue constraintAnnotation) {
        // 获取java后端写好的限�?        int[] vals = constraintAnnotation.vals();
        for (int val : vals) {
            set.add(val);
        }
    }

    /**
     * 判断是否校验成功
     * @param value 需要校验的�?	 *              判断这个值再set里面�?     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext context) {
        // 每次请求传过来的值是否在java后端限制的值里
        return set.contains(value);
    }
}
