package com.smart.tips;

import java.util.Date;

/**
 * Created by aill on 2021/11/14.
 */
public class thread {


    public static void main(String[] args) {
        MyDate md = new MyDate();
        Date d1 = new Date();
        new Thread(()->{
            System.out.println("come in "+Thread.currentThread().getName());
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            md.change();
            System.out.println("thread "+Thread.currentThread().getName()+" change number to "+md.number);
        },"hehe").start();

        while (md.number == 0){
          //  System.out.println("number :"+md.number);
           /* try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }*/
        }
        System.out.println("time:"+(new Date().getTime() - d1.getTime()));
        System.out.println("thread "+Thread.currentThread().getName()+" over, number is +"+md.number);
    }


}
class MyDate{
    int number = 0;
  //  volatile int number = 0;
    public void change(){
        this.number = 60;
    }
}