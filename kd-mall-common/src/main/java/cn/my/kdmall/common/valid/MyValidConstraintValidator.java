package cn.my.kdmall.common.valid;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.HashSet;
import java.util.Set;

public class MyValidConstraintValidator implements ConstraintValidator<MyValid, Integer> {

    Set<Integer> set = new HashSet();

    /**
     * 初始化
     * @param constraintAnnotation
     */
    @Override
    public void initialize(MyValid constraintAnnotation) {
        int[] vals = constraintAnnotation.vals();
        for (int i: vals) {
            set.add(i);
        }
    }

    /**
     * 判断是否校验成功
     *
     * @param value
     * @param constraintValidatorContext
     * @return
     */
    @Override
    public boolean isValid(Integer value, ConstraintValidatorContext constraintValidatorContext) {
        return set.contains(value);
    }

}
