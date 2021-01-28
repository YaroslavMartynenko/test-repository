package interesting_test_app.controller;

import interesting_test_app.domain.Request;
import interesting_test_app.domain.Response;
import interesting_test_app.service.ApiService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class ApiController {

    private ApiService apiService;

    @PostMapping(path = "/",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Response processRequest(@RequestBody Request request) {
        return apiService.processRequest(request);
    }
}
