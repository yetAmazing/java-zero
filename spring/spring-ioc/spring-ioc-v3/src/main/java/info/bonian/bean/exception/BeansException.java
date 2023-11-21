package info.bonian.bean.exception;

/**
 * @description: TODO
 * @author: here
 * @date: 2023/10/29 01:05
 */
public class BeansException extends RuntimeException{

    private String msg;

    public BeansException() {
    }

    public BeansException(String msg) {
        super(msg);
    }
}
