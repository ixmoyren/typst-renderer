package com.github.pndv.typstrenderer.lsp

import com.github.pndv.typstrenderer.language.TypstFileType
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.lsp.api.customization.LspCustomization
import com.intellij.platform.lsp.api.customization.LspFormattingSupport

/**
 * Customizes LSP feature support for the Tinymist language server.
 *
 * Most features are enabled by default in [LspCustomization]:
 * - Go to Definition, Go to Type Definition
 * - Hover documentation
 * - Code completion
 * - Diagnostics (errors/warnings)
 * - Find References
 * - Code Actions (quick fixes)
 * - Semantic Tokens (syntax highlighting)
 * - Code Folding
 * - Inlay Hints
 * - Document Links
 *
 * Only formatting is customised here to ensure tinymist always handles
 * formatting for Typst files, regardless of whether the IDE has its own formatter.
 */
class TinymistCustomisation : LspCustomization() {

    override val formattingCustomizer = object : LspFormattingSupport() {
        override fun shouldFormatThisFileExclusivelyByServer(
            file: VirtualFile,
            ideCanFormatThisFileItself: Boolean,
            serverExplicitlyWantsToFormatThisFile: Boolean,
        ): Boolean {
            // Always let tinymist handle formatting for Typst files
            return file.fileType == TypstFileType
        }
    }
}

