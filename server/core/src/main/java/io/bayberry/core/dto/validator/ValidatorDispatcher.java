package io.bayberry.core.dto.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ValidatorDispatcher implements ConstraintValidator<ValidatedBy, Object> {

    @Autowired
    private ApplicationContext applicationContext;
    private Class<? extends Validator>[] validatorClasses;

    @Override
    public void initialize(ValidatedBy constraintAnnotation) {
        this.validatorClasses = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        for (Class<? extends Validator> validatorClass : validatorClasses) {
            if (!getValidator(validatorClass).isValid(value)) return false;
        }
        return true;
    }

    private Validator getValidator(Class<? extends Validator> validatorClass) {
        return this.applicationContext.getBean(validatorClass);
    }
}
