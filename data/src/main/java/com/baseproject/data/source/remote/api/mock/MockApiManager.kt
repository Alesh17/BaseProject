package com.baseproject.data.source.remote.api.mock

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.Interceptor
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.Protocol
import okhttp3.RequestBody
import okhttp3.Response
import okhttp3.ResponseBody
import okhttp3.ResponseBody.Companion.toResponseBody
import okio.Buffer
import java.io.IOException

abstract class MockApiManager {

    open var delay = 1500L

    abstract fun getResponse(chain: Interceptor.Chain): Response

    inline fun <reified T> Interceptor.Chain.getBody(): T {
        val type = object : TypeToken<T>() {}.type
        val body = this.request().body.bodyToString()
        return Gson().fromJson(body, type)
    }

    fun String.createBody(): ResponseBody {
        val mediaType = "application/json".toMediaTypeOrNull()
        return this.toByteArray().toResponseBody(mediaType)
    }

    fun createResponse(chain: Interceptor.Chain, code: Int, message: String, body: ResponseBody): Response {
        return Response.Builder()
            .request(chain.request())
            .code(code)
            .protocol(Protocol.HTTP_2)
            .message(message)
            .body(body)
            .build()
    }

    fun RequestBody?.bodyToString(): String? {
        return try {
            val buffer = Buffer()
            if (this != null) this.writeTo(buffer) else return ""
            buffer.readUtf8()
        } catch (e: IOException) {
            null
        }
    }
}