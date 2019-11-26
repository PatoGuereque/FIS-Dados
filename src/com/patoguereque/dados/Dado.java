package com.patoguereque.dados;

public class Dado {

    private long result = 0;

    public void roll() {
        result = Math.round(Math.random() * 5) + 1;
    }

    public long getResult() {
        return result;
    }

    public String[] getDie() {
        return new String[] {
            " _____ ",
            "|     |",
            "|  " + result + "  |",
            "'_____'",
        };
    }
}
