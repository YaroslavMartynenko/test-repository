package interesting_test_app.service;

public interface SecurityService {
    String encrypt(String stringToEncrypt);

    String decrypt(String stringToDecrypt);
}
