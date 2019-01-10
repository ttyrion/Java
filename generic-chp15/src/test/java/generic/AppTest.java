package generic;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

class TwoTuple<T1, T2> {
    public final T1 first;
    public final T2 second;

    TwoTuple(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }
}

class ThreeTuple<T1, T2, T3> {
    public final T1 first;
    public final T2 second;
    public final T3 third;

    ThreeTuple(T1 first, T2 second, T3 third) {
        this.first = first;
        this.second = second;
        this.third = third;
    }
}

class TupleGenerator {
    // 在返回值前面声明泛型参数（显然的，因为返回值可能就要用到泛型参数）
    public static <T1,T2> TwoTuple<T1,T2> getTuple(T1 first, T2 second) {
        return new TwoTuple<T1,T2>(first, second);
    }

    public static <T1,T2,T3> ThreeTuple<T1,T2,T3> getTuple(T1 first, T2 second, T3 third) {
        return new ThreeTuple<T1,T2,T3>(first, second, third);
    }
}

public class AppTest 
{
    @Test
    public void onTest()
    {
        TwoTuple<String, Integer> info = TupleGenerator.getTuple("Jack", 25);
        System.out.println(info.first + " is " + info.second + " years old.");
    }
}
