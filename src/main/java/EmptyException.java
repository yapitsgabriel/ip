public class EmptyException extends Exception {
    static final String SPACE = "     ";
    static final String LINE = "    " + "______________________________________________________________";

    public EmptyException() {
        super();
        System.out.println(LINE);
        System.out.println(SPACE + "Oops! Remember to include the details for your item.");
        System.out.println(LINE);
    }
}
