package img;

import java.net.URL;

public class Img {
    public static void thing() {
        URL url = Img.class.getResource("/board.jpg");
        System.out.println("url="+url);
        Img i = new Img();
        url = i.getClass().getResource("/board.jpg");
        System.out.println("url2="+url);
        url = i.getClass().getResource("board.jpg");
        System.out.println("url3="+url);
    }
}
