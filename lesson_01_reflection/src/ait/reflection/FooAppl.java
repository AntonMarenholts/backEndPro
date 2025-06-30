package ait.reflection;

import ait.reflection.infra.FooUtils;

public class FooAppl {
    public static void main(String[] args) {
        if (args.length != 2) {
            System.out.println("Wrong number of arguments");
            return;
        }
        FooUtils.caller(args[0], args[1]);

    }
}
