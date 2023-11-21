package info.bonian.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;


/**
 * @description: 读取资源
 * @author: here
 * @date: 2023/10/29 23:33
 */
public class ClassPathXmlResource implements Resource{

    private Document document;
    private Element rootElement;
    private Iterator<Element> elementIterator;

    public ClassPathXmlResource(String fileName){
        SAXReader saxReader = new SAXReader();
        URL url = this.getClass().getClassLoader().getResource(fileName);
        try{
            this.document = saxReader.read(url);
            this.rootElement = this.document.getRootElement();
            this.elementIterator = rootElement.elementIterator();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    public boolean hasNext() {
        return this.elementIterator.hasNext();
    }

    public Object next() {
        return this.elementIterator.next();
    }
}
