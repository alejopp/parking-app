package com.example.parkingcover.data.db

import android.database.sqlite.SQLiteException
import com.example.parkingcover.R
import com.example.parkingcover.utils.ResponseStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

suspend fun <T> makeDatabaseCall(
    call: suspend () -> T
): ResponseStatus<T> = withContext(Dispatchers.IO) {
    try {
        ResponseStatus.Success(call())
    } catch (e: SQLiteException) {
        ResponseStatus.Error(R.string.unknown_sql_exception)
    } catch (e: Exception) {
        val errorMessage = R.string.unknown_error
        ResponseStatus.Error(errorMessage)
    }
}