package com.peas.springboot.thread.designer.context;

public class QueryFromHttpAction {
    public void execute(){
        try {
            Thread.sleep(1000);
            Context context = ActionContext.getInstance().getContext();
            String name = context.getName();
            String cardId = getCardId(name);
            context.setCardId(cardId);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private String getCardId(String name) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "123456"+Thread.currentThread().getId();
    }
}
