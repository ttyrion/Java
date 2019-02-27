package spittr.data;
import java.util.List;
import spittr.Spittle;

public interface SpittleRepository {
    public List<Spittle> findSpittles();
}