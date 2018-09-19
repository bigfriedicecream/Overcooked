package com.bigfriedicecream.recipes.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bigfriedicecream.recipes.BuildConfig;
import com.bigfriedicecream.recipes.R;
import com.bigfriedicecream.recipes.models.RecipeDataModel;
import com.bigfriedicecream.recipes.observables.EventsDispatcher;
import com.google.gson.Gson;
import com.koushikdutta.ion.Ion;

public class RecipeListItemFragment extends Fragment {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_recipe_list_item, container, false);

        Bundle bundle = getArguments();

        if (bundle != null) {
            final RecipeDataModel model = new Gson().fromJson(bundle.getString("data"), RecipeDataModel.class);
            ImageView hero = view.findViewById(R.id.hero);
            TextView title = view.findViewById(R.id.title);

            title.setText(model.getTitle());
            Ion.with(hero)
                    .load(BuildConfig.BASE_URL + "/Assets/images/" + model.getId() + "/hero.jpg");

            final EventsDispatcher eventsDispatcher = EventsDispatcher.getInstance();

            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    eventsDispatcher.dispatch("NAVIGATION_RECIPE", model.getId());
                }
            });
        }

        return view;
    }
}
