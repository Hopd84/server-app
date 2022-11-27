package spb.ifuture.ru.exceprion;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ServiceException.class)
    public ResponseEntity<ErrorApi> handleServiceException(ServiceException serviceException){
        ErrorApi errorApi = getErrorApiForException(serviceException);
        return new ResponseEntity<>(errorApi, HttpStatus.valueOf(serviceException.getCode()));
    }

    private ErrorApi getErrorApiForException(ServiceException serviceException) {
        return new ErrorApi(serviceException.getMessage());
    }
}
