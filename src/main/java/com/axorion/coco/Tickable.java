package com.axorion.coco;

public interface Tickable {
    void tick(long now);
    int getFps();
}
