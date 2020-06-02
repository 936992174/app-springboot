package com.peas.springboot.thread.designer.balk;

/***************************************
 * @author:Alex Wang
 * @Date:2017/3/24 QQ:532500648
 * QQ交流群:286081824
 ***************************************/
public class BalkingClient {
    public static void main(String[] args) {
        BalkingData balkingData = new BalkingData("aa.txt", "===BEGIN====");
        new CustomerThread(balkingData).start();
        new WaiterThread(balkingData).start();
    }
}