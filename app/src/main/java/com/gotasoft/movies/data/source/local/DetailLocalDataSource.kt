package com.gotasoft.movies.data.source.local

import android.content.Context
import com.gotasoft.movies.data.Detail
import com.gotasoft.movies.data.DetailDao
import org.greenrobot.greendao.DaoException

/**
 * Created by dattien on 6/2/18.
 */

class DetailLocalDataSource(context: Context) {

    var mDetailDao: DetailDao? = null

    init {
        mDetailDao = LocalDatabaseHelper.getInstance(context).daoSession.detailDao
    }

    fun addDetail(detail: Detail): Boolean {
        return mDetailDao?.insert(detail)!! > 0
    }

    fun updateDetail(detail: Detail) {
        mDetailDao?.update(detail)
    }

    fun removeDetail(detail: Detail) {
        mDetailDao?.delete(detail)
    }

    fun getListDetail(): List<Detail>? {
        try {
            return mDetailDao?.queryBuilder()?.orderDesc(DetailDao.Properties.Id)?.list()
        } catch (e: DaoException) {
            e.printStackTrace()
            return null
        }
    }

    fun getDetail(categoryId: String): Detail? {
        try {
            return mDetailDao?.queryBuilder()?.where(DetailDao.Properties.Id.eq(categoryId))?.list()?.get(0)
        } catch (e: DaoException) {
            e.printStackTrace()
            return null
        }
    }
}
