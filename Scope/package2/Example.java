package Scope.package2;

import Scope.package1.Animal;

public class Example extends Animal {
    Animal an = new Animal();

    void trying() {
        // outside package + no inheritance
        int x = an.ears;
        // outside package + inheritance
        System.out.println(super.ears);
    }

    public static void main(String[] args) {

    }

}
