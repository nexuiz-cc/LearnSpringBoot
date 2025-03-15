package com.example.demo.util;
import org.jasypt.encryption.pbe.StandardPBEStringEncryptor;
import org.springframework.stereotype.Component;
@Component
public class EncryptionUtil {
    private StandardPBEStringEncryptor encryptor;

    public EncryptionUtil() {
        this.encryptor = new StandardPBEStringEncryptor();
        this.encryptor.setPassword("yourSecretKey"); // 使用你在 application.properties 中配置的密钥
    }

    public String encrypt(String plainText) {
        return encryptor.encrypt(plainText);
    }

    public String decrypt(String encryptedText) {
        return encryptor.decrypt(encryptedText);
    }
}
