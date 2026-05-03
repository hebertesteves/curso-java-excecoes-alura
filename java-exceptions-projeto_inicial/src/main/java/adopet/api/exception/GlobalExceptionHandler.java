package adopet.api.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AdocaoException.class)
    public ResponseEntity<ResponseError> adocaoException(AdocaoException ex, HttpServletRequest request) {
        ResponseError responseError = new ResponseError(
                "https://api.exemplo.com/erros/adocao",
                "Erro na Adoção",
                HttpStatus.BAD_REQUEST.value(),
                ex.getMessage(),
                request.getRequestURI()
        );

        /* Versão Antiga (Primeira)
        ResponseError response = new ResponseError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
        );
         */
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseError);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ResponseError> trataException(Exception ex, HttpServletRequest request) {
        ResponseError responseError = new ResponseError(
                "https://api.exemplo.com/erros/interno",
                "Erro Interno do Servidor",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                ex.getMessage(),
                request.getRequestURI()
        );

        /* Versão Antiga (Primeira)
           ResponseError response = new ResponseError(
                ex.getMessage(),
                HttpStatus.BAD_REQUEST,
                LocalDateTime.now()
            );
         */

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(responseError);
    }
}
