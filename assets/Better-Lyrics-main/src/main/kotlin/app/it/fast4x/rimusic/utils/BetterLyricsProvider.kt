package app.it.fast4x.rimusic.utils

import io.ktor.client.HttpClient
import io.ktor.client.engine.okhttp.OkHttp
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.bodyAsText
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.contentOrNull
import kotlinx.serialization.json.jsonObject
import kotlinx.serialization.json.jsonPrimitive
import org.w3c.dom.Element
import java.io.ByteArrayInputStream
import javax.xml.parsers.DocumentBuilderFactory

data class BetterLyricsResult(
    val plainLyrics: String? = null,
    val syncedLyrics: String? = null,
)

object BetterLyricsProvider {
    private const val ENDPOINT = "https://api.betterlyrics.org/getLyrics"

    private val json = Json { ignoreUnknownKeys = true }

    private val client by lazy {
        HttpClient(OkHttp)
    }

    @Suppress("UNUSED_PARAMETER")
    suspend fun lyrics(
        title: String,
        artist: String,
        album: String?,
        durationSeconds: Int,
    ): BetterLyricsResult? {
        if (title.isBlank() || artist.isBlank()) return null
        return runCatching {
            val body = client.get(ENDPOINT) {
                parameter("s", title)
                parameter("a", artist)
            }.bodyAsText()
            val ttml = json.parseToJsonElement(body)
                .jsonObject["ttml"]
                ?.jsonPrimitive
                ?.contentOrNull
            if (ttml.isNullOrBlank()) null else render(ttml)
        }.getOrNull()
    }

    private fun render(ttml: String): BetterLyricsResult? {
        val document = DocumentBuilderFactory.newInstance()
            .newDocumentBuilder()
            .parse(ByteArrayInputStream(ttml.toByteArray(Charsets.UTF_8)))

        val paragraphs = document.getElementsByTagName("p")
        val synced = StringBuilder()
        val plain = StringBuilder()
        var lineCount = 0

        for (i in 0 until paragraphs.length) {
            val paragraph = paragraphs.item(i) as? Element ?: continue
            val beginMs = parseTime(paragraph.getAttribute("begin")) ?: continue
            val lineText = paragraph.textContent
                .replace(Regex("""\s+"""), " ")
                .trim()
            if (lineText.isBlank()) continue

            val words = mutableListOf<WordTiming>()
            val spans = paragraph.getElementsByTagName("span")
            for (j in 0 until spans.length) {
                val span = spans.item(j) as? Element ?: continue
                val startMs = parseTime(span.getAttribute("begin")) ?: continue
                val endMs = parseTime(span.getAttribute("end")) ?: (startMs + 1)
                val word = span.textContent.replace(Regex("""\s+"""), " ").trim()
                if (word.isBlank()) continue
                words += WordTiming(word, startMs, endMs)
            }

            synced.append(formatTimestamp(beginMs)).append(lineText).append('\n')
            if (words.isNotEmpty()) {
                synced.append('<')
                words.forEachIndexed { index, timing ->
                    if (index > 0) synced.append('|')
                    synced.append(timing.word)
                        .append(':').append(formatSeconds(timing.startMs))
                        .append(':').append(formatSeconds(timing.endMs))
                }
                synced.append(">\n")
            }
            plain.append(lineText).append('\n')
            lineCount++
        }

        if (lineCount == 0) return null
        return BetterLyricsResult(
            plainLyrics = plain.toString().trimEnd('\n').ifBlank { null },
            syncedLyrics = synced.toString().trimEnd('\n').ifBlank { null },
        )
    }

    private fun parseTime(raw: String?): Long? {
        if (raw.isNullOrBlank()) return null
        val value = raw.trim()
        return try {
            if (value.contains(':')) {
                var seconds = 0.0
                for (part in value.split(':')) {
                    seconds = seconds * 60 + (part.toDoubleOrNull() ?: return null)
                }
                (seconds * 1000).toLong()
            } else {
                (value.toDoubleOrNull() ?: return null).let { (it * 1000).toLong() }
            }
        } catch (_: Exception) {
            null
        }
    }

    private fun formatTimestamp(ms: Long): String {
        val cs = ms / 10
        val minutes = cs / 6000
        val seconds = (cs / 100) % 60
        val centis = cs % 100
        return "[%02d:%02d.%02d]".format(minutes, seconds, centis)
    }

    private fun formatSeconds(ms: Long): String = (ms / 1000.0).toString()

    private data class WordTiming(val word: String, val startMs: Long, val endMs: Long)
}
