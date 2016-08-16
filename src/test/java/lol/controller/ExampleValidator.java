package lol.controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

/**
 * root
 * 2016年8月14日 下午8:55:19
 */
public class ExampleValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
			return Example.class.equals(clazz);
	}

	@Override
	public void validate(Object obj, Errors e) {
		ValidationUtils.rejectIfEmpty(e, "name", "name.empty");
		Example example  =(Example)obj;
		if(example.getAge()<0){
			e.rejectValue("age", "negativevalue");
		}else if(example.getAge()>110){
			e.rejectValue("age", "too.darn.old");
		}
	}
	
}
