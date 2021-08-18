package com.nallani.rest;

import com.nallani.model.AdditionalClaims;
import com.nallani.model.GenRequest;
import com.nallani.model.GenResponse;
import com.nallani.model.ValResponse;
import com.nallani.service.JweServiceImpl;
import com.nimbusds.jose.JOSEException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class JweRestController {

    @Autowired private
    JweServiceImpl service;

        @PostMapping(path = "/getjwe")
        public GenResponse customerInformation(@RequestBody @Valid GenRequest genRequest) throws JOSEException {
            HashMap<String, List<String>> jsonData = new HashMap<>();
            jsonData.put(genRequest.getAdditionalClaims().getKey(),
                            genRequest.getAdditionalClaims().getValue());
            return service.generateToken(jsonData, genRequest);
        }

    @PostMapping(path = "/validatejwe")
    public ValResponse validateJwe(@RequestBody @Valid GenResponse request) throws ParseException, JOSEException {
       return service.validateToken(request);
    }
}
