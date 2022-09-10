package pl.marcin.offers.validators.words;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class EmailMessage {
    @NotNull
    @Email
    private String sender;
    @NotNull
    @Email
    private String recipient;
    @NotBadWord(lang = {Lang.PL, Lang.EN})
    private String message;
}