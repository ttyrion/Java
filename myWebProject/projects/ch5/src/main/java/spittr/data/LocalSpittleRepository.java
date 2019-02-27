package spittr.data;

import java.util.List;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import spittr.Spittle;

//会由RootConfig.class启动组件扫描自动装配此bean
@Component
public class LocalSpittleRepository implements SpittleRepository {
    public List<Spittle> findSpittles() {
        List<Spittle> spittles = new ArrayList<>();
        spittles.add(new Spittle("It's a problem.", new GregorianCalendar(2014, 2, 11).getTime()));
        spittles.add(new Spittle("Hello.", new GregorianCalendar(2015, 3, 11).getTime()));
        return spittles;
    }
}