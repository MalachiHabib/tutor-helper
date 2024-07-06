package com.tutorhelper.response;//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//


import java.util.List;

public record PagedResponse<T>(List<T> data, int count) {
    public PagedResponse(List<T> data, int count) {
        this.data = data;
        this.count = count;
    }

    public List<T> data() {
        return this.data;
    }

    public int count() {
        return this.count;
    }
}
