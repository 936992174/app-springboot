package com.peas.springboot.thread.designer.context;

import java.util.stream.IntStream;

/**
 * 每个线程都有一个上下文互不干扰
 */
public class ExecutionTask implements Runnable {

    private QueryFromDBAction queryAction = new QueryFromDBAction();
    private QueryFromHttpAction httpAction = new QueryFromHttpAction();

    @Override
    public void run() {
        Context context = ActionContext.getInstance().getContext();
        queryAction.execute();
        System.out.println(" the name query success");
        httpAction.execute();
        System.out.println(" the cardId query success");
        System.out.println(" the name is "+context.getName()+" and cardId is "+ context.getCardId());

    }

    public static void main(String[] args) {

        IntStream.range(1,5).forEach(i->{
            new Thread(new ExecutionTask()).start();
        });
    }
}
