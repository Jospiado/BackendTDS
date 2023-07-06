package br.com.projeto.api.exceptionhandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.lang.Nullable;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class TdsExceptionHandler extends ResponseEntityExceptionHandler {
    @Autowired
    private MessageSource messageSource;

    @Override
    @Nullable
    protected ResponseEntity<Object> handleHttpMessageNotReadable(HttpMessageNotReadableException ex,
            HttpHeaders headers, HttpStatusCode status, WebRequest request) {
        // TODO Auto-generated method stub

        String mensagemUsuario = messageSource.getMessage("mensagem.invalida", null, LocaleContextHolder.getLocale());
        String mensagemDesenvolvedor = ex.getCause().toString();

        return handleExceptionInternal(ex, new Erro(mensagemUsuario, mensagemDesenvolvedor), headers,
                HttpStatus.BAD_REQUEST, request);
    }

    public static class Erro {
        public String mensagemUsuario;
        public String mensagemDesenvolvedor;

        public Erro(String mensagemUsuario, String mensagemDesenvolvedor) {
            this.mensagemUsuario = mensagemUsuario;
            this.mensagemDesenvolvedor = mensagemDesenvolvedor;
        }

        public String getMensagemUsuario() {
            return mensagemUsuario;
        }

        public String getMensagemDesvenvolvedor() {
            return mensagemDesenvolvedor;
        }
    }
}
