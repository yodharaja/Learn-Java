package chapter6;

/**
 * ============================================================
 * LESSON 6.2 — Method Overriding & Runtime Polymorphism
 * ============================================================
 *
 * METHOD OVERRIDING occurs when a subclass provides its own specific
 * implementation for a method already defined in its superclass.
 *
 * Rules for Method Overriding:
 *   1. Must have the EXACT SAME name, return type (or covariant subtype), and parameter list.
 *   2. Access modifier cannot be more restrictive than the superclass method
 *      (e.g., if parent is public, child cannot be protected or private).
 *   3. Final, static, and private methods CANNOT be overridden.
 *   4. Always use `@Override` annotation:
 *      - Helps compiler catch typos (e.g. wrong parameter types).
 *
 * Overriding vs Overloading:
 * ┌─────────────────┬────────────────────────────────┬───────────────────────────────┐
 * │ Feature         │ Method Overloading             │ Method Overriding             │
 * ├─────────────────┼────────────────────────────────┼───────────────────────────────┤
 * │ Location        │ Same class                     │ Child class overriding parent │
 * │ Parameters      │ MUST be different              │ MUST be identical             │
 * │ Return Type     │ Can be different               │ Must be same (or covariant)   │
 * │ Binding Time    │ Compile-time (Static binding)  │ Runtime (Dynamic binding)     │
 * └─────────────────┴────────────────────────────────┴───────────────────────────────┘
 */

class Animal {
    protected String name;

    public Animal(String name) {
        this.name = name;
    }

    public void makeSound() {
        System.out.println("  [Animal] Some generic animal sound...");
    }

    public void sleep() {
        System.out.println("  " + name + " is sleeping peacefully 💤");
    }
}

class Dog extends Animal {
    public Dog(String name) {
        super(name);
    }

    // Overriding makeSound() with dog-specific behavior
    @Override
    public void makeSound() {
        System.out.println("  🐕 " + name + " says: Woof! Woof!");
    }

    // Dog-specific method
    public void fetch() {
        System.out.println("  🐕 " + name + " fetches the ball happily!");
    }
}

class Cat extends Animal {
    public Cat(String name) {
        super(name);
    }

    // Overriding makeSound() with cat-specific behavior
    @Override
    public void makeSound() {
        System.out.println("  🐈 " + name + " says: Meow~ Meow~");
    }
}

class Cow extends Animal {
    public Cow(String name) {
        super(name);
    }

    @Override
    public void makeSound() {
        System.out.println("  🐄 " + name + " says: Moooo!");
    }
}

public class MethodOverriding {

    public static void main(String[] args) {
        System.out.println("=== 1. DIRECT INVOCATION ===");
        Dog myDog = new Dog("Buddy");
        Cat myCat = new Cat("Whiskers");
        Cow myCow = new Cow("Bessie");

        myDog.makeSound();
        myCat.makeSound();
        myCow.makeSound();

        System.out.println("\n=== 2. RUNTIME POLYMORPHISM (DYNAMIC METHOD DISPATCH) ===");
        // A superclass reference can point to ANY subclass object!
        // At runtime, Java determines the exact method to call based on the ACTUAL object in memory.
        Animal[] zoo = {
            new Dog("Rex"),
            new Cat("Luna"),
            new Cow("Daisy"),
            new Animal("Mystery Creature")
        };

        for (Animal animal : zoo) {
            System.out.print("Zoo member sound -> ");
            animal.makeSound(); // Dynamic dispatch determines correct version at runtime!
            animal.sleep();
        }

        // ============================================================
        // KEY TAKEAWAYS:
        // ============================================================
        // - Overriding redefines parent behavior in child class.
        // - `@Override` annotation ensures compiler verification.
        // - Dynamic Method Dispatch resolves the method based on object type at runtime.
        // ============================================================
    }
}
