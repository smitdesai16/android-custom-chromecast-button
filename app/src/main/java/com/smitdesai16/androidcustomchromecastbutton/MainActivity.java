package com.smitdesai16.androidcustomchromecastbutton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.mediarouter.app.MediaRouteButton;

import android.os.Bundle;

import com.google.android.gms.cast.framework.CastButtonFactory;
import com.google.android.gms.cast.framework.CastContext;
import com.google.android.gms.cast.framework.SessionManager;

public class MainActivity extends AppCompatActivity {

    private SessionManager sessionManager;
    private final CastSessionListener castSessionListener = new CastSessionListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        CastContext castContext = CastContext.getSharedInstance(this);
        sessionManager = castContext.getSessionManager();

        MediaRouteButton castButton = findViewById(R.id.media_route_button);
        CastButtonFactory.setUpMediaRouteButton(getApplicationContext(), castButton);
    }

    @Override
    protected void onResume() {
        super.onResume();
        sessionManager.addSessionManagerListener(castSessionListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sessionManager.removeSessionManagerListener(castSessionListener);
    }
}