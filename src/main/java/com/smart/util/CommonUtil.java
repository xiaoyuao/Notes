package com.smart.util;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Created by admin on 2018/11/14.
 */
public class CommonUtil {
    public static void sortAndJoint(){
        List numberList = new ArrayList();
        numberList.add(1);
        numberList.add(2);
        numberList.add(5);
        numberList.add(3);
        Collections.sort(numberList);
        String numbers = org.apache.commons.lang.StringUtils.join(numberList.toArray(), "/");
        System.out.println(numbers);
    }
    public  void mapCompare() {
        try {
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd");
            ConcurrentHashMap<String, CPLInfo> hm = new ConcurrentHashMap();
            CPLInfo ci1 = new CPLInfo();
            ci1.setUUID("1");
            ci1.setUploadTime(sf.parse("2018-01-01"));
            hm.put(ci1.getUUID(), ci1);

            CPLInfo ci2 = new CPLInfo();
            ci2.setUUID("2");
            ci2.setUploadTime(sf.parse("2017-01-01"));
            hm.put(ci2.getUUID(), ci2);

            CPLInfo ci3 = new CPLInfo();
            ci3.setUUID("3");
            ci3.setUploadTime(sf.parse("2019-01-01"));
            hm.put(ci3.getUUID(), ci3);

            CPLInfo ci4 = new CPLInfo();
            ci4.setUUID("4");
            ci4.setUploadTime(sf.parse("2020-01-01"));
            hm.put(ci4.getUUID(), ci4);

            try {
                LinkedHashMap<String, CPLInfo> collect1 = hm.entrySet().stream().sorted(new Comparator<Map.Entry<String, CPLInfo>>() {
                    @Override
                    public int compare(Map.Entry<String, CPLInfo> o1, Map.Entry<String, CPLInfo> o2) {
                        //  return Integer.valueOf(o1.getValue().getAge()).compareTo(Integer.valueOf(o2.getValue().getAge()));
                        if (o1.getValue().getUploadTime() == null && o2.getValue().getUploadTime() == null) {
                            return 0;
                        }
                        if (o1.getValue().getUploadTime() == null) {
                            return 1;
                        }
                        if (o2.getValue().getUploadTime() == null) {
                            return -1;
                        }
                        if (o1.getValue().getUploadTime().before(o2.getValue().getUploadTime())) {
                            return 1;
                        }
                        if (o1.getValue().getUploadTime().after(o2.getValue().getUploadTime())) {
                            return -1;
                        }
                        return 0;
                    }
                }).collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue(), (e1, e2) -> e2, LinkedHashMap::new));

                System.out.println("hehehhe");
                for (CPLInfo c : collect1.values()) {
                    System.out.println(sf.format(c.getUploadTime()));
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        CommonUtil c = new CommonUtil();
        c.mapCompare();
    }




    private  class CPLInfo{
        private String UUID;
        private Date uploadTime;

        public String getUUID() {
            return UUID;
        }

        public void setUUID(String UUID) {
            this.UUID = UUID;
        }

        public Date getUploadTime() {
            return uploadTime;
        }

        public void setUploadTime(Date uploadTime) {
            this.uploadTime = uploadTime;
        }
    }

}

