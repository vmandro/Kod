public class C {
    public static Object foo() {
        return new Val(10); // equal by value to A.foo() but different instance
    }

    public static Object goo(Object o) {
        if (o instanceof A) return ((A) o).goo();
        if (o instanceof B) return ((B) o).goo();
        return null;
    }
}