package pl.marcin.offers;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import pl.marcin.offers.validators.validation.Client;
import pl.marcin.offers.validators.validation.ClientService;
import pl.marcin.offers.validators.warehouse.Equipment;
import pl.marcin.offers.validators.warehouse.Product;
import pl.marcin.offers.validators.words.EmailMessage;
import pl.marcin.offers.validators.words.EmailService;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.Locale;
import java.util.Set;

@SpringBootApplication
public class OffersApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(OffersApplication.class, args);

        Locale.setDefault(new Locale("pl"));



        EmailMessage message = new EmailMessage(
                "abc@abc.com",
                "xyz@xyz.com",
                "Hejo! Co tam kura u Ciebie słychać wariacie? Pozdro"
        );
        EmailService emailService = context.getBean(EmailService.class);
        emailService.sendMessage(message);

        Validator validator = context.getBean(Validator.class);
        Product product1 = new Product("Dell XPS 15", "Laptop 15 calowy z 2021 roku", "P13243");
        Set<ConstraintViolation<Product>> productConstraintViolations = validator.validate(product1);
        if (!productConstraintViolations.isEmpty()) {
            System.out.println("Nieprawidlowy produkt, zlamane ograniczenia:");
            productConstraintViolations.stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        }

        Equipment equipment1 = new Equipment("iPhone 13", "KARNOW123", "E7986");
        Set<ConstraintViolation<Equipment>> equipmentConstraintViolations = validator.validate(equipment1);
        if (!equipmentConstraintViolations.isEmpty()) {
            System.out.println("Nieprawidlowe wyposazenie, zlamane ograniczenia:");
            equipmentConstraintViolations.stream()
                    .map(ConstraintViolation::getMessage)
                    .forEach(System.out::println);
        }

        ClientService clientService = context.getBean(ClientService.class);
        Client client = new Client("Jan", "Kowalski", "kowaljan@gmail.com", -15);
        try {
            clientService.register(client);
            System.out.printf("Rejestracja powiodla sie: %s", client);
        } catch (ConstraintViolationException cve) {
            Set<ConstraintViolation<?>> errors = cve.getConstraintViolations();
            System.err.printf("Rejestracja nie powiodla sie %s\n", client);
            errors.stream()
                    .map(err -> " >" + err.getPropertyPath() + " " + err.getInvalidValue() + " " + err.getMessage())
                    .forEach(System.out::println);
        }
    }
}
