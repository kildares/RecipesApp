package recipe.kildare.com.recipeapp.Entities;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by kilda on 4/10/2018.
 */

public class Ingredient implements Parcelable {

    public String Quantity;
    public String Measure;
    public String Name;

    public Ingredient(String name, String quantity, String measure){
        this.Name  = name;
        this.Quantity = quantity;
        this.Measure = measure;
    }

    private Ingredient(Parcel parcel)
    {
        this.Quantity = parcel.readString();
        this.Measure = parcel.readString();
        this.Name = parcel.readString();
    }

    public static final Parcelable.Creator<Ingredient> CREATOR = new Parcelable.Creator<Ingredient>(){

        @Override
        public Ingredient createFromParcel(Parcel parcel) {
            return new Ingredient(parcel);
        }

        @Override
        public Ingredient[] newArray(int i) {
            return new Ingredient[i];
        }
    };

    public String getQuantity() {
        return Quantity;
    }

    public void setQuantity(String quantity) {
        Quantity = quantity;
    }

    public String getMeasure() {
        return Measure;
    }

    public void setMeasure(String measure) {
        Measure = measure;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(this.Quantity);
        parcel.writeString(this.Measure);
        parcel.writeString(this.Name);
    }

}
