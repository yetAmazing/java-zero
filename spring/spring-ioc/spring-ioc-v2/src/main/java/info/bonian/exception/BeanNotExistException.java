package info.bonian.exception;

/**
 * @description: bean不存在异常
 * @author: here
 * @date: 2023/10/25 03:39
 */
public class BeanNotExistException extends RuntimeException{

    private String msg;

    public BeanNotExistException(String msg) {
        super(msg);
    }
}
