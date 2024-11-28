MultiplatformModsDotGroovy.make {
    def modid = buildProperties["mod_id"]

    modLoader = "javafml"
    loaderVersion = libs.versions.get("javafml_range")

    license = buildProperties["mod_license"]
    issueTrackerUrl = buildProperties["mod_issues_url"]

    accessTransformers {
        accessTransformer("META-INF/accesstransformer.cfg")
    }

    mod {
        modId = modid
        displayName = buildProperties["mod_name"]
        authors = [(buildProperties["authors"] as String)]
        version = environmentInfo.version

        displayUrl = buildProperties["mod_display_url"]
        sourcesUrl = buildProperties["mod_source_url"]
        logoFile = "assets/${modid}/logo.png"
        description = buildProperties["mod_description"]

        dependencies {
            mod("neoforge") {
                versionRange = "${libs.versions.get("neoforge_range")}"
            }
        }
    }

    mixins {
        mixin("${modid}.mixins.json")
    }
}