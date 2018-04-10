package recipe.kildare.com.recipeapp.persistence;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by kilda on 4/9/2018.
 */

public class RecipeProvider extends ContentProvider{

    private static final int CODE_RECIPE = 100;
    private static final int CODE_RECIPE_ID = 101;

    private static final UriMatcher sUriMatcher = buildUriMatcher();

    private RecipeDbHelper mOpenHelper;

    private static final UriMatcher buildUriMatcher()
    {
        final UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        final String authority = RecipeDbContract.CONTENT_AUTHORITY;

        matcher.addURI(authority, RecipeDbContract.PATH_RECIPE, CODE_RECIPE);
        matcher.addURI(authority, RecipeDbContract.PATH_RECIPE + "/#", CODE_RECIPE_ID);

        return matcher;
    }


    @Override
    public boolean onCreate()
    {
        mOpenHelper = new RecipeDbHelper(getContext());
        return true;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] projection, @Nullable String selection, @Nullable String[] selectionArgs, @Nullable String sortOrder) {

        int match = sUriMatcher.match(uri);
        Cursor cursor = null;
        switch(match){
            case CODE_RECIPE:{
                SQLiteDatabase sqLiteDatabase = mOpenHelper.getReadableDatabase();

                cursor = sqLiteDatabase.query(RecipeDbContract.RecipeEntry.TABLE_NAME,
                        projection,
                        selection,
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                        );
                break;
            }
            case CODE_RECIPE_ID:{
                SQLiteDatabase sqLiteDatabase = mOpenHelper.getWritableDatabase();

                cursor = sqLiteDatabase.query(RecipeDbContract.RecipeEntry.TABLE_NAME,
                        projection,
                        RecipeDbContract.RecipeEntry.COLUMN_ID + " = ?",
                        selectionArgs,
                        null,
                        null,
                        sortOrder
                );
                break;
            }
            default:{
                throw new UnsupportedOperationException("CANNOT PROCESS URI");
            }
        }

        try{
            cursor.setNotificationUri(getContext().getContentResolver(), uri);
        }catch(NullPointerException e){
            e.printStackTrace();
        }
        return cursor;
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        return null;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String selection, @Nullable String[] selectionArgs) {
        int match = sUriMatcher.match(uri);

        int totalDeleted = 0;

        selection = "1";
        switch(match){
            case CODE_RECIPE:{

                totalDeleted = mOpenHelper.getWritableDatabase().delete(
                        RecipeDbContract.RecipeEntry.TABLE_NAME,
                        selection,
                        null
                );

                break;
            }
            case CODE_RECIPE_ID:{

                totalDeleted = mOpenHelper.getWritableDatabase().delete(
                        RecipeDbContract.RecipeEntry.TABLE_NAME,
                        selection,
                        null
                );
                break;

            }

            default:{
                throw new UnsupportedOperationException("CANNOT PROCESS URI");
            }
        }

        if(totalDeleted != 0)
            getContext().getContentResolver().notifyChange(uri, null);

        return totalDeleted;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String s, @Nullable String[] strings) {
        return 0;
    }

    @Override
    public int bulkInsert(@NonNull Uri uri, @NonNull ContentValues[] values) {
        final SQLiteDatabase db = mOpenHelper.getWritableDatabase();

        switch(sUriMatcher.match(uri)){
            case CODE_RECIPE:{
                db.beginTransaction();
                int rowsInserted = 0;

                try{
                    for(ContentValues contentValue : values){
                        long result = db.insert(
                                RecipeDbContract.RecipeEntry.TABLE_NAME,
                                null,
                                contentValue
                                );

                        if(result != -1)
                            rowsInserted++;

                    }

                    db.setTransactionSuccessful();
                }finally {
                    db.endTransaction();
                }

                if(rowsInserted > 0){
                    getContext().getContentResolver().notifyChange(uri,null);
                }
                return rowsInserted;
            }
            default:{
                return super.bulkInsert(uri, values);
            }
        }
    }
}
