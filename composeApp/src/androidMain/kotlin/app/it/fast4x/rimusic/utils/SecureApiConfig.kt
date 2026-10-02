package app.it.fast4x.rimusic.utils

import app.kreate.android.BuildConfig

object SecureApiConfig {

    private const val NOTIFICATION_JSON_URL =
        "https://raw.githubusercontent.com/nobrainghost/gheiem/main/cubic_music_notifications.json"

    private fun reveal(vararg fragments: String): String =
        fragments.joinToString(separator = "") { it.reversed() }

    val cubicNotificationConfigUrl: String
        get() = NOTIFICATION_JSON_URL

    val cubicNotificationConfigFallbackUrl: String by lazy {
        "https://raw.githubusercontent.com/cybruGhost/ghoststuff/main/msikdf.json"
    }

    val cubicSystemNotificationConfigUrl: String
        get() = NOTIFICATION_JSON_URL

    val ytmSessionOrigin: String by lazy {
        reveal("//:sptth", "-eikooc-mty", ".asomas", ".elbavol", "ppa")
    }

    val ytmSessionEndpoint: String
        get() = ytmSessionOrigin + "/api/ytm-session"

    private val updateServiceBaseUrl: String by lazy {
        reveal("//:sptth", "-etadpu", "-hasilem", "eroc", ".elbavol.", "ppa")
    }

    val updateBuddyLatestReleaseEndpoint: String by lazy {
        updateServiceBaseUrl + "/api/public/latest-release"
    }

    val updateBuddyGithubReleaseEndpoint: String by lazy {
        updateServiceBaseUrl + "/api/public/github-release"
    }

    val updateBuddyReportErrorEndpoint: String by lazy {
        updateServiceBaseUrl + "/api/public/report-error"
    }

    val githubLatestFullApkUrl: String by lazy {
        "https://github.com/nitishco50/Cubic-Music/releases/latest/download/Allomusic-full.apk"
    }

    val crystalApiBaseUrl: String by lazy {
        "https://v0-innertube-api-clone.vercel.app/api"
    }

    val cShareEndpoint: String by lazy {
        updateServiceBaseUrl + "/api/public/share"
    }

    val supportReportEndpoint: String by lazy {
        BuildConfig.SUPPORT_API_ENDPOINT.trim().takeIf { it.isNotBlank() }
            ?: reveal("//:sptth", "ebuceht", "ppa.elbavol.", "troppus/cilbup/ipa/")
    }

    val spotifyCanvasApi: String by lazy {
        reveal("tops//:sptth", "ammag-ipayfi", "/ppa.lecrev.", "savnac/ipa")
    }

    val spotifyMatchApi: String by lazy {
        reveal("ynhs//:sptth", "foepzltvojod", "sabapus.qgtz", "oitcnuf/oc.e", "fitops/1v/sn", "hctam-y")
    }

    val spotifyMatchApiKey: String
        get() = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InNobnlkb2pvdnRsenBlb2Z6dGdxIiwicm9sZSI6ImFub24iLCJpYXQiOjE3Njk2NzYwNzQsImV4cCI6MjA4NTI1MjA3NH0.LzbV-bA7YLpGAG-LYvrBKmnkVe-__NDluSHSXcgt0OE".trim()

    val spotifySecretsUrl: String by lazy {
        reveal(
            ".war//:sptth", "ocresubuhtig", "yx/moc.tnetn", "tops/ekalfol", "/og-sterces-",
            "m/sdaeh/sfer", "/sterces/nia", "j.tciDterces", "nos"
        )
    }

    val spotifyServerTimeUrl: String by lazy {
        reveal("nepo//:sptth", "moc.yfitops.", "-revres/ipa/", "emit")
    }

    val spotifyTokenUrl: String by lazy {
        reveal("nepo//:sptth", "moc.yfitops.", "nekot/ipa/")
    }

    val spotifyWebAccessTokenUrl: String by lazy {
        reveal("nepo//:sptth", "moc.yfitops.", "_ssecca_teg/", "nekot")
    }

    val spotifySearchUrl: String by lazy {
        reveal(".ipa//:sptth", "/moc.yfitops", "hcraes/1v")
    }

    val shazamBaseUrl: String by lazy {
        reveal(".pma//:sptth", "/moc.mazahs")
    }

    val shazamProxyUrl: String by lazy {
        reveal("vfxx//:sptth", "dylniimcuzga", "sabapus.dhnc", "oitcnuf/oc.e", "mazahs/1v/sn", "yxorp-")
    }

    val shazamProxyApiKey: String
        get() = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6Inh4ZnZhZ3p1Y21paW5seWRjbmhkIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NzU3MzQ2MjAsImV4cCI6MjA5MTMxMDYyMH0.-MOJ_ygNoiiYDXXR-J9c-HsH5GLw49aJG6rb12QxIUU".trim()

    val weatherConfigUrl: String by lazy {
        reveal("tsez//:sptth", "8e-kivodem-y", "yfilten.24f8", "kmatnaw/ppa.", ".ehehukisali", "nosj")
    }

    val weatherApiKey: String
        get() = "5174a4c980abc22f0dc589db984742cf".trim()

    val weatherApiBaseUrl: String by lazy {
        reveal(".ipa//:sptth", "mrehtaewnepo", "/atad/gro.pa", "rehtaew/5.2")
    }

    val ipInfoUrl: String by lazy {
        reveal("nipi//:sptth", "/nosj/oi.of")
    }

    fun resolveOmadaSearchApi(): String =
        BuildConfig.OMADA_API.ifBlank { "https://yt.omada.cafe/api/v1/search" }
}
