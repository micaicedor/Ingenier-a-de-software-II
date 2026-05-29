package com.example.pruebatecnica.utils

object VersionComparator {
    fun compare(localVersion: String, remoteVersion: String): Int {
        val localParts = localVersion.toVersionParts()
        val remoteParts = remoteVersion.toVersionParts()
        val maxSize = maxOf(localParts.size, remoteParts.size)

        for (index in 0 until maxSize) {
            val local = localParts.getOrElse(index) { 0 }
            val remote = remoteParts.getOrElse(index) { 0 }
            if (local != remote) return local.compareTo(remote)
        }

        return 0
    }

    private fun String.toVersionParts(): List<Int> {
        return split(".", "-", "_")
            .mapNotNull { part -> part.filter { it.isDigit() }.toIntOrNull() }
            .ifEmpty { listOf(0) }
    }
}
