package rtti;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

class Base {
    private int b = 0;
    public Base() {

    }

    public void doTask() {
        System.out.println("Base doTask.");
    }
}

class Derived1 extends Base {
    public Derived1() {

    }
}

class Derived2 extends Base {
    public Derived2() {

    }
}

class CTest {
    
}

public class AppTest 
{
    /**
     * Rigorous Test :-)
     */
    @Test
    public void onTest()
    {
        Derived1 d = new Derived1();
        Base b = d;
        b.doTask();

        // A compile error, it's not a kind of RTTI
        //CTest c = (CTest)b;

        // RTTI: downcast
        try {
            System.out.println("Cast base to Derived2.");
            Derived2 d2 = (Derived2)b;
        }
        catch (ClassCastException exp) {
            System.out.println("ClassCastException to Derived2.");
        }

        try {
            System.out.println("Cast base to Derived1.");
            Derived1 d1 = (Derived1)b;
        }
        catch (ClassCastException exp) {
            System.out.println("ClassCastException to Derived1.");
        }
    }
}
