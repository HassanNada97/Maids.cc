package com.demo.maids.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class PointCuts {
    @Pointcut("execution(* com.demo.maids.*.*.*(..))")
    public void publicPointCutDeceleration(){

    }
    @Pointcut("execution(* com.demo.maids.controller.BookController.createBook(..))")
    public void addBookPointCutDeceleration(){

    }
    @Pointcut("execution(* com.demo.maids.controller.BookController.updateBook(..))")
    public void updateBookPointCutDeceleration(){

    }
    @Pointcut("execution(* com.demo.maids.controller.PatronController.createPatron(..))")
    public void addPatronPointCutDeceleration(){

    }
    @Pointcut("execution(* com.demo.maids.controller.PatronController.updatePatron(..))")
    public void updatePatronPointCutDeceleration(){

    }

    @Pointcut("updatePatronPointCutDeceleration() || addPatronPointCutDeceleration() || updateBookPointCutDeceleration() || addBookPointCutDeceleration()")
    public void comboPointCutDeceleration(){

    }
}
