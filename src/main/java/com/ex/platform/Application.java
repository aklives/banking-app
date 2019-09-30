package com.ex.platform;

import com.ex.data.CustomerRepository;

public abstract class Application {
    protected String prompt;

    public abstract void run(CustomerRepository cRepo);

    public String getPrompt(){return prompt;}

    public void setPrompt(String prompt){ this.prompt = prompt;}
}