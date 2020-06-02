package com.peas.springboot.thread.designer.future;

/**
 * 执行完任务会回调通知
 */
public class AsynInvoker {
    public static void main(String[] args) throws InterruptedException {
        FutureService service = new FutureService();
        Future<String> future = service.submit(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "FINISH";
        }, AsynInvoker::test);

        System.out.println("============");
        System.out.println("do other thing");
        Thread.sleep(1000);
        System.out.println("==============");


    }

    public static void test(String result){
        System.out.println("后续操作");
    };
}
