package com.peas.springboot.thread.designer.context;

public class Context {
    private String name;
    private String CardId;

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getCardId() {
        return CardId;
    }

    public void setCardId(String cardId) {
        CardId = cardId;
    }
}
