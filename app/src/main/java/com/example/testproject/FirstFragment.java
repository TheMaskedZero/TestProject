package com.example.testproject;

import static android.content.ContentValues.TAG;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.android.volley.Response;
import com.example.testproject.databinding.FragmentFirstBinding;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Console;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class FirstFragment extends Fragment {

    private FragmentFirstBinding binding;

    private TextView textView;
    String Meals;
    JSONArray meals;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState


    ) {

        binding = FragmentFirstBinding.inflate(inflater, container, false);
        return binding.getRoot();


    }

//    private void addItemsFromJson(){
//        try{
//            for(int i=0; i<meals.length(); ++i){
//
//                JSONObject itemObj = meals.getJSONObject(i);
//
//                String mealName = itemObj.getString("strMeal")
//                String mealCategory = itemObj.getString("strCategory")
//                textView.setText(mealName + mealCategory);
//            }
//        }catch{IOException | JSONException e){
//            Log.d(TAG, "addItemsFromJSON: ", e);
//        }
//        }


    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        textView = (TextView) getView().findViewById(R.id.textView);

//        char[] lotsOfChars = "abcdefghijklmnoprstvwy".toCharArray();
        char[] lotsOfChars = "a".toCharArray();


        binding.btnApiButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                RequestQueue queue = Volley.newRequestQueue(getContext());
                for (char letters : lotsOfChars) {
                    String url = "https://www.themealdb.com/api/json/v1/1/search.php?f=" + letters;

                    JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, url, null,
                            new Response.Listener<JSONObject>() {
                                @Override
                                public void onResponse(JSONObject response) {

//                                    String CityName = "";
                                    Meals = "";
                                    meals = new JSONArray();

                                    try {
                                        JSONObject City = response.getJSONObject("meals");
                                        meals = (City.getJSONArray("meals"));
                                        meals.getString(0);

                                    } catch (JSONException e) {
                                        e.printStackTrace();
                                    }


//                                    try {
//                                        for (int i = 0; i<meals.length(); ++i){
//                                            JSONObject itemObj = meals.getJSONObject(i);
//
//                                            String mealName = itemObj.getString("strMeal");
//                                            String mealCategory = itemObj.getString("strCategory");
//                                            textView.setText(mealName + mealCategory);
//                                        }
//                                    } catch (JSONException e) {
//                                        e.printStackTrace();
//                                    }
//                                Toast.makeText(getContext(), ,Toast.LENGTH_SHORT).show();
                                }
                            }, new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(getContext(), "Error Json", Toast.LENGTH_SHORT).show();
                        }
//                }) {
//                    @Override
//                    public Map<String, String> getHeaders() throws AuthFailureError{
//                        HashMap<String, String> headers = new HashMap<String, String>();
//                        //headers.put("content-type", "application/Json");
//                        headers.put("X-RapidAPI-Host", "themealdb.p.rapidapi.com");
//                        headers.put("X-RapidAPI-Key", "4fdb0e68f5msh95dcd880caa4071p10e5ccjsn51fdccfa2a24");
//                        return headers;
//                    }

                    });

                    queue.add(jsonObjectRequest);
//                NavHostFragment.findNavController(FirstFragment.this)
//                        .navigate(R.id.action_FirstFragment_to_SecondFragment);
                }
            }
        });

        binding.btnFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

        binding.btnMoreFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

}