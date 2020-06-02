package com.peas.springboot.thread.single;

public class EnumSingleTon {
    private EnumSingleTon(){}

    /**
     * 枚举类型只会被初始化一次
     */
    private enum SingleTon{
        INSTANCE;

        private final EnumSingleTon instance;

        SingleTon(){
            instance = new EnumSingleTon();
        }

        public EnumSingleTon getInstance(){
            return instance;
        }
    }

    public static EnumSingleTon getInstance(){
        return SingleTon.INSTANCE.getInstance();
    }
}
