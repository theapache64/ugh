package com.theah64.ugh

enum class CommitType(
    val emojiCode: String,
    val type: String,
    val description: String,
    val emoji: String,
    val keywords: Array<String>? = null
) {

    // QUICK COMMIT TYPES
    BACKUP(
        ":arrow_backward:",
        "backup",
        "Backup source code",
        "◀️",
        arrayOf("backup")
    ),


    // STANDARD COMMIT TYPES
    NEW(
        ":star:",
        "new",
        "Add new feature",
        "🌟",
        arrayOf("added")
    ),

    BUG(
        ":bug:",
        "bug",
        "Fix bug issue",
        "🐛",
        arrayOf("fixed")
    ),

    CRITICAL_BUG(
        ":ambulance:",
        "critical-bug",
        "Critical hotfix bug issue",
        "🚑"
    ),
    SECURITY(
        ":lock:",
        "security",
        "Fix security issue",
        "🔒"
    ),
    PERFORMANCE(
        ":chart_with_upwards_trend:",
        "performance",
        "Fix performance issue",
        "📈"
    ),
    IMPROVEMENT(
        ":zap:",
        "improvement",
        "Update backwards-compatible feature",
        "⚡️"
    ),
    BREAKING(
        ":boom:",
        "breaking",
        "Update backwards-incompatible feature",
        "💥"
    ),
    DEPRECATED(
        ":warning:",
        "deprecated",
        "Deprecate feature",
        "⚠️"
    ),
    I18N(
        ":globe_with_meridians:",
        "i18n",
        "Update or fix internationalization",
        "🌐"
    ),
    A11Y(
        ":wheelchair:",
        "a11y",
        "Update or fix accessibility",
        "♿️"
    ),
    UPDATE_UI(
        ":lipstick:",
        "ui-update",
        "Update UI/Cosmetic",
        "💄"
    ),
    UPDATE(
        ":up:",
        "update",
        "Update other",
        "🆙"
    ),
    REFACTOR_NON_CODE(
        ":rotating_light:",
        "non-code-refactor",
        "Remove linter/strict/deprecation warnings",
        "🚨"
    ),
    REFACTOR_CODE(
        ":shirt:",
        "code-refactor",
        "Refactoring or code layouting",
        "👕"
    ),
    TEST_ADD(
        ":white_check_mark:",
        "test-add",
        "Add tests",
        "☑️"
    ),
    TEST_FIX(
        ":green_heart:",
        "test-fix",
        "Fix tests failure or CI building",
        "💚"
    ),
    DOCS_UPDATE(
        ":pencil:",
        "docs-update",
        "Update documentation",
        "📝"
    ),
    LICENCE(
        ":copyright:",
        "licence",
        "Decide or change license",
        "©"
    ),
    EXAMPLE(
        ":lollipop:",
        "example",
        "For example or demo codes",
        "🍭"
    ),
    DEPENDENCY_UPGRADE(
        ":arrow_up:",
        "dependency-up",
        "Upgrade dependencies",
        "⬆️"
    ),
    DEPENDENCY_DOWNGRADE(
        ":arrow_down:",
        "dependency-down",
        "Downgrade dependencies",
        "⬇️"
    ),
    DEPENDENCY_PIN(
        ":pushpin:",
        "dependency-pin",
        "Pin dependencies",
        "📌"
    ),
    CONFIG(
        ":wrench:",
        "config",
        "Update configuration",
        "🔧"
    ),
    BUILD(
        ":package:",
        "build",
        "Packaging or bundling or building",
        "📦"
    ),
    RELEASE_INIT(
        ":hatching_chick:",
        "release-init",
        "Initial commit",
        "🐣"
    ),
    RELEASE_MAJOR(
        ":confetti_ball:",
        "release-major",
        "Release major version",
        "🎊"
    ),
    RELEASE_MINOR(
        ":tada:",
        "release-minor",
        "Release minor version",
        "🎉"
    ),
    RELEASE_PATCH(
        ":sparkles:",
        "release-patch",
        "Release patch version",
        "✨"
    ),
    RELEASE_PRODUCTION(
        ":rocket:",
        "release-production",
        "Deploy to production enviroment",
        "🚀"
    ),
    RELEASE_BOOKMARK(
        ":bookmark:",
        "release-bookmark",
        "Tagged with version label",
        "🔖"
    ),
    REVERT(
        ":back:",
        "revert",
        "Revert commiting",
        "🔙"
    ),
    WIP(
        ":construction:",
        "wip",
        "WIP commiting",
        "🚧"
    ),
    MOVE(
        ":truck:",
        "move",
        "Move or rename files, repository, ...",
        "🚚"
    ),
    MERGE(
        ":twisted_rightwards_arrows:",
        "merge",
        "Merge conflict resolution",
        "🔀"
    ),
    ADD(
        ":heavy_plus_sign:",
        "add",
        "Add files, dependencies, ...",
        "➕"
    ),
    REMOVE(
        ":heavy_minus_sign:",
        "remove",
        "Remove files, dependencies, ...",
        "➖"
    ),
    ENABLE(
        ":on:",
        "enable",
        "Enable feature and something ...",
        "🔛"
    );

    override fun toString(): String {
        return "$emoji : $type : $description"
    }

    init {
        keywords?.let {
            it.forEachIndexed { index, keyword ->
                keywords[index] = keyword.toLowerCase()
            }
        }
    }

}