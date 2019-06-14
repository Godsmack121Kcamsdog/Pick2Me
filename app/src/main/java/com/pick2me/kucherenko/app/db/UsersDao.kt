package com.pick2me.kucherenko.app.db

import android.arch.persistence.room.Dao
import android.arch.persistence.room.Delete
import android.arch.persistence.room.Insert
import android.arch.persistence.room.OnConflictStrategy.REPLACE
import android.arch.persistence.room.Query
import com.pick2me.kucherenko.app.api.data.UsersBody
import io.reactivex.Single

@Dao
interface UsersDao {

    @Query("SELECT * FROM users")
    fun getAll(): Single<List<UsersBody>>

    @Query("SELECT * FROM users WHERE firstName LIKE :name LIMIT 1")
    fun findByName(name: String): Single<UsersBody>

    @Query("SELECT * FROM users WHERE id LIKE :id LIMIT 1")
    fun findById(id: Int): Single<UsersBody>

    @Insert
    fun insertAll(vararg entities: UsersBody)

    @Insert(onConflict = REPLACE)
    fun insert(entity: UsersBody)

    @Delete
    fun delete(entity: UsersBody)
}