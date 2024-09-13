package dev.lpa;

import consumer.specific.ChildClass;
import dev.lpa.generic.BaseClass;
import external.Util.Logger;

public class Main {

    public static void main(String[] args) {
        BaseClass parent = new BaseClass();
        ChildClass child = new ChildClass();

        BaseClass childReferredToAsBase = new ChildClass();

        parent.recommendedMethod();
        callSeperator();
        childReferredToAsBase.recommendedMethod();
        callSeperator();
        child.recommendedMethod();

        callSeperator();
        parent.recommendedStatic();
        callSeperator();
        childReferredToAsBase.recommendedStatic();
        callSeperator();
        child.recommendedStatic();

        callSeperator();
        String xArgument = "This is all I can say about Section ";
        StringBuilder zArgument = new StringBuilder("Only saying this: Section");
        doXYZ(xArgument, 16, zArgument);
        System.out.println("After method call, xArgument = " + xArgument);
        System.out.println("After method call, zArgument = " + zArgument);

        callSeperator();
        StringBuilder tracker = new StringBuilder("Step 1 is abc");
        Logger.logToConsole(tracker.toString());
        tracker.append(" , Step-2 is xyz");
        Logger.logToConsole(tracker.toString());
        System.out.println("After Logging, tracker = " + tracker);
    }

    public static void callSeperator() {
        System.out.println("-".repeat(20));
    }

    private static void doXYZ(String x, int y, final StringBuilder z) {

        final String c = x + y;
        System.out.println("c = " + c);
        x = c;
        z.append(y);
    }
}
