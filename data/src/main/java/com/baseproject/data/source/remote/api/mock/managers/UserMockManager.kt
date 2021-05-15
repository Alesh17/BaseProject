package com.baseproject.data.source.remote.api.mock.managers

import com.baseproject.data.model.pojo.user.UserFilterRequest
import com.baseproject.data.source.remote.api.mock.MockApiManager
import okhttp3.Interceptor
import okhttp3.Response
import okhttp3.ResponseBody

object UserMockManager : MockApiManager() {

    override fun getResponse(chain: Interceptor.Chain): Response {

        val code: Int
        val body: ResponseBody

        val sort = chain.getBody<UserFilterRequest>().sort

        if (sort == "asc") {
            code = 200
            body = successResponse.createBody()
        } else {
            code = 400
            body = errorResponse.createBody()
        }

        return createResponse(chain, code, successResponse, body)
    }
}

private const val successResponse = """
{
  "error": 0,
  "id": "1",
  "firstName": "Dmitry",
  "lastName": "Alesh"
}
"""

private const val errorResponse = """
{
  "error": 1
}
"""