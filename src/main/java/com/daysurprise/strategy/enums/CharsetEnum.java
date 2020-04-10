package com.daysurprise.strategy.enums;

/**
 * @author wangjie
 * @version V1.0
 * @date 2020/4/10 17:31
 * @desc:
 * @className com.daysurprise.strategy.enums.CharsetEnum
 */
public enum CharsetEnum {

                         UTF8("UTF-8"), GBK("GBK"), GB2312("GB2312"), ISO8859_1("ISO8859-1");

    private String value;

    CharsetEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

}
