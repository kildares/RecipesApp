    package recipe.kildare.com.recipeapp.persistence;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kilda on 4/8/2018.
 */

public class RecipeDbHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME =  "recipes.db";
    public static final int DATABASE_VERSION = 1;

    public RecipeDbHelper(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        sqLiteDatabase.execSQL(RecipeDB.createDbSQL());
        sqLiteDatabase.execSQL(IngredientDB.createDbSQL());

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + IngredientDB.TABLE_NAME);
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + RecipeDB.TABLE_NAME);
    }
}
