public class Val {
    private final int v;
    public Val(int v) { this.v = v; }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Val)) return false;
        Val val = (Val) o;
        return v == val.v;
    }
    @Override
    public int hashCode() { return Integer.hashCode(v); }
    @Override
    public String toString() { return "Val("+v+")"; }
}
