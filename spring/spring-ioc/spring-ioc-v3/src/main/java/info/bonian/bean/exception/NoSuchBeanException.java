package info.bonian.bean.exception;

/**
 * @description: bean不存在时抛出的异常
 * @author: here
 * @date: 2023/10/29 00:14
 */
public class NoSuchBeanException extends RuntimeException{

    private String msg;

    public NoSuchBeanException(){
        super();
    }

    public NoSuchBeanException(String msg) {
        super();
    }
}


