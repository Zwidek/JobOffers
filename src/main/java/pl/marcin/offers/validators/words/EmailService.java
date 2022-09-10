package pl.marcin.offers.validators.words;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.Set;

@Service
public class EmailService {

    private final Validator validator;

    @Bean
    public javax.validation.Validator localValidatorFactoryBean() {
        return new LocalValidatorFactoryBean();
    }

    public EmailService(@Lazy Validator validator) {
        this.validator = validator;
    }


    public void sendMessage(EmailMessage email) {
        System.out.println("Sending email...");
        Set<ConstraintViolation<EmailMessage>> violations = validator.validate(email);
        if (violations.isEmpty()) {
            System.out.println("Message has been sent successfully!");
        } else {
            System.out.println("Message contains errors.");
            for (ConstraintViolation<EmailMessage> violation : violations) {
                System.out.println(violation.getPropertyPath() + " : " + violation.getMessage());
            }
        }
    }
}
