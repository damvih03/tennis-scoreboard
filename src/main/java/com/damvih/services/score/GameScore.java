package com.damvih.services.score;

abstract public class GameScore<T> extends Score<T> {
    abstract public String getView(int playerNumber);
}
