package com.surftestingtask.data.local.repository

import com.surftestingtask.data.local.database.CocktailsDao
import com.surftestingtask.data.local.models.CocktailDatabaseEntity
import com.surftestingtask.data.local.states.LocalAddingCocktailResult
import com.surftestingtask.data.local.states.LocalObtainingCocktailByIdResult
import com.surftestingtask.data.local.states.LocalObtainingCocktailsResult

class LocalCocktailsRepositoryImpl(private val cocktailsDao: CocktailsDao) :
    LocalCocktailsRepository {

    override suspend fun addCocktail(cocktailDatabaseEntity: CocktailDatabaseEntity): LocalAddingCocktailResult {
        return try {
            cocktailsDao.insertCocktail(cocktailDatabaseEntity)
            LocalAddingCocktailResult.Success
        } catch (e: Exception) {
            LocalAddingCocktailResult.Error
        }
    }

    override suspend fun getAllCocktails(): LocalObtainingCocktailsResult {
        return try {
            LocalObtainingCocktailsResult.Success(cocktailsDao.getAllCocktails())
        } catch (e: Exception) {
            LocalObtainingCocktailsResult.Error
        }
    }

    override suspend fun getCocktailById(id: Long): LocalObtainingCocktailByIdResult {
        return try {
            LocalObtainingCocktailByIdResult.Success(cocktailsDao.getCocktailById(id))
        } catch (e: Exception) {
            LocalObtainingCocktailByIdResult.Error
        }
    }
}