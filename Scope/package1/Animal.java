package Scope.package1;

/*
 * public > protected > default > private
 * 
 * everywhere 
 * > (anywhere in same package + via inheritence in outside package)
 * > (inside only the same package)
 * > (only inside the class)
 */
public class Animal {
    // public, default, protected, private
    protected int ears = 2;

    public void bark() {
        System.out.println("baaark with " + ears + " ears");
    }
}

class Dog extends Animal {
    protected int ears = 0;
    Animal an = new Animal();

    Dog() {
        // same package + inheritance
        this.ears = super.ears;
        // same package + no inheritance
        int x = an.ears;
    }
}
