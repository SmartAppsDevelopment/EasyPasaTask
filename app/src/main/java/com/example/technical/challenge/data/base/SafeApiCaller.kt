package com.example.technical.challenge.data.base

import android.app.Application
import android.content.Context
import android.view.View
import com.example.technical.challenge.R
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
//                val application = getApplication<Application>()
//                if(!hasInternetConnection(application)) {
//                    errorFieldVisibility.set(View.VISIBLE)
//                    errorFieldString.set(application.getString(R.string.error_no_internet))
//                    return
//                }

            } catch (throwable: Throwable) {
                when (throwable) {
                    is IOException -> ResultWrapper.NetworkError
                    is HttpException -> {
                        val code = throwable.code()
                        val errorResponse = convertErrorBody(throwable)
                        ResultWrapper.GenericError(code, errorResponse)
                    }
                    else -> {
                        ///ResultWrapper.GenericError(null, null)
                        if(hasInternetConnection(context)){
                            ResultWrapper.InternetConnectionError
                        }else{
                            ResultWrapper.InternetConnectionError
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