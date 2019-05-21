package com.song.util;

// html转纯文本
public class HtmlToText
{
    public String getText(String html)
    {
        String text =  html.replaceAll("</?[^>]*>", "").replaceAll("\\s", "").replaceAll("&[\\w]{1,6};", "");
        return text;
    }
}
