package com.gotasoft.movies.data.source.local

import android.content.Context
import com.gotasoft.movies.data.DaoMaster
import com.gotasoft.movies.data.DaoSession
import org.greenrobot.greendao.database.Database


class LocalDatabaseHelper constructor(context: Context) {
    val daoSession: DaoSession
    val database: Database

    init {
        val helper = SqliteOpenHelper(context.applicationContext, "movies-db")
        database = helper.writableDb
        daoSession = DaoMaster(database).newSession()
    }

    companion object {
        private var localDatabaseHelper: LocalDatabaseHelper? = null

        fun getInstance(context: Context): LocalDatabaseHelper {
            if (localDatabaseHelper == null) {
                localDatabaseHelper = LocalDatabaseHelper(context)
            }
            return localDatabaseHelper as LocalDatabaseHelper
        }
    }
}
