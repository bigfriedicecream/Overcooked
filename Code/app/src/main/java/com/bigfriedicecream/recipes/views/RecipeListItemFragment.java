package com.bigfriedicecream.recipes.views;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

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
            TextView servesMakes = view.findViewById(R.id.serves_makes);
            TextView prepTime = view.findViewById(R.id.prep_time);
            TextView cookTime = view.findViewById(R.id.cook_time);

            String servesMakesString = model.serves > 0 ? "Serves " + String.valueOf(model.serves) : "Makes " + String.valueOf(model.makes);
            String prepTimeString = "Prep " + String.valueOf(model.prepTime) + " min";
            String cookTimeString = "Cook " + String.valueOf(model.cookTime) + " min";

            Ion.with(hero)
                    //.placeholder(R.drawable.placeholder_image)
                    //.error(R.drawable.error_image)
                    //.animateLoad(spinAnimation)
                    //.animateIn(fadeInAnimation)
                    .load("https://c7823c74fcf5919154bf-dc9422fbfab3e488dbd72b998b6187ac.ssl.cf4.rackcdn.com/content/3000/2763/loup-emilyskyefit-sweetpotatonachos-veglentilchilli-l.jpg");
            title.setText(model.title);
            servesMakes.setText(servesMakesString);
            prepTime.setText(prepTimeString);
            cookTime.setText(cookTimeString);

            final EventsDispatcher eventsDispatcher = EventsDispatcher.getInstance();

            view.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    eventsDispatcher.dispatch("NAVIGATION_RECIPE", model.id);
                }
            });
        }

        return view;
    }
}
