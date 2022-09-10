package pl.marcin.offers.validators.validation;

import org.springframework.stereotype.Service;

@Service
public class ClientDtoMapper {

    Client map(ClientDto clientDto) {
        Client client = new Client();
        client.setFirstName(client.getFirstName());
        client.setLastName(client.getLastName());
        client.setEmail(client.getEmail());
        client.setAge(client.getAge());
        return client;
    }
}
