interface IPrinter {
    void print(long area);
}

class Square {
    long side;

    public Square(long side) {
        this.side = side;
    }

    public long area() {
        return side * side;
    }

    public void print(IPrinter printer) {
        printer.print(area());
    }
}

public class LambdaExample {
    public static void main(String[] args) {
        Square sq = new Square(10);
        sq.print((x) -> {
            System.out.println("area=" + x);
        });
    }
}
