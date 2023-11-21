package info.bonian.beans;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.net.URL;
import java.util.Iterator;


/**
 * 思考。读取，是一个工具，不应该有太复杂的功能
 * @description: 用于读取xml文件
 * @author: here
 * @date: 2023/9/19 19:59
 */
public class ClassPathXmlResource implements Resource{

    private Document document;
    private Element rootElement;
    private Iterator<Element> elementIterator;

    public ClassPathXmlResource(String filename) {
        URL url = this.getClass().getClassLoader().getResource(filename);
        SAXReader saxReader = new SAXReader();
        //  思考： 一般给多个初始属性赋值，都应该是构造方法，或者初始化方法init·
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
        return elementIterator.hasNext();
    }

    @Override
    public Object next() {
        return elementIterator.next();
    }
}
