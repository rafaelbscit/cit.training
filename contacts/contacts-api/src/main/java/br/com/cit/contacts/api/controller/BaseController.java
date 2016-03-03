package br.com.cit.contacts.api.controller;

import br.com.cit.contacts.api.constants.RestControllerConstant;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashMap;
import java.util.Map;

public class BaseController {

    public ResponseEntity buildResponseSuccess(Object body, HttpStatus httpStatus) {
        return new ResponseEntity(body, httpStatus);
    }

    public ResponseEntity buildResponseErro(HttpStatus httpStatus) {
        return new ResponseEntity(httpStatus);
    }

    public ResponseEntity buildResponseErro(String cause, HttpStatus httpStatus) {
        Map<String, String> body = new HashMap<String, String>();
        body.put(RestControllerConstant.BODY_ERRO, cause);
        return new ResponseEntity(body, HttpStatus.BAD_REQUEST);
    }

    public ResponseEntity buildResponseErro(Throwable cause, HttpStatus httpStatus) {
        return buildResponseErro(cause.getMessage(), httpStatus);
    }

}
