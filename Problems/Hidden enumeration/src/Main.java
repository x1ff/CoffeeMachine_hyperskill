public class Main {

    public static void main(String[] args) {
        // write your program here
        System.out.println();
        int count = 0;
        for (Secret status : Secret.values()) {
            if (status.name().startsWith("STAR")) {
                count++;
            }
        }
        System.out.println(count);
    }
}
/*
   enum Secret {
    STAR, CRASH, START, // ...
}
*/