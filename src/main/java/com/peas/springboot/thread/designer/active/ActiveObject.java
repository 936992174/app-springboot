package com.peas.springboot.thread.designer.active;

/**
 * 接受异步消息的主动方法
 */
public interface ActiveObject {

    Result makeString(int count, char fillChar);

    void displayString(String text);

}
