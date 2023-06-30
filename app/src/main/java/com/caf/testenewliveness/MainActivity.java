package com.caf.testenewliveness;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import input.CafStage;
import input.FaceAuthenticator;
import input.VerifyAuthenticationListener;
import output.FaceAuthenticatorResult;

import com.caf.facelivenessiproov.input.CAFStage;
import com.caf.facelivenessiproov.input.FaceLiveness;
import com.caf.facelivenessiproov.input.VerifyLivenessListener;
import com.caf.facelivenessiproov.output.FaceLivenessResult;

public class MainActivity extends AppCompatActivity {

    //Add your information here
    public final String YOUR_MOBILE_TOKEN = "";
    public final String YOUR_PERSON_ID = "";

    //Defining all the activity's components
    Button btFaceLiveness, btFaceAuth;
    TextView tvSdkName, tvSdkStatus;
    ProgressBar pbLoading;
    ConstraintLayout clFrontLayout;

    public String sdkName;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //binding view variables to the view components
        btFaceLiveness = findViewById(R.id.btFaceLivenes);
        btFaceAuth = findViewById(R.id.btFaceAuth);
        tvSdkName = findViewById(R.id.tvSdkName);
        tvSdkStatus = findViewById(R.id.tvSdkStatus);
        clFrontLayout = findViewById(R.id.clFront);
        pbLoading = findViewById(R.id.progressBar2);
    }

    public void startFaceLiveness(View view) {
        sdkName = "Face Liveness";

        FaceLiveness faceLiveness = new FaceLiveness.Builder(YOUR_MOBILE_TOKEN)
                .setStage(CAFStage.DEV)
                .build();

        faceLiveness.startSDK(this, YOUR_PERSON_ID, new VerifyLivenessListener() {
            @Override
            public void onSuccess(FaceLivenessResult faceLivenessResult) {
                //The sdk has finished with success, it doesn't mean that the user is approved. It means that everything
                // went through and worked.
                runOnUiThread(() -> {
                    tvSdkName.setText(sdkName);
                    tvSdkStatus.setText("isMatch: " + faceLivenessResult.isAlive);
                });
            }

            @Override
            public void onError(FaceLivenessResult faceLivenessResult) {
                //The sdk has finished error, the message will be return in the result, so you can see what went wrong.
                runOnUiThread(() -> {
                    tvSdkName.setText(sdkName);
                    tvSdkStatus.setText("errorMessage: " + faceLivenessResult.errorMessage );
                });
            }

            @Override
            public void onCancel(FaceLivenessResult faceLivenessResult) {
                //The sdk has been closed by the user.
                runOnUiThread(() -> {
                    tvSdkName.setText(sdkName);
                    tvSdkStatus.setText("errorMessage: " + faceLivenessResult.errorMessage );
                });
            }

            @Override
            public void onLoading() {
                //The sdk has enter in a loading process, you can use this step to customize a loading in your application
                // that way your user know that the SDK is loading.
                runOnUiThread(() -> {
                    pbLoading.setVisibility(View.VISIBLE);
                    clFrontLayout.setVisibility(View.VISIBLE);
                });
            }

            @Override
            public void onLoaded() {
                //The sdk has finished the loading process, you can use this step to customize your application
                // that way your user know that the SDK is not loading anymore.
                runOnUiThread(() -> {
                    pbLoading.setVisibility(View.INVISIBLE);
                    clFrontLayout.setVisibility(View.INVISIBLE);
                });
            }
        });
    }

    public void startFaceAuth(View view) {
        sdkName = "Face Auth";

        FaceAuthenticator faceAuthenticator = new FaceAuthenticator.Builder(YOUR_MOBILE_TOKEN)
                .setStage(CafStage.DEV)
                .build();

        faceAuthenticator.authenticate(this, YOUR_PERSON_ID, new VerifyAuthenticationListener() {
            @Override
            public void onSuccess(FaceAuthenticatorResult result) {
                //The sdk has finished with success, it doesn't mean that the user is approved. It means that everything
                // went through and worked.
                runOnUiThread(() -> {
                    tvSdkName.setText(sdkName);
                    tvSdkStatus.setText("isAlive: " + result.isAlive()  +"\n" + "isMatch: " + result.isMatch());
                });
            }

            @Override
            public void onError(FaceAuthenticatorResult result) {
                //The sdk has finished error, the message will be return in the result, so you can see what went wrong.
                runOnUiThread(() -> {
                    tvSdkName.setText(sdkName);
                    tvSdkStatus.setText("errorMessage: " + result.getErrorMessage() );
                });
            }

            @Override
            public void onCancel(FaceAuthenticatorResult result) {
                //The sdk has been closed by the user.
                runOnUiThread(() -> {
                    tvSdkName.setText(sdkName);
                    tvSdkStatus.setText("errorMessage: " + result.getErrorMessage() );
                });
            }

            @Override
            public void onLoading() {
                //The sdk has enter in a loading process, you can use this step to customize a loading in your application
                // that way your user know that the SDK is loading.
                runOnUiThread(() -> {
                    pbLoading.setVisibility(View.VISIBLE);
                    clFrontLayout.setVisibility(View.VISIBLE);
                });
            }

            @Override
            public void onLoaded() {
                //The sdk has finished the loading process, you can use this step to customize your application
                // that way your user know that the SDK is not loading anymore.
                runOnUiThread(() -> {
                    pbLoading.setVisibility(View.INVISIBLE);
                    clFrontLayout.setVisibility(View.INVISIBLE);
                });
            }
        });

    }
}