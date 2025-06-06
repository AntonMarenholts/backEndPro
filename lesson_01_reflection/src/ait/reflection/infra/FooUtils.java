package ait.reflection.infra;

import ait.reflection.model.Foo;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;

public class FooUtils {
    public static void caller(String functionName, String arg){

        Class<Foo> clazz = Foo.class;
        try {
            Method method = clazz.getDeclaredMethod(functionName, String.class);
            method.setAccessible(true);

//            method.invoke(new Foo(), arg);

            Constructor constructor = clazz.getDeclaredConstructor();
            method.invoke(clazz.newInstance(), arg);

        } catch (Exception e) {
            System.out.println(functionName + " doesn't exists");;
        }


//        Foo foo = new Foo();
//        switch (functionName){
//            case "f1":
//                foo.f1(arg);
//                break;
//            case "f2":
//                foo.f2(arg);
//                break;
//            case "f3":
//                foo.f3(arg);
//                break;
//
//            default:
//                System.out.println(functionName + " doesn't exists");
//        }
    }
}
