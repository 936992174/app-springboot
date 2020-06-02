package com.peas.springboot.thread.designer.context;

public class ActionContext {
    private static final ThreadLocal<Context> THREAD_LOCAL = ThreadLocal.withInitial(Context::new);

    private ActionContext(){}

    public static ActionContext getInstance(){
        return ActionContextHolder.actionContext;
    }

    private static class ActionContextHolder{
        private final static ActionContext actionContext = new ActionContext();
    }


    public Context getContext(){
        return THREAD_LOCAL.get();
    }

}
