package com.flint.smartsecurity.android;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;
import com.badlogic.gdx.backends.android.AndroidApplication;
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration;
import com.flint.smartsecurity.AndroidDataManager;
import com.flint.smartsecurity.IDataManager;
import com.flint.smartsecurity.SmartSecurity;
import java.util.concurrent.Executor;

public class AndroidLauncher extends AndroidApplication {
    private FingerprintDataManager fingerprintDataManager;
    private IDataManager dataManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        fingerprintDataManager = new FingerprintDataManager(this);
        dataManager = new AndroidDataManager(fingerprintDataManager);

        Executor executor = ContextCompat.getMainExecutor(this);
        BiometricPrompt biometricPrompt = new BiometricPrompt(this, executor, new BiometricPrompt.AuthenticationCallback() {
            @Override
            public void onAuthenticationSucceeded(@NonNull BiometricPrompt.AuthenticationResult result) {
                super.onAuthenticationSucceeded(result);
                String userId = "user_123";
                long timestamp = System.currentTimeMillis();
                long duration = 1000;
                fingerprintDataManager.addAccessRecord(userId, timestamp, duration);
            }
        });

        BiometricPrompt.PromptInfo promptInfo = new BiometricPrompt.PromptInfo.Builder()
            .setTitle("Fingerprint Authentication")
            .setSubtitle("Authenticate with your fingerprint")
            .setNegativeButtonText("Cancel")
            .build();

        biometricPrompt.authenticate(promptInfo);

        AndroidApplicationConfiguration configuration = new AndroidApplicationConfiguration();
        configuration.useImmersiveMode = true;
        initialize(new SmartSecurity(dataManager), configuration);
    }
}
