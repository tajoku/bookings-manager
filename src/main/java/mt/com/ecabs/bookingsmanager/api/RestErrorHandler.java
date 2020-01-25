package mt.com.ecabs.bookingsmanager.api;

import lombok.extern.slf4j.Slf4j;
import mt.com.ecabs.bookingsmanager.exceptions.BaseException;
import mt.com.ecabs.bookingsmanager.exceptions.BookingNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class RestErrorHandler {

    @ExceptionHandler(BaseException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ApiResponse processBaseException(BaseException ex) {

        log.error("Controller Advice is being called. Base exception thrown", ex);
        return new ApiResponse(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(BookingNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ApiResponse processNotFoundException(BookingNotFoundException ex) {

        log.error("Controller Advice is being called. Booking not exception thrown", ex);
        return new ApiResponse(new ErrorResponse(ex.getMessage()));
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ApiResponse processException(Exception ex) {

        log.error("Controller Advice is being called. Exception thrown", ex);
        return new ApiResponse(new ErrorResponse("Unable to complete request."));
    }

}
