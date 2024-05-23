package com.example.technical.challenge.data.base

import android.content.Context
import com.example.technical.challenge.utils.hasInternetConnection
import com.squareup.moshi.Moshi
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class SafeApiCaller @Inject constructor(@ApplicationContext private val context: Context, private val moshi: Moshi) {

    suspend fun <T> safeApiCall(
        dispatcher: CoroutineDispatcher,
        apiCall: suspend () -> T
    ): ResultWrapper<T> {
        return withContext(dispatcher) {
            try {
                ResultWrapper.Success(apiCall.invoke())
            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> ResultWrapper.ERROR(Errors.NetworkError)

                    is HttpException -> {
                        val code = throwable.code()
                        val errorResponse = convertErrorBody(throwable)

                        ResultWrapper.ERROR(Errors.GenericError(code, errorResponse))
                    }

                    else -> {
                        if(hasInternetConnection(context)){
                            ResultWrapper.ERROR(Errors.InternetConnectionError)
                        }else{
                            ResultWrapper.ERROR(Errors.NotSure)
                        }
                    }
                }
            }
        }
    }

    private fun convertErrorBody(throwable: HttpException): ErrorResponse? {
        return try {
            throwable.response()?.errorBody()?.source()?.let {
                val moshiAdapter = moshi.adapter(ErrorResponse::class.java)
                moshiAdapter.fromJson(it)
            }
        } catch (exception: Exception) {
            null
        }
    }
}