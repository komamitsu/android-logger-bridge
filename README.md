# android-logger-bridge

android-logger-bridge enables using Android Log util on non Android envinronment. If this library runs on Android, it outputs to logcat. If not, it outputs to stdout.

## Requirement

Android OS >= 2.2

## Usage

You can use android-logger-bridge with importing org.komamitsu.android.util.Log.

    import org.komamitsu.android.util.Log;

    public class FooActivity {
            private static final String TAG = FooActivity.class.getSimpleName();

                 :

            public void onBarEvent() {
                Log.d(TAG, "onBarEvent: start");
                        
                    :
                    
                try {
                        
                    :

                }
                catch (IOException e) {
                    Log.e(TAG, "API call error", e);

                        :
