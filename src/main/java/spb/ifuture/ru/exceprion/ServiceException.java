package spb.ifuture.ru.exceprion;

import lombok.Data;
import lombok.Getter;
import org.springframework.stereotype.Component;

@Component
@Getter
public class ServiceException extends RuntimeException{
    private final int code;
    private final String message;

    public ServiceException() {
        message = "Something went wrong";
        this.code = 500;
    }

    public ServiceException(String message) {
        this.message = message;
        this.code = 500;
    }

    public ServiceException(String message, int code){
        this.message = message;
        this.code = code;
    }
}
