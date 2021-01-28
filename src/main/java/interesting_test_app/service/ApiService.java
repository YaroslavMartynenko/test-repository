package interesting_test_app.service;

import interesting_test_app.domain.Request;
import interesting_test_app.domain.Response;

public interface ApiService {
    Response processRequest(Request request);
}
