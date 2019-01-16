package com.roc.demo.j2se.itcastThread;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
public class CollectionModifyExceptionTest {
    public static void main(String[] args) {
//        Collection users = new ArrayList(); //ArrayList集合在迭代过程中，不能对其进行修改
        Collection users = new CopyOnWriteArrayList();

        users.add(new User("张三",28));
        users.add(new User("李四",25));
        users.add(new User("王五",31));
        Iterator itrUsers = users.iterator();
        while(itrUsers.hasNext()){
            System.out.println("aaaa");
            User user = (User)itrUsers.next();
            if("王五".equals(user.getName())){
                //ArrayList集合在迭代过程中，不能对其进行修改
                users.remove(user);
                //itrUsers.remove();
            } else {
                System.out.println(user);
            }
        }
    }
}
