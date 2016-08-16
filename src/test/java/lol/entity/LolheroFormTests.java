package lol.entity;

import static org.junit.Assert.assertEquals;

import java.util.Locale;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.junit.Test;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

public class LolheroFormTests {
	private Validator createValidator() {
        LocalValidatorFactoryBean localValidatorFactoryBean = new LocalValidatorFactoryBean();
        localValidatorFactoryBean.afterPropertiesSet();
        return localValidatorFactoryBean;
    }
	@Test
	public void shouldNotValidateWhenNickNameEmpty() {
		LocaleContextHolder.setLocale(Locale.ENGLISH);
        LolheroForm person = new LolheroForm();
        person.setNameCn("a");

        Validator validator = createValidator();
        Set<ConstraintViolation<LolheroForm>> constraintViolations = validator.validate(person);
        assertEquals(constraintViolations.size(), 2);
        ConstraintViolation<LolheroForm> violation = constraintViolations.iterator().next();
    }

}
