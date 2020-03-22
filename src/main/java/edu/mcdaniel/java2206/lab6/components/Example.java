package edu.mcdaniel.java2206.lab6.components;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * This class is a simple structure to allow you to do your work during the semester.
 * That is, if you want to do an assignment, copy this code, make a new class with the
 * name of your class, paste this code in there and change the class and constructor
 * names to your class, add a reference to your new class and it's major functions in
 * the Application class and then do your coding here.
 */
public class Example {

    //=============================================================================================
    // Private Assets
    //=============================================================================================

    /**
     * This provides access to logging.
     */
    private final Logger log;

    /**
     * This String variable will hold the greeting.
     */
    private String greeting;

    /**
     * This String variable will hold the name of the audience.
     */
    private String audience;


    //=============================================================================================
    // Constructor(s)
    //=============================================================================================

    /**
     * This No Argument constructor is designed to initialize the greeting to the standard
     * "Hello World."
     */
    public Example(){
        this.greeting = "Hello";
        this.audience = "World";

        this.log = LogManager.getLogger(this);

        log.debug("The output message is {} {}!", this.greeting, this.audience);
    }


    //=============================================================================================
    // Major Methods
    //=============================================================================================

    /**
     * This single argument method is designed to initialize the greeting to the value
     * supplied in the customGreeting variable.
     * @param customGreeting the custom greeting to be used.
     * @return the Example object to allow chaining.
     */
    public Example withGreeting(String customGreeting){

        this.greeting = customGreeting;

        log.debug("The greeting was changed to: {}", this.greeting);

        return this;
    }

    /**
     * This single argument method is designed to initialize the audience to the value
     * supplied in the customAudience variable.
     * @param customAudience the custom audience who will be addressed
     * @return the Example object to allow chaining.
     */
    public Example withAudience(String customAudience){

        this.audience = customAudience;

        log.debug("The audience was changed to: {}", this.audience);

        return this;
    }


    //=============================================================================================
    // Minor Methods(s)
    //=============================================================================================

    /**
     * A method to get the message.
     * @return a string of the message.
     */
    public String getMessage(){
        return getGreeting() + " " + getAudience() + "!";
    }


    //=============================================================================================
    // Getters and Setters
    //=============================================================================================

    public String getGreeting() {
        return greeting;
    }

    public void setGreeting(String greeting) {
        this.greeting = greeting;
    }

    public String getAudience() {
        return audience;
    }

    public void setAudience(String audience) {
        this.audience = audience;
    }
}
