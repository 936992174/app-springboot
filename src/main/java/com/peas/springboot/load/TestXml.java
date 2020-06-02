package com.peas.springboot.load;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

public class TestXml {
    public static void main(String[] args) throws ParserConfigurationException, IOException, SAXException {
        parseTagName();
    }

    private static void parseTagName() throws ParserConfigurationException, SAXException, IOException {
        DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
        Document document = documentBuilder.parse("D:\\user\\workplace_maven\\app-maven\\app-springboot\\src\\main\\resources\\resources\\test.xml");
        NodeList list = document.getElementsByTagName("name");
        for(int i=0;i<list.getLength();i++){
            Node item = list.item(i);
            String textContent = item.getTextContent();
            System.out.println(textContent);
        }
    }

}

class MyHandler extends DefaultHandler{
    @Override
    public void startDocument() throws SAXException {
        super.startDocument();
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        super.characters(ch, start, length);
    }

    @Override
    public void endDocument() throws SAXException {
        super.endDocument();
    }
}
