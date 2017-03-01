package com.yuyang.VRHospital.utils;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.util.regex.Pattern;

/**
 * Created by fanshy on 2016/6/26.
 */
public class XmlParseUtil {

    public static String ParseContent(String content){
        String listValue = "";
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            XmlPullParser xmlPullParser = factory.newPullParser();
            xmlPullParser.setInput(new StringReader(content));
            int eventType = xmlPullParser.getEventType();

            while (eventType != XmlPullParser.END_DOCUMENT) {
                String nodeName = xmlPullParser.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG: {
                        if ("QuestionItem".equals(nodeName)) {
                            if(listValue != "") {
                                listValue += ",";
                            }
                            listValue += xmlPullParser.nextText();
                        }
                        break;
                    }
                    case XmlPullParser.END_TAG: {
                        break;
                    }
                    default:
                        break;
                }
                eventType = xmlPullParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return listValue;
    }

    public static String TrimHtmltag(String htmlstring) {
        if(htmlstring==null || "".equals(htmlstring)) return "";
        String textStr ="";
        java.util.regex.Pattern pattern;
        java.util.regex.Matcher matcher;

        try {
            String regEx_remark = "<!--.+?-->";
            String regEx_script = "<[\\s]*?script[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?script[\\s]*?>"; //定义script的正则表达式{或<script[^>]*?>[\\s\\S]*?<\\/script> }
            String regEx_style = "<[\\s]*?style[^>]*?>[\\s\\S]*?<[\\s]*?\\/[\\s]*?style[\\s]*?>"; //定义style的正则表达式{或<style[^>]*?>[\\s\\S]*?<\\/style> }
            String regEx_html = "<[^>]+>"; //定义HTML标签的正则表达式
            String regEx_html1 = "<[^>]+";
            htmlstring = htmlstring.replaceAll("\n","");
            htmlstring = htmlstring.replaceAll("\t","");
            pattern= Pattern.compile(regEx_remark);//过滤注释标签
            matcher=pattern.matcher(htmlstring);
            htmlstring=matcher.replaceAll("")    ;

            pattern = Pattern.compile(regEx_script,Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(htmlstring);
            htmlstring = matcher.replaceAll(""); //过滤script标签




            pattern = Pattern.compile(regEx_style,Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(htmlstring);
            htmlstring = matcher.replaceAll(""); //过滤style标签

            pattern = Pattern.compile(regEx_html,Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(htmlstring);
            htmlstring = matcher.replaceAll(""); //过滤html标签

            pattern = Pattern.compile(regEx_html1,Pattern.CASE_INSENSITIVE);
            matcher = pattern.matcher(htmlstring);
            htmlstring = matcher.replaceAll(""); //过滤html标签


            textStr = htmlstring.trim();

        }catch(Exception e) {
            System.out.println("获取HTML中的text出错:");
            e.printStackTrace();
        }

        return textStr;//返回文本字符串
    }
}
