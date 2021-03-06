package io.kotlintest

import io.kotlintest.extensions.Extension

/**
 * The description gives the full path to a [TestScope].
 *
 * It contains the name of every parent, with the root at index 0.
 * And it includes the name of the test scope it represents.
 *
 * This is useful when you want to write generic [Extension]s and you
 * need to be able to filter on certain tests only.
 *
 * @param parents each parent scope
 * @param name the name of this scope
 */
data class Description(val parents: List<String>, val name: String) {

  fun append(name: String) =
      Description(this.parents + listOf(this.name), name)

  fun hasParent(description: Description): Boolean = parents.containsAll(description.parents + listOf(description.name))

  fun fullName(): String = (parents + listOf(name)).joinToString(" ")

  /**
   * Returns a String version of this description, which is
   * the parents + this name concatenated with slashes.
   */
  fun id(): String = (parents + listOf(name)).joinToString("/")
}