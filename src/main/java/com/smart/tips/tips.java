package com.smart.tips;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * Created by admin on 2018/11/14.
 * 用来做试验
 */
public class tips {

    public static void main(String[] args) {
        List numberList = new ArrayList();
        numberList.add(1);
        numberList.add(2);
        numberList.add(5);
        numberList.add(3);
        Collections.sort(numberList);
        String numbers = org.apache.commons.lang.StringUtils.join(numberList.toArray(), "/");
        System.out.println(numbers);

        System.out.println(new Date());
    }



}
