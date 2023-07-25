package registry

interface Registry<K, V> {

    val registry: MutableMap<K, V>

    fun registerAll()
    fun register(k: K, v: V) {
        registry[k] = v
    }

    fun getOrNull(k: K): V? {
        return registry[k]
    }

}