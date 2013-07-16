# android-logger-bridge

android-logger-bridge enables using Android Log util on non Android envinronment. If this library runs on Android, it outputs to logcat. If not, it outputs to stdout.

## Requirement

Android OS >= 2.2

## Usage

If you're using maven, add the folloing directive:

    <dependencies>
      <dependency>
  		<groupId>org.komamitsu</groupId>
  		<artifactId>android-logger-bridge</artifactId>
  		<version>0.0.1</version>
      </dependency>
    </dependencies>

If not, download android-logger-bridge-x.x.x.jar from http://search.maven.org/#search%7Cga%7C1%7Ca%3A%22android-logger-bridge%22.

Next, import org.komamitsu.android.util.Log.

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
