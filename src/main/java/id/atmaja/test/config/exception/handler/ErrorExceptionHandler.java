package id.atmaja.test.config.exception.handler;

import id.atmaja.test.base.BaseResponse;
import id.atmaja.test.utils.ExceptionUtil;
import io.jsonwebtoken.ExpiredJwtException;
import javassist.NotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

@ControllerAdvice
public class ErrorExceptionHandler extends ResponseEntityExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(ErrorExceptionHandler.class);

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
                                                                  HttpHeaders headers,
                                                                  HttpStatusCode status,
                                                                  WebRequest request) {
        StringBuilder builder = new StringBuilder();
        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            builder.append(error.getField() + error.getDefaultMessage() + "\n");
        }
        for (ObjectError error : ex.getBindingResult().getGlobalErrors()) {
            builder.append(error.getObjectName() + error.getDefaultMessage() + "\n");
        }

        return handleExceptionInternal(ex, createGeneralError(), headers, HttpStatus.OK, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(MissingServletRequestParameterException ex,
                                                                          HttpHeaders headers,
                                                                          HttpStatusCode status,
                                                                          WebRequest request) {
        String error = ex.getParameterName() + " parameter is missing";

        return new ResponseEntity(createGeneralError(), new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                                                                   HttpHeaders headers,
                                                                   HttpStatusCode status,
                                                                   WebRequest request) {
        String error = ex.getHttpMethod() + " " + ex.getRequestURL();
        return new ResponseEntity(createGeneralError(), new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(HttpMediaTypeNotSupportedException ex,
                                                                     HttpHeaders headers,
                                                                     HttpStatusCode status,
                                                                     WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getMessage());
        builder.append(
                " method is not supported for this request. Supported methods are ");

        return new ResponseEntity(createGeneralError(), new HttpHeaders(), HttpStatus.OK);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(HttpMediaTypeNotAcceptableException ex,
                                                                      HttpHeaders headers,
                                                                      HttpStatusCode status,
                                                                      WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(ex.getSupportedMediaTypes());
        builder.append(" media type is not supported. Supported media types are ");
        ex.getSupportedMediaTypes().forEach(t -> builder.append(t + ", "));

        return new ResponseEntity(createGeneralError(), new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Object> handleConstraintViolation(
            ConstraintViolationException ex, WebRequest request) {
        StringBuilder builder = new StringBuilder();
        for (ConstraintViolation<?> violation : ex.getConstraintViolations()) {
            builder.append(violation.getRootBeanClass().getName() + " " + violation.getPropertyPath() + ": " + violation.getMessage() + "\n");
        }
        return new ResponseEntity(createGeneralError(), new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<Object> handleMethodArgumentTypeMismatch(MethodArgumentTypeMismatchException ex, WebRequest request) {
        String error = ex.getName() + " should be of type " + ex.getRequiredType().getName();
        return new ResponseEntity(createGeneralError(), new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler({ExpiredJwtException.class})
    public ResponseEntity<Object> handleTokenException(Exception ex, WebRequest request) {
        String message = ex.getMessage();
        if (ex instanceof ExpiredJwtException) {
            message = "Token Expired";
        }
        return new ResponseEntity(createGeneralError(), new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleAll(Exception ex, WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(getExceptionClassName(ex)).append("\n").append(ex.getMessage());

        logger.error(builder.toString());
        return new ResponseEntity(createGeneralError(), new HttpHeaders(), HttpStatus.OK);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<Object> handleRouteNotFound(Exception ex, WebRequest request) {
        StringBuilder builder = new StringBuilder();
        builder.append(getExceptionClassName(ex)).append("\n").append(ex.getMessage());

        logger.error(builder.toString());
        return new ResponseEntity(createGeneralError(), new HttpHeaders(), HttpStatus.OK);
    }

    private String getExceptionClassName(Exception ex) {
        if (ex instanceof NullPointerException) {
            return NullPointerException.class.getName();
        } else if (ex instanceof IllegalStateException) {
            return IllegalStateException.class.getName();
        }

        return Exception.class.getName();
    }

    private BaseResponse createGeneralError() {
        return ExceptionUtil.createGeneralErrorResponse();
    }
}
