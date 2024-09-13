package consumer.specific;

import dev.lpa.generic.BaseClass;

public class ChildClass extends BaseClass {
    @Override
    protected void optionalMethod() {
        System.out.println("[ChildClass.optionalMethod]: Some EXTRA stuff going on here");
        super.optionalMethod();
    }

//    @Override
//    public void recommendedMethod() {
//        System.out.println("[ChildClass.recommendedMethod]: I'll do things my way");
//        optionalMethod();
//    }

    public static void recommendedStatic() {
        System.out.println("[ChildClass.recommendedStatic]: BEST way to so it");
        optionalStatic();
        //mandatoryStatic();
    }
}
