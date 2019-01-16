package com.roc.demo.j2se.ot;

/**
 * 判断两个对象是否为同一个对象
 */
public class IsSameObjTest {

    public static void main(String[] args) {
        System.out.println("=======test begin...");
//        String a = new String("1");
//        String b = new String("1");
//        String a = "1";
//        String b = "1";
//        String a = "1" + "";    //编译器会将"1" + ""优化成"1"，所以和"1"是同一个对象
//        String b = "1";
//        String blank = "";
//        String a = "1" + blank; //换成变量，编译器就不能将"1" + ""优化成"1"
//        String b = "1";
        String one = "1";
        String a = one;
        String b = one;
        System.out.println("=======test finish=======IsSameObj1 = " + (a == b) +
                ", IsSameObj2 = " + a.equals(b) + ", a.hashCode() = " + a.hashCode() +
                ", b.hashCode() = " + b.hashCode());
    }

}
