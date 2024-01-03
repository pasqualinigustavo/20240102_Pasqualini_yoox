package com.yoox.items.app.api

import com.yoox.items.utilities.Const.EMPTY_RESPONSE_ERROR
import com.yoox.items.utilities.Const.requestDispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import retrofit2.Response
import java.io.IOException

class ApiResponseHandler<T> {

    fun <T> handleApiCall(apiCall: suspend () -> Response<T>): Flow<T?> {
        return flow {
            runCatching {
                apiCall()
            }.onSuccess { result ->
                if (result.isSuccessful) {
                    val responseBody = result.body()
                    if (responseBody != null) {
                        emit(responseBody)
                    } else {
                        throw EmptyResponseException(EMPTY_RESPONSE_ERROR)
                    }
                } else {
                    val exception = asException(result)
                    throw exception
                }
            }.onFailure { error ->
                val exception = when (error) {
                    is IOException -> YooxException.networkError(error)
                    is NullPointerException -> YooxException.nullPointerError(error)
                    else -> YooxException.unexpectedError(error)
                }
                throw exception
            }
        }.flowOn(requestDispatchers)
    }

    fun asException(response: Response<*>): YooxException {
        return when (response.code()) {
            else -> YooxException.httpError(
                throwable = null,
                url = null,
                response = response,
                retrofit = null
            )
        }
    }
}

// Custom exception for empty response
class EmptyResponseException(message: String) : Exception(message)
