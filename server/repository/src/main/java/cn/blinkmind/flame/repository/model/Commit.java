package cn.blinkmind.flame.repository.model;

public interface Commit<T> {
    String SN = "commitNumber";
    String VERSION = "commitVersion";

    Headers getHeaders();

    T getPayload();

    static boolean equals(Commit first, Commit second) {
        return first.getHeaders().getString(SN).equals(second.getHeaders().getString(SN));
    }
}
