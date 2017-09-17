package com.gotasoft.movies.data.source.local

import android.content.Context
import com.gotasoft.movies.data.DaoMaster

import org.greenrobot.greendao.database.Database

/**
 * Created by dattien on 9/17/17.
 */

class SqliteOpenHelper(context: Context, name: String) : DaoMaster.DevOpenHelper(context, name) {

    override fun onUpgrade(db: Database?, oldVersion: Int, newVersion: Int) {
        super.onUpgrade(db, oldVersion, newVersion)
    }
}
