import java.util.stream.*;
public class java520{
    public static void main(String[] args) {
        IntStream.iterate(15,i -> i-1)
        .limit(30)
        .mapToObj(y ->
        String.join(
        "",
        IntStream.range(-30,30)
        .mapToObj(x ->
        Math.pow(Math.pow(x * 0.05,2) + Math.pow(y * 0.1,2) -1,3)
        -Math.pow(x*0.05,2) * Math.pow(y*0.1,3)<=0
        ?"I Love U PCL ".split("")[Math.floorMod(x-y, 13)]:" "
        		)
        		.collect(Collectors.toList())
        	)
        )
        .forEach(System.out::println);
    }
}