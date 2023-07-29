interface Runner {
    void run();
}

class Man {
    public Man() {
        System.out.println("constructor...");
    }

    static void walk() {
        System.out.println("static method...");
    }

    void moonWalk() {
        System.out.println("instance method...");
    }
}

class MethodReference {
    public static void main(String[] args) {
        Runner rn1 = Man::walk;
        Runner rn2 = new Man()::moonWalk;
        Runner rn3 = Man::new;
        rn1.run();
        rn2.run();
        rn3.run();

    }
}