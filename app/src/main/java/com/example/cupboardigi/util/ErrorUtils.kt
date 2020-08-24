package com.example.cupboardigi.util

import com.google.gson.JsonParser
import com.squareup.moshi.Moshi
import retrofit2.HttpException
import java.net.ConnectException
import java.net.SocketTimeoutException
import java.net.UnknownHostException
import javax.net.ssl.HttpsURLConnection

object ErrorUtils {
    fun getErrorThrowableMsg(error: Throwable): String = when (error) {
        is HttpException ->
            when (error.code()) {
                HttpsURLConnection.HTTP_UNAUTHORIZED -> "Tidak dapat mengakses data"
                HttpsURLConnection.HTTP_NOT_FOUND -> "Data tidak ditemukan"
                HttpsURLConnection.HTTP_INTERNAL_ERROR -> "Terjadi gangguan pada server"
                HttpsURLConnection.HTTP_BAD_REQUEST -> "Data tidak sesuai"
                HttpsURLConnection.HTTP_FORBIDDEN -> "Sesi telah berakhir"
                else -> "Oops, Terjadi gangguan, coba lagi beberapa saat"
            }
        is UnknownHostException -> "Tidak ada koneksi internet"
        else -> "Terjadi kesalahan"
    }

    fun getErrorThrowableCode(error: Throwable): Int = when (error) {
        is HttpException ->
            when (error.code()) {
                HttpsURLConnection.HTTP_UNAUTHORIZED -> 401
                HttpsURLConnection.HTTP_NOT_FOUND -> 404
                HttpsURLConnection.HTTP_INTERNAL_ERROR -> 500
                HttpsURLConnection.HTTP_BAD_REQUEST -> 400
                HttpsURLConnection.HTTP_FORBIDDEN -> 403
                HttpsURLConnection.HTTP_CONFLICT -> 409
                else -> error.code()
            }
        else -> 500
    }

    fun getServiceErrorMsg(error: Throwable): String {
        var message = "Terjadi kesalahan pada service kami"
        message = when (error) {
            is HttpException -> {
                try {
                    val errorJsonString = error.response()
                        ?.errorBody()?.string()
                    JsonParser().parse(errorJsonString)
                        .asJsonObject["message"]
                        .asString
                } catch (e: Exception) {
                    message
                }
            }
            is UnknownHostException -> "Unknown Error"
            is ConnectException -> "No internet connected"
            is SocketTimeoutException -> "No internet connected"
            is Errors.OfflineException -> "No internet connected"
            is Errors.FetchException -> "Fetch exception"
            else -> error.message ?: message
        }
        return message
    }

    fun getErrorCodeMsa(error: Throwable): Int {
        var code = getErrorThrowableCode(error)
        code = when (error) {
            is HttpException -> {
                try {
                    val errorJsonString = error.response()
                        ?.errorBody()?.string()
                    JsonParser().parse(errorJsonString)
                        .asJsonObject["code"]
                        .asInt
                } catch (e: Exception) {
                    code
                }
            }
            else -> code
        }
        return code
    }

    fun getDataFromError(error: Throwable): Int {
        var data = getErrorThrowableCode(error)
        data = when (error) {
            is HttpException -> {
                try {
                    val errorJsonString = error.response()?.errorBody()?.string()
                    JsonParser.parseString(errorJsonString)
                        .asJsonObject["data"]
                        .asJsonObject["loginAttempt"]
                        .asInt
                } catch (e: Exception) {
                    data
                }
            }
            else -> getErrorThrowableCode(error)
        }
        return data
    }

    fun getObjectDataFromError(error: Throwable, serviceClass: Class<out Any>): Any {
        var data: Any? = null
        data = when (error) {
            is HttpException -> {
                try {
                    val errorJsonString = error.response()?.errorBody()?.string().toString()
                    val moshi = Moshi.Builder()
                        .build()
                    val jsonAdapter = moshi.adapter<Any>(serviceClass)
                    jsonAdapter.fromJson(errorJsonString)
                } catch (e: Exception) {
                    data
                }!!
            }
            else -> getServiceErrorMsg(error)
        }
        return data
    }

    fun getDataFromErrorReward(error: Throwable): Int {
        var data = getErrorThrowableCode(error)
        data = when (error) {
            is HttpException -> {
                try {
                    val errorJsonString = error.response()?.errorBody()?.string()
                    JsonParser().parse(errorJsonString)
                        .asJsonObject["data"]
                        .asJsonObject["verifyAttempt"]
                        .asInt
                } catch (e: Exception) {
                    data
                }
            }
            else -> getErrorThrowableCode(error)
        }
        return data
    }

    fun getMsgFromError(error: Throwable): String {
        if (error is HttpException) {
            try {
                val errorJsonString = error.response()?.errorBody()?.string()
                return JsonParser.parseString(errorJsonString)
                    .asJsonObject["message"]
                    .asString
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return ""
    }

    sealed class Errors
        (msg: String) : Exception(msg) {
        class OfflineException(msg: String = "Not Connected to Internet") : Errors(msg)
        class FetchException(msg: String) : Errors(msg)
    }

}