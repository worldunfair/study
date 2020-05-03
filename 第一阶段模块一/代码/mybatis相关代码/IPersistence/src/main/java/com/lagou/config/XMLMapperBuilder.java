package com.lagou.config;

import com.lagou.pojo.Configuration;
import com.lagou.pojo.MappedStatement;
import com.lagou.pojo.SqlType;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.InputStream;
import java.util.List;

public class XMLMapperBuilder {

    private Configuration configuration;

    public XMLMapperBuilder(Configuration configuration) {
        this.configuration =configuration;
    }

    public void parse(InputStream inputStream) throws DocumentException {

        Document document = new SAXReader().read(inputStream);
        Element rootElement = document.getRootElement();

        String namespace = rootElement.attributeValue("namespace");
        parseSelect(rootElement,namespace);
        parseUpdate(rootElement,namespace);
        parseDelete(rootElement,namespace);

    }

    void parseSelect(Element rootElement,String namespace){
        List<Element> list = rootElement.selectNodes("//select");
        for (Element element : list) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("resultType");
            String paramterType = element.attributeValue("paramterType");
            String sqlText = element.getTextTrim();
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParamterType(paramterType);
            mappedStatement.setSql(sqlText);
            mappedStatement.setSqlType(SqlType.SELECT);
            String key = namespace+"."+id;
            configuration.getMappedStatementMap().put(key,mappedStatement);
        }
    }

    void parseUpdate(Element rootElement,String namespace){
        List<Element> list = rootElement.selectNodes("//update");
        for (Element element : list) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("java.lang.Boolean");
            String paramterType = element.attributeValue("paramterType");
            String sqlText = element.getTextTrim();
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParamterType(paramterType);
            mappedStatement.setSql(sqlText);
            mappedStatement.setSqlType(SqlType.UPDATE);
            String key = namespace+"."+id;
            configuration.getMappedStatementMap().put(key,mappedStatement);
        }
    }

    void parseDelete(Element rootElement,String namespace){
        List<Element> list = rootElement.selectNodes("//delete");
        for (Element element : list) {
            String id = element.attributeValue("id");
            String resultType = element.attributeValue("java.lang.Boolean");
            String paramterType = element.attributeValue("paramterType");
            String sqlText = element.getTextTrim();
            MappedStatement mappedStatement = new MappedStatement();
            mappedStatement.setId(id);
            mappedStatement.setResultType(resultType);
            mappedStatement.setParamterType(paramterType);
            mappedStatement.setSql(sqlText);
            mappedStatement.setSqlType(SqlType.DELETE);
            String key = namespace+"."+id;
            configuration.getMappedStatementMap().put(key,mappedStatement);
        }
    }


}
