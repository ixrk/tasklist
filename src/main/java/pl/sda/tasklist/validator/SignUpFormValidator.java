package pl.sda.tasklist.validator;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import pl.sda.tasklist.dto.SignUpForm;
import org.apache.commons.lang3.StringUtils;

@Component
@Qualifier("signUpValidator")
public class SignUpFormValidator implements Validator {
    public boolean supports(Class<?> aClass) {
        return SignUpForm.class.equals(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        SignUpForm form = (SignUpForm) target;

        if (StringUtils.isBlank(form.getUserName())) {
            errors.rejectValue("userName", "validator.field.notEmpty");
        }

        if (!form.getPassword().matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,}$")) {
            errors.rejectValue("password", "validator.password.incorrectValue");
        }

        if (StringUtils.isBlank(form.getPassword())) {
            errors.rejectValue("repeatedPassword", "validator.field.notEmpty");
        }

        if (!form.getPassword().equals(form.getRepeatedPassword())) {
            errors.rejectValue("password", "validator.password.notEquals");
        }
    }
}
