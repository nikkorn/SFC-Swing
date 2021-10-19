package com.dumbpug.sfc.input;

public interface MouseInputListener {
    void onClick(int x, int y);

    void onPress(int x, int y);

    void onRelease(int x, int y);

    void onMove(int x, int y);
}
