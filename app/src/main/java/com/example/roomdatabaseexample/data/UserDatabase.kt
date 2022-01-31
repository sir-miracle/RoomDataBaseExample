package com.example.roomdatabaseexample.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.roomdatabaseexample.model.User

//contains the database holder and serves as the main access point for the
// underlying connection to your app's persisted relational data.
// it needs to be an abstract class, and extends the RoomDatabase class

@Database(
    entities = [User::class],
    version = 1, exportSchema = false
)//you specify as many entities you
// have here, now we only have the User entity
abstract class UserDatabase : RoomDatabase() {

    abstract fun userDao(): IUserDao

    //we want to make our Userdatabase a singleton class so it can be seen by other classes,
    // so we are creating a companion object for it
    companion object {
        @Volatile
        private var INSTANCE: UserDatabase? = null

        fun getDatabase(context: Context): UserDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            //if the instance is null, then we create an instance
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    UserDatabase::class.java,
                    "user_database"//name of the database - choose any name
                ).build()
                INSTANCE = instance
                return instance
            }
        }


    }
}