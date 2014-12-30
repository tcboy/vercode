package com.oooly.api.vercode.common;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangbo on 14-12-29.
 */
public class CodeMessage {

    private final String SEPARATOR = "|";

    private int code;
    private List<String> msg;

    public CodeMessage(int code) {
        this.code = code;
        this.msg = new ArrayList<String>();
    }

    public CodeMessage(int code, String... msg) {
        this.code = code;
        this.msg = new ArrayList<String>();
        for (String m : msg) {
            this.msg.add(m);
        }
    }

    public CodeMessage(int code, List<String> msg) {
        this.code = code;
        this.msg = msg;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(String.valueOf(code));
        for (String m : msg) {
            sb.append(SEPARATOR).append(m);
        }
        return sb.toString();
    }
}
