@file:Suppress("MoveVariableDeclarationIntoWhen")

package com.baseproject.data.source.remote.api.mock

import com.baseproject.data.source.remote.api.mock.managers.UserMockManager
import okhttp3.Interceptor
import okhttp3.Response
import java.lang.Thread.sleep
import javax.inject.Inject

class MockInterceptor @Inject constructor() : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {

        val uri = chain.request().url.toUri().path.toString()

        val manager: MockApiManager = when (uri) {
            "/api/users" -> UserMockManager
            else         -> return chain.proceed(chain.request())
        }

        return manager.getResponse(chain).also { sleep(manager.delay) }
    }
}