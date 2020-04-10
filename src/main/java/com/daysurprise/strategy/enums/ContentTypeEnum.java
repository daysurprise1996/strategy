package com.daysurprise.strategy.enums;

/**
 * @author wangjie
 * @version V1.0
 * @date 2020/4/10 17:32
 * @desc:
 * @className com.daysurprise.strategy.enums.ContentTypeEnum
 */
public enum ContentTypeEnum {

                             HTML("text/html; charset=UTF-8"), TEXT_PLAIN("text/plain; charset=UTF-8"),
                             XML("text/xml; charset=UTF-8"), JSON("application/json; charset=UTF-8"),
                             FORM_URLENCODED("application/x-www-form-urlencoded"), PNG("image/png");

    private String value;

    ContentTypeEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
