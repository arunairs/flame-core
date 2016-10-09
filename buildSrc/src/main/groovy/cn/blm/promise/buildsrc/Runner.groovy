package cn.blm.promise.buildsrc

/**
 * @author jiaan.zhang@oracle.com
 * @date 27/09/2016 12:18 AM
 */
class Runner {
    public static fail(String message) {
        println message
        throw new RuntimeException(message)
    }
}
