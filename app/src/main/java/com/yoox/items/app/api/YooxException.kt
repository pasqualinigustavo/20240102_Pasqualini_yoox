package com.yoox.items.app.api

import retrofit2.HttpException
import retrofit2.Response
import retrofit2.Retrofit
import java.io.IOException
import java.net.HttpURLConnection

class YooxException(
    val msg: String?,
    private val exceptionResponse: Response<*>?,
    private val exceptionKind: Kind,
    private val exception: Throwable?,
) : RuntimeException() {

    private var errorBody: String? = null

    init {
        if (exception is HttpException) {
            errorBody = exception.response()?.errorBody()?.string()
        }
    }

    companion object {
        fun httpError(
            throwable: Throwable?,
            url: String?,
            response: Response<*>?,
            retrofit: Retrofit?
        ): YooxException {
            val message = response?.code().toString() + " " + response?.message()
            return YooxException(
                message,
                response,
                Kind.fromStatusCode(response?.code()),
                throwable
            )
        }


        fun networkError(exception: IOException): YooxException {
            return YooxException(exception.message, null, Kind.NETWORK, exception)
        }

        fun unexpectedError(exception: Throwable): YooxException {
            return YooxException(exception.message, null, Kind.UNEXPECTED, exception)
        }

        fun nullPointerError(exception: NullPointerException): YooxException {
            return YooxException(null, null, Kind.NULL_POINTER, exception)
        }

        fun technicalDifficutiesError(throwable: Throwable): YooxException {
            return YooxException(
                null,
                null,
                Kind.TECHNICAL_DIFFICULTIES,
                throwable
            )
        }
    }

    enum class Kind {
        NETWORK,

        BAD_REQUEST,

        TIMEOUT,

        UNEXPECTED,

        UNAUTHORIZED,

        NOT_LINKED,

        NULL_POINTER,

        FORBIDDEN,

        UNPROCESSABLE_ENTITY,

        TECHNICAL_DIFFICULTIES,

        MAINTENANCE;

        companion object {
            fun fromStatusCode(code: Int?): Kind {
                return when (code) {
                    HttpURLConnection.HTTP_FORBIDDEN -> FORBIDDEN
                    HttpURLConnection.HTTP_UNAUTHORIZED -> UNAUTHORIZED
                    410, 412 -> NOT_LINKED
                    503 -> MAINTENANCE
                    400 -> BAD_REQUEST
                    422 -> UNPROCESSABLE_ENTITY
                    in 401..599 -> TECHNICAL_DIFFICULTIES
                    else -> TECHNICAL_DIFFICULTIES
                }
            }
        }
    }

    fun getKind(): Kind {
        return exceptionKind
    }

    fun isTimeout(): Boolean {
        return getKind() == Kind.TIMEOUT || getKind() == Kind.TECHNICAL_DIFFICULTIES
    }

    fun isBadRequest(): Boolean {
        return getKind() == Kind.BAD_REQUEST || getKind() == Kind.TECHNICAL_DIFFICULTIES
    }

    fun isForbidden(): Boolean {
        return getKind() == Kind.FORBIDDEN
    }

    fun isUnauthorized(): Boolean {
        return getKind() == Kind.UNAUTHORIZED
    }

    fun isNotLinked(): Boolean {
        return getKind() == Kind.NOT_LINKED
    }

    fun isUnexpected(): Boolean {
        return getKind() == Kind.UNEXPECTED
    }

    fun isMaintenance(): Boolean {
        return getKind() == Kind.MAINTENANCE
    }

    fun isNetwork(): Boolean {
        return getKind() == Kind.NETWORK || getKind() == Kind.TECHNICAL_DIFFICULTIES
    }

    fun isUnProcessableEntity(): Boolean {
        return getKind() == Kind.UNPROCESSABLE_ENTITY
    }

    fun isTechnicalDifficulties(): Boolean {
        return getKind() == Kind.TECHNICAL_DIFFICULTIES
    }

    fun isAnInternalServerError() =
        (exceptionResponse?.code() == HttpURLConnection.HTTP_INTERNAL_ERROR)
}
