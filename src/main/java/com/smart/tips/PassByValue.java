package com.smart.tips;

/**
 * Created by admin on 2018/11/27.
 */
public class PassByValue {
    /*作者：Intopass
    链接：https://www.zhihu.com/question/31203609/answer/50992895
    来源：知乎
    著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。*/
    public static void main(String[] args) {


        //  第一个例子：基本类型
        int num = 1;
        fooInt(num); // num 没有被改变
        System.out.println(num);

//  第二个例子：没有提供改变自身方法的引用类型
        String str = "linux";
        fooString(str); // str 也没有被改变
        System.out.println(str);

      //  第三个例子：提供了改变自身方法的引用类型
        StringBuilder sb = new StringBuilder("iphone");

        fooSB1(sb); // sb 被改变了，变成了"iphone4"。
        System.out.println(sb.toString());

      //  第四个例子：提供了改变自身方法的引用类型，但是不使用，而是使用赋值运算符。
        StringBuilder sb2 = new StringBuilder("iphone");

        fooSB2(sb2); // sb 没有被改变，还是 "iphone"。
        System.out.println(sb2.toString());
    }


    static void fooInt(int value) {
        value = 100;
    }


    static void fooString(String text) {
        text = "windows";
    }

    static void fooSB1(StringBuilder builder) {
        builder.append("4");
    }

    static void fooSB2(StringBuilder builder) {
        builder = new StringBuilder("ipad");
    }
}
