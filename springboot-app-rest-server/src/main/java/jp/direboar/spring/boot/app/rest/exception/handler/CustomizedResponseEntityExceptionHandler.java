package jp.direboar.spring.boot.app.rest.exception.handler;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// see https://www.mkyong.com/spring-boot/spring-rest-validation-example/

@ControllerAdvice
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
                    HttpRequestMethodNotSupportedException ex, HttpHeaders headers,
                    HttpStatus status, WebRequest request) {
        // TODO 自動生成されたメソッド・スタブ
        return super.handleHttpRequestMethodNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotSupported(
                    HttpMediaTypeNotSupportedException ex, HttpHeaders headers, HttpStatus status,
                    WebRequest request) {
        // TODO 自動生成されたメソッド・スタブ
        return super.handleHttpMediaTypeNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMediaTypeNotAcceptable(
                    HttpMediaTypeNotAcceptableException ex, HttpHeaders headers, HttpStatus status,
                    WebRequest request) {
        // TODO 自動生成されたメソッド・スタブ
        return super.handleHttpMediaTypeNotAcceptable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex,
                    HttpHeaders headers, HttpStatus status, WebRequest request) {
        // TODO 自動生成されたメソッド・スタブ
        return super.handleMissingPathVariable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestParameter(
                    MissingServletRequestParameterException ex, HttpHeaders headers,
                    HttpStatus status, WebRequest request) {
        // TODO 自動生成されたメソッド・スタブ
        return super.handleMissingServletRequestParameter(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleServletRequestBindingException(
                    ServletRequestBindingException ex, HttpHeaders headers, HttpStatus status,
                    WebRequest request) {
        // TODO 自動生成されたメソッド・スタブ
        return super.handleServletRequestBindingException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleConversionNotSupported(
                    ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status,
                    WebRequest request) {
        // TODO 自動生成されたメソッド・スタブ
        return super.handleConversionNotSupported(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleTypeMismatch(TypeMismatchException ex,
                    HttpHeaders headers, HttpStatus status, WebRequest request) {
        // TODO 自動生成されたメソッド・スタブ
        return super.handleTypeMismatch(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
                    HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status,
                    WebRequest request) {
        // TODO 自動生成されたメソッド・スタブ
        ex.printStackTrace();
        return super.handleHttpMessageNotReadable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotWritable(
                    HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status,
                    WebRequest request) {
        // TODO 自動生成されたメソッド・スタブ
        return super.handleHttpMessageNotWritable(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
                    MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
                    WebRequest request) {
        Map<String, Object> body = createErrorMessageBody(ex.getBindingResult(), status);
        return handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleMissingServletRequestPart(
                    MissingServletRequestPartException ex, HttpHeaders headers, HttpStatus status,
                    WebRequest request) {
        // TODO 自動生成されたメソッド・スタブ
        return super.handleMissingServletRequestPart(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleBindException(BindException ex, HttpHeaders headers,
                    HttpStatus status, WebRequest request) {
        Map<String, Object> body = createErrorMessageBody(ex, status);
        return handleExceptionInternal(ex, body, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(NoHandlerFoundException ex,
                    HttpHeaders headers, HttpStatus status, WebRequest request) {
        // TODO 自動生成されたメソッド・スタブ
        return super.handleNoHandlerFoundException(ex, headers, status, request);
    }

    @Override
    protected ResponseEntity<Object> handleAsyncRequestTimeoutException(
                    AsyncRequestTimeoutException ex, HttpHeaders headers, HttpStatus status,
                    WebRequest webRequest) {
        // TODO 自動生成されたメソッド・スタブ
        return super.handleAsyncRequestTimeoutException(ex, headers, status, webRequest);
    }

    private Map<String, Object> createErrorMessageBody(BindingResult bindingResult,
                    HttpStatus status) {

        Map<String, Object> body = new LinkedHashMap<>();
        body.put("status", status.value());
        body.put("reason", status.getReasonPhrase());

        List<String> messages = bindingResult.getFieldErrors().stream()
                        .map(f -> f.getField() + ":" + f.getDefaultMessage())
                        .collect(Collectors.toList());

        body.put("messages", messages);
        return body;
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal(Exception ex, Object body,
                    HttpHeaders headers, HttpStatus status, WebRequest request) {
        // TODO 自動生成されたメソッド・スタブ
        headers.add("Content-Type", "application/json;charset=UTF-8");
        return super.handleExceptionInternal(ex, body, headers, status, request);
    }
}
