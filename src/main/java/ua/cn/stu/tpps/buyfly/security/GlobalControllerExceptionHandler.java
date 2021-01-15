package ua.cn.stu.tpps.buyfly.security;

import org.hibernate.service.spi.ServiceException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

import javax.persistence.PersistenceException;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)  // 500
    @ExceptionHandler(value = {ServiceException.class, PersistenceException.class})
    public ResponseEntity<ExceptionResponse> handleConflict(Exception ex, WebRequest request) {
        return new ResponseEntity<>(new ExceptionResponse(ex.getLocalizedMessage(), 500), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    public class ExceptionResponse {
        public String exception;

        public int code;

        public ExceptionResponse(String exception, int code) {
            this.exception = exception;
            this.code = code;
        }
    }

}
