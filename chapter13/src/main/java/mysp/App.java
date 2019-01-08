package mysp;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println("Current working on: " + System.getProperty("user.dir"));
        HexLoader loader = new HexLoader();
        String hex_data = loader.loadHexFile(".project");
        System.out.print(hex_data);
    }
}
