package info.bonian.exception;

/**
 * @description: TODO
 * @author: here
 * @date: 2023/9/19 23:27
 */
public class UnExistBeanException extends RuntimeException{

    public UnExistBeanException(){
        super();
    }

    public UnExistBeanException(String name){
        super(name);
    }
}
