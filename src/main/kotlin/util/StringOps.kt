package util

fun longestBetween(x: String, y: String): String {
    return if (x.length >= y.length) x else y
}