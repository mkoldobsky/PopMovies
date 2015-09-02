package com.example.mkoldobsky.popmovies;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MoviesFragment extends Fragment {

    ArrayAdapter<String> mMovieAdapter;

    public MoviesFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_movies, container, false);



        mMovieAdapter = new ArrayAdapter<String>(this.getActivity(),
                R.layout.list_item_movie,
                R.id.list_item_title_textview,
                new ArrayList<String>()
        );
        FetchMoviesTask moviesTask = new FetchMoviesTask(getActivity());
        moviesTask.execute(Constants.MOST_POPULAR_SORT_ORDER);
        ListView moviesListView = (ListView)rootView.findViewById(R.id.moviesListView);
        moviesListView.setAdapter(mMovieAdapter);


        return rootView;
    }

    public class FetchMoviesTask extends AsyncTask<String, Void, String[]> {

        private static final String MOST_POPULAR = "mostPopular";
        private static final String MOST_POPULAR_VALUE = "popularity.desc";
        private static final String HIGHEST_RATED_VALUE = "vote_average.desc";
        private final String LOG_TAG = FetchMoviesTask.class.getSimpleName();

        private final static String API_KEY = "xxxxx"; // need to be obfuscated

        private final Context mContext;

        public FetchMoviesTask(Context context) {
            mContext = context;
        }


        private String[] getMovieDataFromJson(String movieJsonString)
                throws JSONException {

            final String MDB_PAGE = "page";

            final String MDB_RESULTS = "results";

            String[] results = null;

            Log.v(LOG_TAG, movieJsonString);

            try {
                JSONObject movieJson = new JSONObject(movieJsonString);
                JSONArray movieArray = movieJson.getJSONArray(MDB_RESULTS);


                 results = new String[movieArray.length()];


                for(int i = 0; i < movieArray.length(); i++) {

                    JSONObject movie = movieArray.getJSONObject(i);

                    String title = movie.getString("original_title");
                    String plot = movie.getString("overview");
                    String path = movie.getString("poster_path");
                    Double vote = movie.getDouble("vote_average");


                    results[i] = "Title: " + title + " votes: " + vote;

                }


                Log.d(LOG_TAG, "FetchMoviesTask Complete. " + movieArray.length() + " Fetched");

            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            }
            return results;
        }

        @Override
        protected String[] doInBackground(String... params) {

            // Need highestRank or mostPopular
            if (params.length == 0) {
                return null;
            }
            String sortOrder = params[0];

            HttpURLConnection urlConnection = null;
            BufferedReader reader = null;

            // Will contain the raw JSON response as a string.
            String moviesJsonString = null;


            try {
                // https://api.themoviedb.org/3/discover/movie?api_key=xxxx&sort_by=popularity.desc
                //https://api.themoviedb.org/3/discover/movie?api_key=xxxx&sort_by=vote_average.desc
                final String FORECAST_BASE_URL =
                        "https://api.themoviedb.org/3/discover/movie?";
                final String API_KEY_PARAM = "api_key";
                final String SORT_BY_PARAM = "sort_by";
                final String SORT_BY_VALUE = sortOrder == MOST_POPULAR ? MOST_POPULAR_VALUE : HIGHEST_RATED_VALUE;

                Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                        .appendQueryParameter(API_KEY_PARAM, API_KEY)
                        .appendQueryParameter(SORT_BY_PARAM, SORT_BY_VALUE)
                        .build();

                URL url = new URL(builtUri.toString());

                urlConnection = (HttpURLConnection) url.openConnection();
                urlConnection.setRequestMethod("GET");
                urlConnection.connect();

                InputStream inputStream = urlConnection.getInputStream();
                StringBuffer buffer = new StringBuffer();
                if (inputStream == null) {
                    // Nothing to do.
                    return null;
                }
                reader = new BufferedReader(new InputStreamReader(inputStream));

                String line;
                while ((line = reader.readLine()) != null) {
                    // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                    // But it does make debugging a *lot* easier if you print out the completed
                    // buffer for debugging.
                    buffer.append(line + "\n");
                }

                if (buffer.length() == 0) {
                    // Stream was empty.  No point in parsing.
                    return null;
                }
                moviesJsonString = buffer.toString();
                return getMovieDataFromJson(moviesJsonString);
            } catch (IOException e) {
                Log.e(LOG_TAG, "Error ", e);
            } catch (JSONException e) {
                Log.e(LOG_TAG, e.getMessage(), e);
                e.printStackTrace();
            } finally {

                if (urlConnection != null) {
                    urlConnection.disconnect();
                }
                if (reader != null) {
                    try {
                        reader.close();
                    } catch (final IOException e) {
                        Log.e(LOG_TAG, "Error closing stream", e);
                    }
                }
            }
            return null;
        }

        @Override
        protected void onPostExecute(String[] result) {
            if (result != null) {
                mMovieAdapter.clear();
                for(String movieStr : result) {
                    mMovieAdapter.add(movieStr);
                }
                // New data is back from the server.  Hooray!
            }
        }
    }
}
