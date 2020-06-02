package com.peas.springboot.thread.pool;

public interface DiscardPolicy {
     void discard() throws DiscardException;
}
