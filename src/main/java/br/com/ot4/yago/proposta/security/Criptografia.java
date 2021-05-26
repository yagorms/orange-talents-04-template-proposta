package br.com.ot4.yago.proposta.security;

import org.springframework.security.crypto.encrypt.Encryptors;

import javax.persistence.AttributeConverter;
import javax.persistence.Convert;

@Convert
public class Criptografia implements AttributeConverter<String,String> {

    @Override
    public String convertToDatabaseColumn(String dado) {
        return Encryptors.text("${criptografia.password}", "7C8EF022EEC320E0").encrypt(dado);
    }

    @Override
    public String convertToEntityAttribute(String dado) {
        return Encryptors.text("${criptografia.password}", "7C8EF022EEC320E0").decrypt(dado);
    }
}
