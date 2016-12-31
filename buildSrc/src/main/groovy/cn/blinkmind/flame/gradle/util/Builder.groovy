package cn.blinkmind.flame.gradle.util

/**
 * @author jiaan.zhang@outlook.com
 * @date 27/09/2016 12:18 AM
 */
class Builder {
    static fail(String message) {
        println message
        throw new RuntimeException(message)
    }
}
