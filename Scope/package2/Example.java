package Scope.package2;

import Scope.package1.Animal;

public class Example extends Animal {
    Animal an = new Animal();

    void trying() {
        int x = an.ears;
        System.out.println(super.ears);
    }

    public static void main(String[] args) {

    }

}
