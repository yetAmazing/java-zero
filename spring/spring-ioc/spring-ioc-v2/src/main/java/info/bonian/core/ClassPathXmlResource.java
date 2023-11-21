package info.bonian.core;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;

/**
 * 注意resource和reader的区别
 * resource是资源
 * reader是读取
 * @description: 读取xml文件
 * @author: here
 * @date: 2023/9/21 00:04
 */
public class ClassPathXmlResource implements Resource {

    Document document;
    Element rootElement;
    private Iterator<Element> elementIterator;

    public ClassPathXmlResource(String fileName) {
        SAXReader saxReader = new SAXReader();
        URL url = this.getClass().getClassLoader().getResource(fileName);
        try {
            this.document = saxReader.read(url);
            this.rootElement = document.getRootElement();
            this.elementIterator = rootElement.elementIterator();
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean hasNext() {
        return this.elementIterator.hasNext();
    }

    @Override
    public Object next() {
        return this.elementIterator.next();
    }
}
