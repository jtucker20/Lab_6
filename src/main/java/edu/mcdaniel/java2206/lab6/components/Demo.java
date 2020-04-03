package edu.mcdaniel.java2206.lab6.components;

public class Demo {

// this is just a test of recursive methods and trying to understand how it works properly

    public static final int MAX_RECURSIONS = 55;

    public String valueMaker(String input, int n){
        String output = input + "$";
        System.out.println(output);
        if(output.length()<MAX_RECURSIONS){
            output = valueMaker(output, n+1);
        }
        return output;
    }

    public static void main(String[] args) {
        System.out.println((new Demo()).valueMaker("$",0));
    }
}
