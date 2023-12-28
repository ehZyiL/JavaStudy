package com.example.liquibase;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.core.io.ClassPathResource;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParserFactory;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class XmlParserUtils {
    private static final Log logger = LogFactory.getLog(XmlParserUtils.class);

    // 私有化getInstance方法，用于获取XMLReader实例
    private static XMLReader xmlReader;

    private XmlParserUtils() {
        // 防止实例化
    }

    public static List<ClassPathResource> loadDbChangeSetResources(String source) {
        try {
            XMLReader reader = getXMLReader();
            ChangeHandler logHandler = new ChangeHandler("include", "file");
            reader.setContentHandler(logHandler);
            Path path = new ClassPathResource(source.replace("classpath:", "")).getFile().toPath();
            reader.parse(path.toString());

            List<String> changeSetFiles = logHandler.getSets();

            List<ClassPathResource> result = new ArrayList<>();
            ChangeHandler setHandler = new ChangeHandler("sqlFile", "path");
            for (String set : changeSetFiles) {
                reader.setContentHandler(setHandler);
                Path setPath = new ClassPathResource(set).getFile().toPath();
                reader.parse(setPath.toString());
                result.addAll(setHandler.getSets().stream().map(ClassPathResource::new).collect(Collectors.toList()));
                setHandler.reset();
            }
            return result;
        } catch (Exception e) {
            throw new IllegalStateException("加载初始化脚本异常!", e);
        }
    }

    private static XMLReader getXMLReader() throws ParserConfigurationException, SAXException {
        if (xmlReader == null) {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            xmlReader = factory.newSAXParser().getXMLReader();
        }
        return xmlReader;
    }

    public static class ChangeHandler extends DefaultHandler {
        private final String tag;
        private final String attr;
        private List<String> sets = new ArrayList<>();

        /**
         * 构造函数
         *
         * @param tag  标签
         * @param attr 属性
         */
        public ChangeHandler(String tag, String attr) {
            this.tag = tag;
            this.attr = attr;
        }

        @Override
        public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
            if (tag.equals(qName)) {
                sets.add(attributes.getValue(attr));
            }
        }

        /**
         * 获取变更脚本列表
         *
         * @return 变更脚本列表
         */
        public List<String> getSets() {
            return sets;
        }

        /**
         * 重置变更脚本列表
         */
        public void reset() {
            sets.clear();
        }
    }
}