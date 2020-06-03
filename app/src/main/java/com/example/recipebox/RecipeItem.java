package com.example.recipebox;

public class RecipeItem {
    private int mImageResource;
    private String mRecipeName;
    private String mRecipeDesc;

    public RecipeItem(int imageResource, String text, String text2){
        mImageResource = imageResource;
        mRecipeName = text;
        mRecipeDesc = text2;
    }

    public int getmImageResource() {
        return mImageResource;
    }

    public String getmRecipeName() {
        return mRecipeName;
    }

    public String getmRecipeDesc(){return mRecipeDesc;}
}
