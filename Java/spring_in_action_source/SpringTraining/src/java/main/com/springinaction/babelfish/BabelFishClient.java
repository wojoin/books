package com.springinaction.babelfish;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;


public class BabelFishClient {
  public static void main(String[] args) throws Exception {
    ApplicationContext context = 
        new FileSystemXmlApplicationContext("bn.xml");

    BabelFishService babelFish = 
        (BabelFishService) context.getBean("babelFish");
    System.out.println(babelFish.BabelFish("en_es", "Hello World"));
  }
}
