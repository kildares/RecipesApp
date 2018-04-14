package recipe.kildare.com.recipeapp.Entities;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by kilda on 4/10/2018.
 */

public class Recipe implements Parcelable {

    private String Title;
    private String Image_URL;
    private String Recipe_ID;
    private List<Ingredient> Ingredients;
    private List<Step> Steps;
    private String Servings;

    public Recipe(String recipe_ID, String title, List<Ingredient> ingredients, List<Step> steps, String servings, String imageUrl){
        Recipe_ID = recipe_ID;
        this.Title = title;
        Ingredients = ingredients;
        Steps = steps;
        Servings = servings;
        Image_URL = imageUrl;
    }

    public static final Creator<Recipe> CREATOR = new Creator<Recipe>() {
        @Override
        public Recipe createFromParcel(Parcel in) {
            return new Recipe(in);
        }

        @Override
        public Recipe[] newArray(int size) {
            return new Recipe[size];
        }
    };

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getImage_URL() {
        return Image_URL;
    }

    public void setImage_URL(String image_URL) {
        Image_URL = image_URL;
    }

    public String getRecipe_ID() {
        return Recipe_ID;
    }

    public void setRecipe_ID(String recipe_ID) {
        Recipe_ID = recipe_ID;
    }

    public List<Ingredient> getIngredients() {
        return Ingredients;
    }

    public void setIngredients(List<Ingredient> ingredients) {
        Ingredients = ingredients;
    }

    public List<Step> getSteps() {
        return Steps;
    }

    public void setSteps(List<Step> steps) {
        Steps = steps;
    }

    private Recipe(Parcel parcel)
    {
        this.Title = parcel.readString();
        this.Image_URL = parcel.readString();
        this.Recipe_ID = parcel.readString();
        this.Ingredients = parcel.readArrayList(Ingredient.class.getClassLoader());
        this.Steps = parcel.readArrayList(Step.class.getClassLoader());
        this.Servings = parcel.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {

        parcel.writeString(this.Title);
        parcel.writeString(this.Image_URL);
        parcel.writeString(this.Recipe_ID);
        parcel.writeList(Ingredients);
        parcel.writeList(Steps);
        parcel.writeString(Servings);
    }
}
