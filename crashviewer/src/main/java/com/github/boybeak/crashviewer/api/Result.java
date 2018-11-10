package com.github.boybeak.crashviewer.api;

public class Result<T> {
    public boolean success;
    public T data;
    public int error;
    public String message;
}
