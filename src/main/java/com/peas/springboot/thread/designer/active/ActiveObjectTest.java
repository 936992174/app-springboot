package com.peas.springboot.thread.designer.active;

public class ActiveObjectTest {
    public static void main(String[] args) {
            ActiveObject activeObject = ActiveObjectFactory.createActiveObject();
            new MakeStringClient("peas", activeObject).start();
//            new DisplayClientThread("display", activeObject).start();
    }
}
