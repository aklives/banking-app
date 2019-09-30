package com.ex.platform;

public class StringMenuBuilder implements MenuBuilder {
    private StringBuilder state;
    private String delimiter = "\n";

    public StringMenuBuilder(){state = new StringBuilder();}
    public String build(){return state.toString();}

    public StringMenuBuilder addOption(String key, String value){
        state.append(String.format("%s. %s %s", key, value, delimiter));
        return this;
    }
}