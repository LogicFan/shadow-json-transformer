package com.github.logicfan.gradle.shadow.transformers

import com.github.jengelman.gradle.plugins.shadow.transformers.Transformer
import com.github.jengelman.gradle.plugins.shadow.transformers.TransformerContext
import org.gradle.api.file.FileTreeElement
import org.gradle.api.specs.Spec
import org.gradle.api.tasks.util.PatternFilterable
import org.gradle.api.tasks.util.PatternSet
import org.gradle.internal.impldep.com.google.gson.JsonElement
import shadow.org.apache.tools.zip.ZipOutputStream;

class JsonTransformer implements Transformer, PatternFilterable {
    private PatternSet patternSet = new PatternSet().include("**")
    private Map<String, JsonElement> jsonFiles = [:]

    // === Transformer ===
    @Override
    boolean canTransformResource(FileTreeElement element) {
        return false
    }

    @Override
    void transform(TransformerContext context) {

    }

    @Override
    boolean hasTransformedResource() {
        return false
    }

    @Override
    void modifyOutputStream(ZipOutputStream zipOutputStream, boolean b) {

    }

    @Override
    Set<String> getIncludes() {
        return patternSet.includes
    }

    @Override
    Set<String> getExcludes() {
        return patternSet.excludes
    }

    @Override
    PatternFilterable setIncludes(Iterable<String> includes) {
        patternSet.includes = includes
        return this
    }

    @Override
    PatternFilterable setExcludes(Iterable<String> excludes) {
        patternSet.excludes = excludes;
        return this
    }

    @Override
    PatternFilterable include(String... includes) {
        patternSet.include(includes)
        return this
    }

    @Override
    PatternFilterable include(Iterable<String> includes) {
        patternSet.include(includes)
        return this
    }

    @Override
    PatternFilterable include(Spec<FileTreeElement> includeSpec) {
        patternSet.include(includeSpec)
        return this
    }

    @Override
    PatternFilterable include(Closure includeSpec) {
        patternSet.include(includeSpec)
        return this
    }

    @Override
    PatternFilterable exclude(String... excludes) {
        patternSet.exclude(excludes)
        return this
    }

    @Override
    PatternFilterable exclude(Iterable<String> excludes) {
        patternSet.exclude(excludes)
        return this
    }

    @Override
    PatternFilterable exclude(Spec<FileTreeElement> excludeSpec) {
        patternSet.exclude(excludeSpec)
        return this
    }

    @Override
    PatternFilterable exclude(Closure excludeSpec) {
        patternSet.exclude(excludeSpec)
        return this
    }
}
