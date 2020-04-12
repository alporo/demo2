package com.example.demo2

import org.springframework.util.AntPathMatcher

class VersionedAntPathMatcher : AntPathMatcher() {
    private val regex = "/api/(?<version>v\\d{1,2}|latest)/.*".toRegex()

    override fun doMatch(pattern: String, path: String?, fullMatch: Boolean, uriTemplateVariables: MutableMap<String, String>?): Boolean =
            if (path == null || !regex.matches(pattern) || !regex.matches(path))
                super.doMatch(pattern, path, fullMatch, uriTemplateVariables)
            else doVersionedMatch(pattern, path, fullMatch, uriTemplateVariables)

    private fun doVersionedMatch(pattern: String, path: String, fullMatch: Boolean, uriTemplateVariables: MutableMap<String, String>?): Boolean =
            version(path) >= version(pattern) && super.doMatch(wildcardVersionApi(pattern), path, fullMatch, uriTemplateVariables)

    private fun version(s: String) = versionStringToInt(versionString(s))
    private fun versionStringToInt(s: String) = if (s == "latest") Int.MAX_VALUE else s.substring(1).toInt()
    private fun versionString(s: String) = regex.find(s)?.groups?.get(1)?.value!!

    private fun wildcardVersionApi(s: String) = s.replaceFirst(versionString(s), "*")

    override fun getPatternComparator(path: String): Comparator<String> =
            (Comparator<String> { p1, p2 ->
                if (!regex.matches(path) || !regex.matches(p1) || !regex.matches(p2)) 0
                else compare(version(path), version(p1), version(p2))
            }).thenComparing(super.getPatternComparator(path))

    private fun compare(pathv: Int, pv1: Int, pv2: Int): Int =
            when {
                pathv == pv1 && pathv == pv2 -> 0
                pathv == pv1 && pathv != pv2 -> -1
                pathv != pv1 && pathv == pv2 -> 1
                else -> pv2 - pv1
            }
}