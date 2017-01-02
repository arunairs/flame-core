package cn.blinkmind.flame.gradle.util

class Builder {
    static fail(String message) {
        println message
        throw new RuntimeException(message)
    }
}
