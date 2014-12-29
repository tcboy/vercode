package com.oooly.api.vercode.common;

import java.util.List;

/**
 * Created by wangbo on 14-12-29.
 */
public class CodeMessage {

    private final String SEPARATOR = "|";

    private int code;
    private String[] msg;

    public CodeMessage(int code, String... msg) {
        this.code = code;
        this.msg = msg;
    }

    public CodeMessage(int code, List<String> msg) {
        this.code = code;
        msg.toArray(this.msg);
    }

    @Override
    public String toString()
    {
        StringBuilder sb = new StringBuilder();
        sb.append(code);
        for (String m : msg)
        {
            sb.append(SEPARATOR).append(m);
        }

        return sb.toString();
    }
}
