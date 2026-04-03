package duoc.mediturn.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.LinkedHashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarErroresDeValidacion(MethodArgumentNotValidException ex){
        Map<String, String> errores = new LinkedHashMap<>();

        for(FieldError error : ex.getBindingResult().getFieldErrors()){
            errores.put(error.getField(), error.getDefaultMessage());
        }//fin de ciclo for

        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);

    }//fin manejarErroresDeValidacion

    @ExceptionHandler(RutDuplicadoException.class)
    public ResponseEntity<String> manejarRutDuplicado (RutDuplicadoException ex){
        return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }//fin manejarRutDuplicado

    @ExceptionHandler(PacienteNoEncontradoException.class)
    public ResponseEntity<String> manejarPacienteNoEncontrado (PacienteNoEncontradoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(PacienteAsociadoNoEncontradoException.class)
    public ResponseEntity<String> manejarPacienteAsociadoNoEncontrado(PacienteAsociadoNoEncontradoException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(SolicitudNoEncontradaException.class)
    public ResponseEntity<String> manejarSolicitudNoEncontrada(RutDuplicadoException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> manejarErrorGeneral(Exception ex){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error interno del servidor.");
    }

}//termino GlobalExceptionHandler
