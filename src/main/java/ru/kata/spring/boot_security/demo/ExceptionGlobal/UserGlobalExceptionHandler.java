package ru.kata.spring.boot_security.demo.ExceptionGlobal;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.kata.spring.boot_security.demo.rest.exception_handler.UserIncorrectData;
import ru.kata.spring.boot_security.demo.rest.exception_handler.UserIncorrectDataException;

@ControllerAdvice
public class UserGlobalExceptionHandler {

    //обработка исключения при запросе user с ID, которого нет в базе
    //ResponseEntity - обертка
    //На вход exception, которую вызвали, затем объект для JSON, подаем в него текст ошибки из exception и возвращаем в обертке.
    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> handleException(UserIncorrectDataException exception) {
        UserIncorrectData userIncorrectData = new UserIncorrectData();
        userIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(userIncorrectData, HttpStatus.NOT_FOUND);
    }

    //остальные ошибки
    @ExceptionHandler
    public ResponseEntity<UserIncorrectData> handleException(Exception exception) {
        UserIncorrectData userIncorrectData = new UserIncorrectData();
        userIncorrectData.setInfo(exception.getMessage());
        return new ResponseEntity<>(userIncorrectData, HttpStatus.BAD_REQUEST);
    }

}
