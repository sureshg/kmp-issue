package dev.suresh.routes

import dev.suresh.http.MediaApiClient
import dev.suresh.log.RespLogger
import io.github.oshai.kotlinlogging.KLogger
import io.github.oshai.kotlinlogging.KotlinLogging
import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.opentelemetry.instrumentation.annotations.WithSpan
import java.io.Writer

private val logger = KotlinLogging.logger {}

fun Routing.services() {

  get("/trace") {
    call.respond(
        mapOf("OpenTelemetry" to BuildConfig.otelInstr, "Image Size" to mediaApiCall().toString()))
  }
}

@WithSpan suspend fun mediaApiCall() = MediaApiClient().images().size

suspend fun ApplicationCall.respondLogStream(
    contentType: ContentType = ContentType.Text.EventStream,
    block:
        suspend context(KLogger)
        Writer.() -> Unit
) {
  respondTextWriter(contentType = contentType) { block(RespLogger(this, logger), this) }
}
