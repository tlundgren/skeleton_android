package com.android.skeleton.crash

/**
 * Context data relevant for crash analytics.
 */
// The methods here are a meant as a sample of what a project may need.
// This architecture allows for decoupling from any one crash analytics provider when logging
// contextual data. An implementation could relay data to several providers transparently to users.
interface Context {
    fun add(key: String, value: String)
    fun add(key: String, value: Int)
    fun add(key: String, value: Boolean)
}