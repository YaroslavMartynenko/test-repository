package interesting_test_app.service.impl;

import interesting_test_app.domain.Request;
import interesting_test_app.domain.Response;
import interesting_test_app.service.ApiService;
import interesting_test_app.service.SecurityService;
import interesting_test_app.util.JsonConverter;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ApiServiceImpl implements ApiService {

    private final SecurityService securityService;

    @Override
    public Response processRequest(Request request) {
        if (Objects.nonNull(request) && request.getId() == 1) {
            Response response = new Response("Test Testov");
            encryptAndDecryptValue(JsonConverter.convertToJson(request));
            encryptAndDecryptValue(JsonConverter.convertToJson(response));
            return response;
        }
        return null;
    }

    private void encryptAndDecryptValue(String value) {
        String encryptedValue = securityService.encrypt(value);
        securityService.decrypt(encryptedValue);
    }
}
