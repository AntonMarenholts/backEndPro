package ait.reflection;

public class reflectionApp {
    public static void main(String[] args) throws ClassNotFoundException, InstantiationException, IllegalAccessException {

        Class<String> clazzStr1 = String.class;

        String str = "Hello";
        Class<? extends String> clazzStr2 = str.getClass();

        Class<?> clazzStr3 = Class.forName("java.lang.String");

        System.out.println(clazzStr1 == clazzStr2);
        System.out.println(clazzStr1 == clazzStr3);

        String str1 = (String) clazzStr3.newInstance();

        Class<?> clazzInt = int.class;
        Class<?> clazzInteger = Integer.class;
        System.out.println(clazzInteger == clazzInt);

        Class<?> clazz = void.class;

    }
}
