package ru.iteco.mfsm.dao.mfsm;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.TreeNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Service("BEAN_MFSMDAO")
public class MFSMDAO {
    @Autowired
    @Qualifier("BEAN_mfsmRestTemplate")
    private RestTemplate mfsmRestTemplate;

    public <T extends MFSMEntity> T createEntity(String name, String context, T entity, Class<T> valueType) {
        return send(name, context, HttpMethod.POST, entity, valueType);
    }

    public <T extends MFSMEntity> T updateEntity(String name, String context, T entity, Class<T> valueType) {
        return send(name, context, HttpMethod.PUT, entity, valueType);
    }

    public <T extends MFSMEntity> T send(String name, String context, HttpMethod method, T entity, Class<T> valueType) {
        try {
            System.out.println("MFSMDAO.send {name=" + name + ", context=" + context + ", method=" + method + ", entity=" + entity + "}");
            Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

            ObjectMapper mapper = new ObjectMapper();
            String json = "{\"" + name + "\":" + mapper.writerWithDefaultPrettyPrinter().writeValueAsString(entity) + "}";
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Type", "application/json;charset=UTF-8");
            HttpEntity<String> request = new HttpEntity<>(json, headers);
            System.out.println("MFSMDAO.send json=" + json);
            ResponseEntity<String> response = mfsmRestTemplate.exchange(context, method, request, String.class);

            JsonParser parser = mapper.getFactory().createParser(response.getBody());
            TreeNode node = mapper.readTree(parser);
            return mapper.treeToValue(node.get(name), valueType);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
    }
}